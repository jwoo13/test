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

@WebServlet(name = "VoteSearchController", value = "/Vote/search")
@Log4j2
public class VoteSearchController extends HttpServlet {

    private final VoteService voteService = VoteService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        log.info("검색 요청: " + keyword);

        try {
            List<VoteDTO> searchResults = voteService.searchVotesByKeyword(keyword);
            req.setAttribute("VoteList", searchResults);
            req.getRequestDispatcher("/WEB-INF/vote/list.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("검색 중 오류 발생: " + e.getMessage(), e);
            throw new ServletException("검색 오류", e);
        }
    }
}