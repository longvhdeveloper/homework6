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

public class LoginFilter implements Filter {

    private static final String LOGIN_URL = "/login";

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("init login filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (!isExcludeURL(httpRequest)) {
            AccountDTO accountDTO = (AccountDTO) httpRequest.getSession().getAttribute("account");
            if (null == accountDTO) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendRedirect(LOGIN_URL);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("destory login filter");
    }

    private boolean isExcludeURL(HttpServletRequest httpRequest) {
        return httpRequest.getRequestURL().indexOf(LOGIN_URL) > -1;
    }

}
