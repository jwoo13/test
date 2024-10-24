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

        log.info("로그아웃");

        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("loginInfo");
            session.invalidate();
        }

        resp.sendRedirect(req.getContextPath() + "/login");

    }
}