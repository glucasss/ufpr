/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.Cliente;
import bean.LoginBean;
import dao.ClientesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "AlterarClienteServlet", urlPatterns = {"/AlterarClienteServlet"})
public class AlterarClienteServlet extends HttpServlet {

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
        
        ClientesDAO clientesDao = new ClientesDAO();
        
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
        
        Cliente c = new Cliente();
        c.setIdCliente(Integer.valueOf(request.getParameter("idCliente")));
        c.setNomeCliente(request.getParameter("nomeCliente"));
        c.setCpfCliente(request.getParameter("cpfCliente"));
        c.setEmailCliente(request.getParameter("emailCliente"));
        
        //DATA
        String stDate = request.getParameter("dataCliente");
        Date dt = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = (Date)formatter.parse(stDate);
        } catch (ParseException ex) {
            Logger.getLogger(AlterarClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setDataCliente(dt);
        
        c.setRuaCliente(request.getParameter("ruaCliente"));
        c.setNumCliente(Integer.parseInt(request.getParameter("numCliente")));
        c.setCepCliente(request.getParameter("cepCliente"));
        c.setCidadeCliente(request.getParameter("cidadeCliente"));
        c.setUfCliente(request.getParameter("ufCliente"));
        
        int status = clientesDao.update(c);
        
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
