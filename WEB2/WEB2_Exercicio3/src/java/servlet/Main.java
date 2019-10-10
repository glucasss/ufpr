/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */
@WebServlet(name = "Main", urlPatterns = {"/Main"})
public class Main extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercicio 1</title>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            out.println("<br/>");
            out.println("<h1 class=\"text-center text-primary\">Setor de Educação Profissional e Tecnológica</h1>");
            out.println("<small><p class=\"text-center\"><strong>Endereço:</strong> R. Dr. Alcides Vieira Arcoverde, 1225 - Jardim das Américas, Curitiba - PR, 81520-260</p></small>");
            out.println("<table class=\"table table-bordered w-75\" style=\"margin: 0 auto !important;\">");
            out.println("<thead><tr>");
            out.println("<th class=\"w-75\">Curso</th>");
            out.println("<th class=\"w-25\">Página</th>");
            out.println("</tr></thead>");
            out.println("<tbody>");
            out.println("<tr><td>");
            out.println("Técnologo em Análise e Desenvolvimento de Sistemas</td>");
            out.println("<td><a href=\"https://www.tads.ufpr.br\" target=\"_blank\">https://www.tads.ufpr.br</a></td></tr>");
            out.println("<tr><td>");
            out.println("Produção Cênica</td>");
            out.println("<td><a href=\"http://www.tpc.ufpr.br\" target=\"_blank\">http://www.tpc.ufpr.br</a></td></tr>");
            out.println("<tr><td>");
            out.println("Agente Comunitário de Saúde</td>");
            out.println("<td><span class=\"font-italic\">sem link</span></td></tr>");
            out.println("<tr><td>");
            out.println("Gestão Pública</td>");
            out.println("<td><a href=\"http://www.gestaopublica.ufpr.br\">http://www.gestaopublica.ufpr.br</a></td></tr>");
            out.println("<tr><td>");
            out.println("Secretariado</td>");
            out.println("<td><span class=\"font-italic\">sem link</span></td></tr>");
            out.println("<tr><td>");
            out.println("Gestão de Qualidade</td>");
            out.println("<td><a href=\"http://www.gestaodaqualidade.ufpr.br/\" target=\"_blank\">http://www.gestaodaqualidade.ufpr.br/</a></td><tr/>");
            out.println("<tr><td>");
            out.println("Negócios Imobiliários</td>");
            out.println("<td><a href=\"http://www.tni.ufpr.br/\" target=\"_blank\">http://www.tni.ufpr.br/</a></td></tr>");
            out.println("<tr><td>");
            out.println("Petróleo e Gás</td>");
            out.println("<td><span class=\"font-italic\">sem link</span></td></tr>");
            out.println("<tr><td>");
            out.println("Especialização em Engenharia de Software</td>");
            out.println("<td><a href=\"http://www.engenhariadesoftware.ufpr.br\" target=\"_blank\">http://www.engenhariadesoftware.ufpr.br</a></td></tr>");
            out.println("<tr><td>");
            out.println("Pós-Graduação em Bioinformática</td>");
            out.println("<td><a href=\"http://www.bioinfo.ufpr.br/\" target=\"http://www.bioinfo.ufpr.br/\">http://www.bioinfo.ufpr.br/</a></td></tr>");
            out.println("<tr><td>");
            out.println("Comunicação Institucional</td>");
            out.println("<td><a href=\"http://200.17.200.17/comunicacaoinstitucional.html\" target=\"_blank\">http://200.17.200.17/comunicacaoinstitucional.html</a></td></tr>");
            out.println("<tr><td>");
            out.println("Luteria</td>");
            out.println("<td><a href=\"http://www.luteria.ufpr.br/portal/\" target=\"_blank\">http://www.luteria.ufpr.br/portal/</a></td></tr>");
            out.println("<tr><td>");
            out.println("Especialização em Inteligência Artificial</td>");
            out.println("<td><a href=\"http://www.iaa.ufpr.br/\" target=\"_blank\">http://www.iaa.ufpr.br/</a></td></tr>");
            out.println("</tbody></table><br/>");
            out.println("<h6 class=\"text-center\"><a href=\""+request.getContextPath()+"/MeuTADS.jsp\">Meu TADS</a></h6>");
            out.println("</div>");
            out.println("<br/>");
            out.println("</body>");
            out.println("</html>");
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
