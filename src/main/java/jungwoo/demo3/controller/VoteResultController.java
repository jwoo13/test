package jungwoo.demo3.controller;

import jungwoo.demo3.service.VoteService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "VoteResultController", value = "/Vote/result")
@Log4j2
public class VoteResultController extends HttpServlet {

    private final VoteService voteService = VoteService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        try {
            Long id = Long.parseLong(idParam.trim());

            List<Map<String, Object>> optionList = voteService.getVoteCounts(id);

            req.setAttribute("optionList", optionList);
            req.getRequestDispatcher("/WEB-INF/vote/result.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("결과 조회 오류: " + e.getMessage(), e);
            throw new ServletException("결과 조회 중 오류 발생", e);
        }
    }
}