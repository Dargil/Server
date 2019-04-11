/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLET;

import DAO.postgresqlImpDAO.FacadeFactory;
import Entidades.Categoria;
import MODELO.CategoriaM;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeffe
 */
public class SERVCategoria extends HttpServlet {

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
            out.println("<title>Servlet categoria</title>");
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
        if (listar != null) {
            System.out.println("se recibio: " + listar);
            Gson myGson = new Gson();
            Categoria myCategoria = new Categoria();
            List<Categoria> resultado = FacadeFactory.getFacade().listarGenerico(myCategoria);
            System.out.println("resultado " + resultado.toString());
            ArrayList<CategoriaM> categoria = new ArrayList<>();
            for (int i = 0; i < resultado.size(); i++) {
                CategoriaM n = new CategoriaM();
                Categoria b = resultado.get(i);
                n.setId(b.getId());
                n.setNombre(b.getNombre());
                categoria.add(n);
            }

            JsonObject o = new JsonObject();
            o.addProperty("respuesta", myGson.toJson(categoria));
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
