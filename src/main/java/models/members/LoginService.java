package models.members;

import controllers.member.LoginForm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class LoginService {
    
    private final LoginValidator loginValidator;
    private final HttpSession session;
    private final MemberDao memberDao;
    private final HttpServletResponse response;
    
    public void login(LoginForm loginForm, Errors errors){
        loginValidator.validate(loginForm,errors);
        if(errors.hasErrors()){//기본 검증 실패시 
            return;
        }
        
        //검증 성공시 로그인 처리
        String userId = loginForm.getUserId();
        Member member = memberDao.get(userId);
        session.setAttribute("member",member);

        //아이디 저장 처리
        Cookie cookie = new Cookie("saveId", userId);
        if(loginForm.isSaveId()){//쿠키저장
            cookie.setMaxAge(60*60*24*365);
        }else{//쿠키제거
            cookie.setMaxAge(0);
        }

        response.addCookie(cookie);
    }
}
