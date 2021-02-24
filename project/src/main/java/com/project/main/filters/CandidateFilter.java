package com.project.main.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.main.controllers.UserType;
import com.project.main.models.User;

import org.springframework.web.filter.OncePerRequestFilter;

public class CandidateFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (!UserType.isCandidate(user)) {
      response.sendRedirect("/signin"); // ! to be changed
    } 
    else {
      filterChain.doFilter(request, response);
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return "/candidate/signup".equals(request.getRequestURI());
  }

}
