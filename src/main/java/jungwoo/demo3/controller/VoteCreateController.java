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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "VoteCreateController", value = "/Vote/create")
@Log4j2
public class VoteCreateController extends HttpServlet {

    private VoteService voteService = VoteService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("/vote/create GET .......");
        req.getRequestDispatcher("/WEB-INF/vote/create.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        VoteDTO voteDTO = VoteDTO.builder()
                .title(req.getParameter("title"))
                .build();

        log.info("/feed/create POST...");
        log.info(voteDTO);
        try {
            voteService.createVote(voteDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/Vote/list");

    }
}