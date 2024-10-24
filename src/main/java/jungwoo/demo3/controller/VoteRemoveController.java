package jungwoo.demo3.controller;

import jungwoo.demo3.service.VoteService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VoteRemoveController", value = "/Vote/remove")
@Log4j2
public class VoteRemoveController extends HttpServlet {

    private VoteService voteService = VoteService.INSTANCE;



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        log.info("id: " + id);

        try{
            voteService.removeVote(id);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
        resp.sendRedirect("/Vote/list");

    }
}
