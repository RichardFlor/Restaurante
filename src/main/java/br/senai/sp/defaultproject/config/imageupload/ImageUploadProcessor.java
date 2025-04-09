package br.senai.sp.defaultproject.config.imageupload;

import br.senai.sp.defaultproject.errors.ExceptionCode;
import br.senai.sp.defaultproject.errors.exceptions.InvalidRequestException;
import br.senai.sp.defaultproject.services.ImageService;
import br.senai.sp.defaultproject.utils.ConvertInputJsonToObject;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartResolver;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class ImageUploadProcessor {
    private final ImageService imageService;
    private final MultipartResolver multipartResolver;
    private final ConvertInputJsonToObject convertInputJson;

    @Value("${default-project.images-public-base-folder}")
    private String IMAGES_FOLDER;

    @Around("@annotation(postMapping) && @annotation(imageUploadEndpoint)")
    public Object aroundMethodExecution(
            ProceedingJoinPoint method,
            PostMapping postMapping,
            ImageUploadEndpoint imageUploadEndpoint
    ) throws Throwable {
        var request = (HttpServletRequest) method.getArgs()[0];
        var postMappingConsumes = Arrays.stream(postMapping.consumes()).toList();

        if (!postMappingConsumes.contains(MediaType.MULTIPART_FORM_DATA_VALUE) || !isMultipartRequest(request))
            return method.proceed(method.getArgs());

        var multipartRequest = multipartResolver.resolveMultipart(request);
        var image = Optional.ofNullable(multipartRequest.getFile("image"));
        var body = Optional.ofNullable(multipartRequest.getParameter("body")).orElseThrow(InvalidRequestException::new);

        var bodyObjectClass = imageUploadEndpoint.body();
        var convertedInput = convertInputJson.convertToObject(body, bodyObjectClass);

        image.ifPresent(uploadImage -> {
            if (!Objects.requireNonNull(uploadImage.getContentType()).startsWith("image/"))
                throw new InvalidRequestException(ExceptionCode.IMAGE_FILE_REQUIRED);

            var imageURI = imageService.save(IMAGES_FOLDER, UUID.randomUUID().toString(), uploadImage);
            request.setAttribute("imageURI", imageURI);

        });

        request.setAttribute("request", convertedInput);

        var inputArgImage = image.orElse(null);
        Object[] args = {request, inputArgImage, body};

        return method.proceed(args);
    }

    private boolean isMultipartRequest(ServletRequest request) {
        return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
    }
}
