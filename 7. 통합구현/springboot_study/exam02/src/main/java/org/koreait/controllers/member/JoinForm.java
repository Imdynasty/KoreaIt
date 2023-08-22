package org.koreait.controllers.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JoinForm {
    @NotBlank
    private String userId;
    @NotBlank
    private String userPw;
    @NotBlank
    private String userPwRe;
    @NotBlank
    private String userNm;
}