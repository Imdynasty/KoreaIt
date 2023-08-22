package org.koreait.models.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();
        session.removeAttribute("requiredUserId");
        session.removeAttribute("requiredUserPw");
        session.removeAttribute("loginFail");
        session.removeAttribute("userId");

        /** 편의 회원 정보 처리 */
        MemberInfo memberInfo = (MemberInfo)authentication.getPrincipal();
        session.setAttribute("memberInfo", memberInfo);

        response.sendRedirect(request.getContextPath() + "/");
    }
}