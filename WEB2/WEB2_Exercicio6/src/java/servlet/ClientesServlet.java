/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.Cliente;
import bean.LoginBean;
import dao.ClientesDAO;
import facade.ClientesFacade;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "ClientesServlet", urlPatterns = {"/ClientesServlet"})
public class ClientesServlet extends HttpServlet {

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
        
        ClientesFacade clientesFacade = new ClientesFacade();
        
        HttpSession session = request.getSession(false);
        String msg;
        String stDate;
        
        int idCliente;
        Cliente cliente;
        Date dt;
        DateFormat formatter;
        List<Cliente> clientes;
        
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
        
        String action = (String)request.getParameter("action");
        
        if(action.equals("list")){
            clientes = new ArrayList<>();
            clientes = clientesFacade.findAll();
            request.setAttribute("clientes", clientes);
            rd = getServletContext().getRequestDispatcher("/clientesListar.jsp");
            rd.forward(request, response);
        }else if(action.equals("show")){
            idCliente = Integer.parseInt((String)request.getParameter("id"));
            cliente = new Cliente();
            cliente = clientesFacade.getById(idCliente);
            request.setAttribute("cliente", cliente);
            rd = getServletContext().getRequestDispatcher("/clientesVisualizar.jsp");
            rd.forward(request, response);
        }else if(action.equals("formUpdate")){
            idCliente = Integer.parseInt((String)request.getParameter("id"));
            cliente = new Cliente();
            cliente = clientesFacade.getById(idCliente);
            request.setAttribute("cliente", cliente);
            rd = getServletContext().getRequestDispatcher("/clientesAlterar.jsp");
            rd.forward(request, response);
        }else if(action.equals("remove")){
            idCliente = Integer.parseInt((String)request.getParameter("id"));
            clientesFacade.delete(idCliente);
            rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=list");
            rd.forward(request, response);
        }else if(action.equals("update")){
            cliente = new Cliente();
            cliente.setIdCliente(Integer.valueOf(request.getParameter("idCliente")));
            cliente.setNomeCliente(request.getParameter("nomeCliente"));
            cliente.setCpfCliente(request.getParameter("cpfCliente"));
            cliente.setEmailCliente(request.getParameter("emailCliente"));

            //DATA
            stDate = request.getParameter("dataCliente");
            dt = null;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dt = (Date)formatter.parse(stDate);
            } catch (ParseException ex) {
                Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            cliente.setDataCliente(dt);

            cliente.setRuaCliente(request.getParameter("ruaCliente"));
            cliente.setNumCliente(Integer.parseInt(request.getParameter("numCliente")));
            cliente.setCepCliente(request.getParameter("cepCliente"));
            cliente.setCidadeCliente(request.getParameter("cidadeCliente"));
            cliente.setUfCliente(request.getParameter("ufCliente"));

            clientesFacade.update(cliente);

            rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=list");
            rd.forward(request, response);
        }else if(action.equals("formNew")){
            rd = getServletContext().getRequestDispatcher("/clientesNovo.jsp");
            rd.forward(request, response);
        }else if(action.equals("new")){
            cliente = new Cliente();
            cliente.setNomeCliente(request.getParameter("nomeCliente"));
            cliente.setCpfCliente(request.getParameter("cpfCliente"));
            cliente.setEmailCliente(request.getParameter("emailCliente"));

            //DATA
            stDate = request.getParameter("dataCliente");
            dt = null;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dt = (Date)formatter.parse(stDate);
            } catch (ParseException ex) {
                Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            cliente.setDataCliente(dt);

            cliente.setRuaCliente(request.getParameter("ruaCliente"));
            cliente.setNumCliente(Integer.parseInt(request.getParameter("numCliente")));
            cliente.setCepCliente(request.getParameter("cepCliente"));
            cliente.setCidadeCliente(request.getParameter("cidadeCliente"));
            cliente.setUfCliente(request.getParameter("ufCliente"));

            clientesFacade.save(cliente);

            rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=list");
            rd.forward(request, response);
        }else{
            msg = "Usuário deve se autenticar para acessar o sistema.";
            request.setAttribute("msg", msg);
            rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
        
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
