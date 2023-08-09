package controllers.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginForm {

    @NotBlank
    private String userId;

    @NotBlank
    private String userPw;
}
