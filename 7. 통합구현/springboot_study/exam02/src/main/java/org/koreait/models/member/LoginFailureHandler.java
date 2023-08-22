package org.koreait.models.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        HttpSession session = request.getSession();
        session.removeAttribute("requiredUserId");
        session.removeAttribute("requiredUserPw");
        session.removeAttribute("loginFail");
        session.removeAttribute("userId");

        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        if (userId == null || userId.isBlank()) {
            session.setAttribute("requiredUserId", "아이디를 입력하세요.");
        }

        if (userPw == null || userPw.isBlank()) {
            session.setAttribute("requiredUserPw", "비밀번호를 입력하세요.");
        }

        if (userId != null && !userId.isBlank() && userPw != null && !userPw.isBlank()) {
            session.setAttribute("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        session.setAttribute("userId", userId);

        // 로그인 실패 -> /member/login
        response.sendRedirect(request.getContextPath() + "/member/login");
    }
}