package jungwoo.demo3.filter;

import lombok.extern.log4j.Log4j2;
import jungwoo.demo3.dto.MemberDTO;
import jungwoo.demo3.service.MemberService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;



@WebFilter(urlPatterns = {"/vote/*"})
@Log4j2
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Login check filter....");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String registerPath = contextPath + "/register";

        log.info("Request URI: " + requestURI);
        log.info("Register Path: " + registerPath);

        if (requestURI.equals(registerPath)) {
            log.info("회원가입 경로는 필터에서 제외됩니다.");
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("loginInfo") == null) {
            log.info("로그인되지 않은 사용자, 로그인 페이지로 리다이렉트합니다.");
            resp.sendRedirect(contextPath + "/login");
            return;
        }

        chain.doFilter(request, response);
    }
}