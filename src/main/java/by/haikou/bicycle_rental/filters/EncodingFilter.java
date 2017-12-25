package by.haikou.bicycle_rental.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"},
        initParams = {@WebInitParam(name = "defaultEncoding", value = "UTF-8")})
public class EncodingFilter implements Filter {
    private String defaultEncoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        defaultEncoding = filterConfig.getInitParameter("defaultEncoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(defaultEncoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        defaultEncoding = null;
    }
}
