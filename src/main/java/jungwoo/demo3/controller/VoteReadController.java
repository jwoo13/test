package jungwoo.demo3.controller;

import jungwoo.demo3.dto.VoteDTO;
import jungwoo.demo3.service.VoteService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VoteReadController", value = "/Vote/read")
@Log4j2
public class VoteReadController extends HttpServlet {

    private final VoteService voteService = VoteService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        log.info("투표읽기: " + id);

        try {
            VoteDTO voteDTO = voteService.getVote(id);
            log.info("조회된 투표: " + voteDTO);

            req.setAttribute("vote", voteDTO);
            req.getRequestDispatcher("/WEB-INF/vote/read.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("조회오류 " + e.getMessage(), e);
            throw new ServletException("조회오류", e);
        }
    }
}