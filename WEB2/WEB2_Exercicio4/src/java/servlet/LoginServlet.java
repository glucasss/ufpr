/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.LoginBean;
import dao.LoginDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lucas
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String login = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        HttpSession session;
        String msg, page;
        RequestDispatcher rd;
        
        LoginBean loginBean = this.validaLogin(login, password);
        
        if(this.validaCampoNull(login, password) 
        && this.validaCampoVazio(login, password)
        && loginBean.getLoginUsuario() != null){
                
            // Armazena o usuário na sessão
            session = request.getSession();
            session.setAttribute("loginBean", loginBean);
                
            // Redireciona para PortalServlet
            response.sendRedirect(request.getServletContext().getContextPath()+"/portal.jsp");
            
        }else{
            
            msg = "Usuário/senha não encontrado!";
            page = "index.html";
            request.setAttribute("msg", msg);
            request.setAttribute("page", page);
            rd = getServletContext().getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
    
    public LoginBean validaLogin(String login, String senha){
        return new LoginDAO().findByUsernameAndPassword(login, senha);
    }
    
    public boolean validaCampoVazio(String login, String senha){
        return !(login.equals("") || senha.equals(""));
    }
    
    public boolean validaCampoNull(String login, String senha){
        return !(login == null || senha == null);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
