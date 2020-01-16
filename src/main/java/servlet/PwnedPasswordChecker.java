/**
 * PwnedPassword checker
 * Uses third-party service https://haveibeenpwned.com/Passwords
 *
 * @author nick
 */

package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.PwnedPasswordService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/checkpass")
public class PwnedPasswordChecker extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(PwnedPasswordChecker.class);

    private PwnedPasswordService service;

    @Override
    public void init() {
        service = new PwnedPasswordService();
    }

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        LOGGER.info("[!] Access to GET-method denied. Use POST-method.");
        throw new IOException("Use POST-method, Luke!");
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        processRequest(httpServletRequest, httpServletResponse);
    }

    private void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        String passToCheck = httpServletRequest.getParameter("password");
        int count = service.getCountOfHashedPassword(passToCheck);
        if (count != 0) {
            httpServletRequest.setAttribute("count", String.valueOf(count));
            httpServletRequest.getRequestDispatcher("found.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletRequest.getRequestDispatcher("notfound.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}