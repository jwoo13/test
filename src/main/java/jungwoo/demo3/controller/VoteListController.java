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
import java.util.List;

@Log4j2
@WebServlet(name = "VoteListController", value = "/Vote/list")
public class VoteListController extends HttpServlet {

    private final VoteService voteService = VoteService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("투표 리스트");

        try {
            List<VoteDTO> dtoList = voteService.listAll();
            req.setAttribute("VoteList", dtoList);
            req.getRequestDispatcher("/WEB-INF/vote/list.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("투표 오류" + e.getMessage(), e);
            throw new ServletException("리스트 오류", e);
        }
    }


}