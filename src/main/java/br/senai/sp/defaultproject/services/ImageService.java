package br.senai.sp.defaultproject.services;

import br.senai.sp.defaultproject.errors.ExceptionCode;
import br.senai.sp.defaultproject.errors.exceptions.ImageManipulationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ImageService {

    public String save(String destinationPathString, String filename, MultipartFile file) {
        try {
            if (Objects.isNull(file) || !Objects.requireNonNull(file.getContentType()).startsWith("image/"))
                throw new ImageManipulationException(ExceptionCode.EMPTY_FILE);

            var destinationPath = Paths.get(destinationPathString);
            if (Files.notExists(destinationPath))
                Files.createDirectories(destinationPath);

            var extension = getExtension(file);
            var customFilename = filename + "." + extension;

            var destinationFile = destinationPath.resolve(Paths.get(customFilename)).normalize().toAbsolutePath();
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            return destinationFile.normalize().toString();

        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ImageManipulationException(ExceptionCode.UNEXPECTED_ERROR_DURING_IMAGE_MANIPULATION);
        }
    }

    public String getExtension(MultipartFile file) {
        return StringUtils.getFilenameExtension(file.getOriginalFilename());
    }

    public void delete(String filePath) {
        try {
            if (Objects.isNull(filePath))
                throw new ImageManipulationException(ExceptionCode.EMPTY_FILE);

            var file = Path.of(filePath);
            Files.deleteIfExists(file);

        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ImageManipulationException(ExceptionCode.UNEXPECTED_ERROR_DURING_IMAGE_MANIPULATION);
        }
    }
}
