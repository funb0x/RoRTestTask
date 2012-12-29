package com.tymchak.utils;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/register.xhtml")
public class RegFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		Principal principal = request.getUserPrincipal();
		if (principal == null) {
			filterChain.doFilter(req, resp);
		} else {
			((HttpServletResponse) resp)
					.sendRedirect("user/main.xhtml");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
