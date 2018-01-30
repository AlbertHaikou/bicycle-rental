package by.haikou.bicycle_rental.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom tag for user greeting depending on the role
 */
public class HelloTag extends TagSupport {
    private String role;
    private String name;

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String to;
            if ("administrator".equalsIgnoreCase(role)) {
                to = "Hello, " + name;
            } else {
                to = "Welcome, " + name;
            }
            pageContext.getOut().write(to);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
