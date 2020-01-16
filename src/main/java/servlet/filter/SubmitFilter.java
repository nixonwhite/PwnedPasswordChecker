/**
 * PwnedPassword checker
 * Uses third-party service https://haveibeenpwned.com/Passwords
 *
 * @author nick
 */

package servlet.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/checkpass")
public class SubmitFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("[>] Filter start");
    }

    @Override
    public void destroy() {
        LOGGER.info("[<] Filter end");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String passToCheck = servletRequest.getParameter("password");
        if (StringUtils.isBlank(passToCheck)) {
            servletRequest.getRequestDispatcher("nullrequest.jsp").forward(servletRequest, servletResponse);
            LOGGER.info("[*] Filter done");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
