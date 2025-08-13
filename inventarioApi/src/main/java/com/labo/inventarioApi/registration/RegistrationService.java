package com.labo.inventarioApi.registration;

import com.labo.inventarioApi.alumno.Alumno;
import com.labo.inventarioApi.appUser.AppUser;
import com.labo.inventarioApi.appUser.AppUserRole;
import com.labo.inventarioApi.appUser.AppUserService;
import com.labo.inventarioApi.email.EmailSender;
import com.labo.inventarioApi.profesor.Profesor;
import com.labo.inventarioApi.profesor.ProfesorService;
import com.labo.inventarioApi.registration.token.ConfirmationToken;
import com.labo.inventarioApi.registration.token.ConfirmationTokenService;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegistrationService {
    
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private final ProfesorService profesorService;


    public String register(RegistrationRequest request) {
    boolean isValidEmail = emailValidator.test(request.getEmail());
    if (!isValidEmail) {
        throw new IllegalStateException("Email no válido.");
    }

    // Validación manual del DNI si es alumno
    if (request.getRole() == AppUserRole.ALUMNO && request.getDni() == null) {
        throw new IllegalArgumentException("El DNI es obligatorio para alumnos.");
    }

    AppUser user;

    switch (request.getRole()) {
        case ALUMNO:
            if (request.getDni() == null) {
                throw new IllegalArgumentException("El DNI es obligatorio para alumnos.");
            }
            user = new Alumno(
                request.getNombre(),
                request.getApellido(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.ALUMNO,
                request.getDni()
            );
            break;

        case PROFESOR:
            Profesor profesor = new Profesor(
                request.getNombre(),
                request.getApellido(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.PROFESOR,
                request.getMateriaDada(),
                request.getCarrera()
            );

            String tokenProfesor = appUserService.signUpUser(profesor);
            profesorService.saveProfesor(profesor);
            String linkProfesor = "http://localhost:8080/api/v1/registration/confirm?token=" + tokenProfesor;
            emailSender.send(request.getEmail(), buildEmail(request.getNombre(), linkProfesor));
            return tokenProfesor;

        case ADMIN:
            user = new AppUser(
                request.getNombre(),
                request.getApellido(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.ADMIN
            );
            break;

        default:
            throw new IllegalArgumentException("Rol no válido: " + request.getRole());
    }

    String token = appUserService.signUpUser(user);
    String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
    emailSender.send(request.getEmail(), buildEmail(request.getNombre(), link));

    return token;
}


    
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }
    
    private String buildEmail(String name, String link) {
        if (name == null || name.isBlank()) {
        name = "there"; // un fallback para que no quede null
        }
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
    
}
