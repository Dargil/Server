/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLET;

import DAO.postgresqlImpDAO.FacadeFactory;
import Entidades.Cliente;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeffe
 */
public class SERVCliente extends HttpServlet {

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
            out.println("<title>Servlet ciente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet dfdf at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("Recibiendo Peticion");
        String insertar = "";
        String modificar = "";
        String listar = "";
        String eliminar = "";
        String consultar = "";
        String login = "";

        insertar = request.getParameter("insertar");
        modificar = request.getParameter("modificar");
        listar = request.getParameter("listar");
        eliminar = request.getParameter("eliminar");
        consultar = request.getParameter("consultar");
        login = request.getParameter("login");

        if (insertar != null) {
            System.out.println("se recibio: " + insertar);
            Gson myGson = new Gson();
            Cliente myCliente = (Cliente) myGson.fromJson(insertar, Cliente.class);
            System.out.println(myCliente.toString());
            boolean resultado = FacadeFactory.getFacade().insertarGenerico(myCliente);
            System.out.println("resultado " + resultado);
            JsonObject o = new JsonObject();
            o.addProperty("respuesta", myGson.toJson(resultado));
            out.write(o.toString());
            out.print(o.toString());
            System.out.println("se envio: " + o.toString());
        } else if (login != null) {
            System.out.println("se recibio: " + login);
            Gson myGson = new Gson();
            Cliente myCliente = (Cliente) myGson.fromJson(login, Cliente.class);
            System.out.println(myCliente.toString());
            int resultado = FacadeFactory.getFacade().loginGenerico(myCliente.getUsuario(), myCliente.getContrasenia(), myCliente);
            JsonObject o = new JsonObject();
            o.addProperty("count", myGson.toJson(resultado));
            o.addProperty("tipo", "cliente");
            out.write(o.toString());
            out.print(o.toString());
            System.out.println("se envio: " + o.toString());
        }

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
