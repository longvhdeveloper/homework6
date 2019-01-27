package my.vlong.java.homework6.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import my.vlong.java.homework6.dto.AccountDTO;
import my.vlong.java.homework6.dto.UserRole;

public class RoleFilter implements Filter {

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        AccountDTO accountDTO = (AccountDTO) httpRequest.getSession().getAttribute("account");
        if (!accountDTO.getUserRole().equals(UserRole.ADMIN)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpRequest.getSession().setAttribute("message", "You don't have permission to access admin area.");
            httpResponse.sendRedirect("/info");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("init role Filter");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy role filter");
    }

}
