/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.LoginBean;
import dao.ClientesDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "RemoverClienteServlet", urlPatterns = {"/RemoverClienteServlet"})
public class RemoverClienteServlet extends HttpServlet {

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
        
        RequestDispatcher rd;
        
        HttpSession session = request.getSession(false);
        String msg;
        
        if(session == null){
            msg = "Usuário deve se autenticar para acessar o sistema.";
            request.setAttribute("msg", msg);
            rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }else{
            if((LoginBean)session.getAttribute("loginBean") == null){
                msg = "Usuário deve se autenticar para acessar o sistema.";
                request.setAttribute("msg", msg);
                rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        }
        
        int idCliente = Integer.parseInt((String)request.getParameter("id"));
        
        ClientesDAO clienteDao = new ClientesDAO();
        int result = clienteDao.delete(idCliente);
        
        rd = getServletContext().getRequestDispatcher("/ClientesServlet");
        rd.forward(request, response);
        
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
