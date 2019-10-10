/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.Usuario;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */
@WebServlet(name = "CadastrarUsuarioServlet", urlPatterns = {"/CadastrarUsuarioServlet"})
public class CadastrarUsuarioServlet extends HttpServlet {

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
        
        String msg, page;
        RequestDispatcher rd;
        
        try (PrintWriter out = response.getWriter()) {
            
            Usuario user = new Usuario();
            user.setNome(request.getParameter("nome"));
            user.setLogin(request.getParameter("login"));
            user.setSenha(request.getParameter("senha"));
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
            out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
            out.println("<title>Cadastrar Usuario Servlet</title>");            
            out.println("</head>");
            out.println("<body class=\"text-center\">");
            out.println("<br/><br/>");
            int status = this.saveUser(user);
            if(status > 0){
                out.println("<h1 class=\"text-success\">Usuário cadastrado com sucesso!</h1>");
                out.println("<h5><a href=\"PortalServlet\">PortalServlet</a></h5>");
            }else{
                page = "index.html";
                msg = "Erro ao cadastrar usuário!";
                request.setAttribute("msg", msg);
                request.setAttribute("page", page);
                rd = getServletContext().getRequestDispatcher("/ErroServlet");
                rd.forward(request, response);
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    public int saveUser(Usuario user){
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.save(user);
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
