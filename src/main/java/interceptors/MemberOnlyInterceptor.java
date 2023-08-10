package interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.members.Member;
import org.springframework.web.servlet.HandlerInterceptor;

public class MemberOnlyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        HttpSession session = request.getSession();
        Member member =(Member) session.getAttribute("member");
        if(member!=null){
            return true;//컨트롤러 실행
        }
        //회원전용페이지에 미로그인 상태->로그인 페이지 이동
        response.sendRedirect(request.getContextPath() + "/member/login");

        return false;//컨트롤러 실행 x
    }
}
