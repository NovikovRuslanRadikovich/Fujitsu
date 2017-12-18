package com.fujitsu.fs.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/registration")
public class RegistrationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
         HttpServletRequest request = (HttpServletRequest) servletRequest;
         HttpServletResponse response = (HttpServletResponse) servletResponse;


         if(request.getSession().getAttribute("username") != null) {
             response.sendRedirect("/home");
         } else {
             chain.doFilter(servletRequest,servletResponse);
         }


    }

    @Override
    public void destroy() {

    }
}
