package com.labo.inventarioApi.appUser;

import com.labo.inventarioApi.registration.token.ConfirmationToken;
import com.labo.inventarioApi.registration.token.ConfirmationTokenService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

    private final static String USER_NOT_FOUND_MSG = "Usuario con email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    System.out.println("Buscando usuario con email: " + email);
    return appUserRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    
    public String signUpUser(AppUser appUser){
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if(userExists){
            //Hacer si no se activo la cuenta que envie devuelta el mail
            throw new IllegalStateException("El Email ya esta registrado.");
        }
        
        
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        
        appUserRepository.save(appUser);
        
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),appUser);
        
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        
        return token;
    }
    
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
    
}
