package my.vlong.java.homework6.servlet;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import my.vlong.java.homework6.dto.AccountDTO;
import my.vlong.java.homework6.dto.UserRole;
import my.vlong.java.homework6.exception.LoginException;
import my.vlong.java.homework6.request.LoginRequest;
import my.vlong.java.homework6.service.LoginService;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private RequestDispatcher view;
    private final LoginService loginService;

    public LoginServlet() {
        loginService = new LoginService();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        view = request.getRequestDispatcher("login.jsp");
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(request.getParameter("email"));
        loginRequest.setPassword(request.getParameter("password"));
        System.out.println(loginRequest);
        try {
            Optional<AccountDTO> accountOptional = loginService.login(loginRequest);
            if (accountOptional.isPresent()) {
                AccountDTO accountDTO = accountOptional.get();
                request.getSession().setAttribute("account", accountDTO);

                if (accountDTO.getUserRole().equals(UserRole.ADMIN)) {
                    response.sendRedirect("/admin/info");
                } else if (accountDTO.getUserRole().equals(UserRole.STAFF)) {
                    response.sendRedirect("/info");
                }

            } else {
                request.setAttribute("message", "Login is failed. Please try again.");
            }
        } catch (LoginException ex) {
            request.setAttribute("message", ex.getMessage());
        }
        request.setAttribute("error", true);
        view = request.getRequestDispatcher("login.jsp");
        view.forward(request, response);
    }

}
