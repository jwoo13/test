package jungwoo.demo3.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
@Log4j2
public class LogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("log out..................");

        HttpSession session = req.getSession(false); // 세션이 존재할 경우에만 가져오기
        if (session != null) {
            session.removeAttribute("loginInfo"); // 세션에서 로그인 정보 제거
            session.invalidate(); // 세션 무효화
        }

        resp.sendRedirect(req.getContextPath() + "/login"); // 메인 페이지로 리다이렉트

    }
}