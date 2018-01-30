package com.wangzhi.sync.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeFilter implements Filter {

    private String[] excludedMapping;

    public void init(FilterConfig filterConfig) throws ServletException {
        excludedMapping = filterConfig.getInitParameter("excluded-mapping").split(",");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        boolean isExcludedMapping = false;
        String servletPath = request.getServletPath();
        for(String url : excludedMapping) {
            if(servletPath.startsWith(url)) {
                isExcludedMapping = true;
                break;
            }
        }
        if(isExcludedMapping) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/home");
        }
    }

    public void destroy() {

    }
}
