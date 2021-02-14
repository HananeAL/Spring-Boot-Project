package com.project.main.filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.main.models.User;

import org.springframework.web.filter.OncePerRequestFilter;

public class LoginFilter extends OncePerRequestFilter {

  private static final String[] exceptions = {"/candidate/signup", "/company/signup"};

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("user");
    if (user == null) {
      response.sendRedirect("/signin");
    } 
    else {
      filterChain.doFilter(request, response);
    }
  }


  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

    String url = request.getRequestURI();
    for (String e : exceptions) {
      if (e.equals(url))
        return true;
    }
    return false;
  }

}
