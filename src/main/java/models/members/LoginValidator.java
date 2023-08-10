package models.members;

import controllers.member.LoginForm;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class LoginValidator implements Validator {
    private final MemberDao memberDao;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(LoginForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm loginForm = (LoginForm) target;
        String userId = loginForm.getUserId();
        String userPw = loginForm.getUserPw();
        Member member = null;
        //1. 아이디로 회원이 조회 되는지 체크
        if(userId != null && !userId.isBlank()){
            member = memberDao.get(userId);
            if(member == null){
                errors.reject("LoginValidation.fail");
            }
        }


        //2. 입력한 비밀번호와 DB에 있는 해시와 일치하는지
        if(member != null && userPw != null && !userPw.isBlank()){
          boolean matched = BCrypt.checkpw(userPw, member.getUserPw());
          if(!matched){
              errors.reject("LoginValidation.fail");
          }
        }
    }
}
