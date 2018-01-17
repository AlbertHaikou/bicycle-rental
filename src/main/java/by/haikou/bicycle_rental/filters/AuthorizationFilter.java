package by.haikou.bicycle_rental.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList("/css", "/js", "/img", "login", "register", "/login.jsp", "/registration.jsp",
                    "changeLocale")
            )
    );
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        String path = req.getRequestURI();
        String command = req.getParameter("command");
        if (null == command) {
            command = "";
        }
        if (session.getAttribute("user") == null && !isPathAllowedAnonymously(path) && !isPathAllowedAnonymously(command)) {
            req.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isPathAllowedAnonymously(String path) {
        for (String allowedPath : ALLOWED_PATHS) {
            if (path.startsWith(allowedPath)) {
                return true;
            }
        }
        return false;
    }
}
