package controllers.member;

import lombok.Data;

import java.util.List;

@Data
public class JoinForm {
    private String userId;
    private String userPw;
    private String userPwRe;
    private String userNm;
    private String email;
    private String mobile;
    private List<String> hobby;
    private Address addr;
    private boolean agree;
}
