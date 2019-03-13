/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLET;

import DAO.postgresqlImpDAO.FacadeFactory;
import Utilities.Utils;
import VO.ClienteVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeffe
 */
public class SERVRegister extends HttpServlet {

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
  throws ServletException, IOException, SQLException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
     
            System.out.println("Recibiendo Peticion");
            String insertar = "";
            String modificar = "";
            String listar = "";
            String eliminar = "";
            String consultar = "";

            insertar = request.getParameter("insertar");
            modificar = request.getParameter("modificar");
            listar = request.getParameter("listar");
            eliminar = request.getParameter("eliminar");
            consultar = request.getParameter("consultar");

            if (insertar != null) {
                System.out.println("entra a insertar");
                System.out.println("se recibio: " + insertar);
                Gson myGson=new Gson();
                ClienteVO myCliente = (ClienteVO) myGson.fromJson(insertar, ClienteVO.class);
                System.out.println(myCliente.toString());
                FacadeFactory myFacade = new FacadeFactory();
                myFacade.Conexion();
                System.out.println("---------------------");
                boolean resultado = myFacade.getClienteDAO().insertar(myCliente);
                String a = Utils.toJson(resultado);
                out.write(a);
                out.print(a);
                System.out.println("se envio: " + a);
                myFacade.CerrarConexion();
            } else if (modificar != null) {
                System.out.println("se recibio: " + modificar);
                ClienteVO myCliente = (ClienteVO) Utils.fromJson(modificar, ClienteVO.class);
                FacadeFactory myFacade = new FacadeFactory();
                myFacade.Conexion();
                boolean resultado = myFacade.getClienteDAO().modificar(myCliente);
                String a = Utils.toJson(resultado);
                out.write(a);
                out.print(a);
                System.out.println("se envio: " + a);
                myFacade.CerrarConexion();
            } else if (listar != null) {
                System.out.println("se recibio: " + listar);
                //ClienteVO myCliente = (ClienteVO) Utils.fromJson(listar, ClienteVO.class);
                FacadeFactory myFacade = new FacadeFactory();
                myFacade.Conexion();
                ArrayList<ClienteVO> resultado = myFacade.getClienteDAO().listar();
                System.out.println(resultado.toString());
                String a = Utils.toJson(resultado);
                out.write(a);
                out.print(a);
                System.out.println("se envio: " + a);
                myFacade.CerrarConexion();
            } else if (eliminar != null) {
                System.out.println("se recibio: " + eliminar);
                ClienteVO myCliente = (ClienteVO) Utils.fromJson(eliminar, ClienteVO.class);
                FacadeFactory myFacade = new FacadeFactory();
                myFacade.Conexion();
                boolean resultado = myFacade.getClienteDAO().eliminar(myCliente);
                String a = Utils.toJson(resultado);
                out.write(a);
                out.print(a);
                System.out.println("se envio: " + a);
                myFacade.CerrarConexion();
            } else if (consultar != null) {
                System.out.println("se recibio: " + consultar);
                ClienteVO myCliente = (ClienteVO) Utils.fromJson(consultar, ClienteVO.class);
                FacadeFactory myFacade = new FacadeFactory();
                myFacade.Conexion();
                ClienteVO resultado = myFacade.getClienteDAO().consultar(String.valueOf(myCliente.getId()));
                String a = Utils.toJson(resultado);
                out.write(a);
                out.print(a);
                System.out.println("se envio: " + a);
                myFacade.CerrarConexion();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SERVRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SERVRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SERVRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SERVRegister.class.getName()).log(Level.SEVERE, null, ex);
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
