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
import java.util.Arrays;

@WebServlet(name = "VoteCreateController", value = "/Vote/create")
@Log4j2
public class VoteCreateController extends HttpServlet {

    private VoteService voteService = VoteService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("투표 만들기 GET");
        req.getRequestDispatcher("/WEB-INF/vote/create.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String[] options = req.getParameterValues("options");

        VoteDTO voteDTO = VoteDTO.builder()
                .title(title)
                .options(Arrays.asList(options))
                .build();

        log.info("투표 만들기 POST");
        log.info(voteDTO);

        try {
            voteService.createVote(voteDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/Vote/list");
    }
}