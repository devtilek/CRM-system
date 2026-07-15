package practice.springcrm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest {
    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Неккоректный формат email")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;
}
