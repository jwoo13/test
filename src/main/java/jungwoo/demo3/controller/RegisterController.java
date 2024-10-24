package jungwoo.demo3.controller;

import jungwoo.demo3.dto.MemberDTO;
import jungwoo.demo3.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "RegisterController", value = "/signup")
@Log4j2
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("회원가입 요청 GET");
        req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("회원가입 요청 POST");

        try {
            String mid = req.getParameter("mid");
            String mpw = req.getParameter("mpw");
            String mname = req.getParameter("mname");

            MemberDTO memberDTO = MemberDTO.builder()
                    .mid(mid)
                    .mpw(mpw)
                    .mname(mname)
                    .uuid(UUID.randomUUID().toString())
                    .build();

            MemberService.INSTANCE.register(memberDTO);
            resp.sendRedirect(req.getContextPath() + "/login");

        } catch (Exception e) {
            log.error("회원가입에러", e);
            req.getRequestDispatcher("/WEB-INF/signup-error.jsp").forward(req,resp);
        }
    }
}
