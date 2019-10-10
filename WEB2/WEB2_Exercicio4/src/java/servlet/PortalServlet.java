/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.LoginBean;
import bean.Usuario;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "PortalServlet", urlPatterns = {"/PortalServlet"})
public class PortalServlet extends HttpServlet {

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
        
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = new UsuarioDAO().findAll();
        
        // Captura a sessão
        // parametro false: para não criar sessão caso não exista
        HttpSession session = request.getSession(false);
        
        //Redirecionar
        RequestDispatcher rd;
        
        String msg, page;
        
        //Valida se o campo está dentro da session
        if(session == null){
            msg = "Sessão não iniciada, retornar a página inicial";
            page = "index.html";
            request.setAttribute("msg", msg);
            request.setAttribute("page", page);
            rd = getServletContext().getRequestDispatcher("/ErroServlet");
            rd.forward(request, response);
        }else{
            if((LoginBean)session.getAttribute("loginBean") == null){
                msg = "Erro na sessão, favor retornar a página inicial";
                page = "index.html";
                request.setAttribute("msg", msg);
                request.setAttribute("page", page);
                rd = getServletContext().getRequestDispatcher("/ErroServlet");
                rd.forward(request, response);
            }else{
                try (PrintWriter out = response.getWriter()) {
            
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<meta charset=\"utf-8\">");
                    out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
                    out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
                    out.println("<title>PortalServlet</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<br/>");
                    out.println("<h1 class=\"text-center\"> Cadastrar novo usuário </h1>");
                    out.println("<br/>");
                    out.println("<div class=\"container w-25\">");
                    out.println("<form action=\"CadastrarUsuarioServlet\" method=\"post\">");
                    out.println("<div class=\"form-group\">");
                    out.println("<label for=\"inputNome\">Nome</label>");
                    out.println("<input type=\"text\" class=\"form-control\" id=\"inputNome\" name=\"nome\" placeholder=\"ex: João da Silva\"/>");
                    out.println("</div>");
                    out.println("<div class=\"form-group\">");
                    out.println("<label for=\"inputLogin\">Login</label>");
                    out.println("<input type=\"text\" class=\"form-control\" id=\"inputLogin\" name=\"login\" placeholder=\"ex: joaosilva\"/>");
                    out.println("</div>");
                    out.println("<div class=\"form-group\">");
                    out.println("<label for=\"inputNome\">Senha</label>");
                    out.println("<input type=\"password\" class=\"form-control\" id=\"inputSenha\" name=\"senha\"/>");
                    out.println("</div>");
                    out.println("<button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Salvar</button>");
                    out.println("</form>");
                    out.println("<br/>");
                    out.println("</div>");
                    out.println("<hr/>");
                    out.println("<h3 class=\"text-center\">Usuarios</h3>");
                    out.println("<div class=\"container\">");
                    out.println("<table class=\"table table-bordered w-50\" style=\"margin: 0 auto !important;\">");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th class=\"w-50\">Nome</th>");
                    out.println("<th class=\"w-25\">Usuario</th>");
                    out.println("<th class=\"w-25\">Senha</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                    usuarios.forEach(item -> {
                        out.println("<tr>");
                        out.println("<td>"+item.getNome()+"</td>");
                        out.println("<td>"+item.getLogin()+"</td>");
                        out.println("<td>"+item.getSenha()+"</td>");
                    });
                    out.println("</tbody>");
                    out.println("</table>");
                    out.println("</div>");
                    out.println("<br/>");
                    out.println("<h5 class=\"text-center\"><a href=\"LogoutServlet\">Logout</a></h5>");
                    out.println("<br/>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
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
