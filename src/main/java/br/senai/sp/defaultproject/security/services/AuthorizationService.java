package br.senai.sp.defaultproject.security.services;

import br.senai.sp.defaultproject.repositories.user.UserJpaRepository;
import br.senai.sp.defaultproject.security.dto.UserDetailsDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userJpaRepository.findByEmail(username);
        user.orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserDetailsDTO(user.get());
    }
}
