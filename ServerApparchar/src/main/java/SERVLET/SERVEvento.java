/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLET;

import DAO.postgresqlImpDAO.FacadeFactory;
import Entidades.Categoria;
import Entidades.Cliente;
import Entidades.Empresa;
import Entidades.EmpresaPK;
import Entidades.Evento;
import Entidades.EventoPK;
import Entidades.Lugar;
import MODELO.CalificacionM;
import MODELO.CategoriaM;
import MODELO.EventoM;
import MODELO.EventoPKM;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class SERVEvento extends HttpServlet {

    /*
      System.out.println("se recibio: " + insertar);
                Gson myGson = new Gson();
                Evento myEvento = (Evento) myGson.fromJson(insertar, Evento.class);
                System.out.println(myEvento.toString());
                EventoPK myEventoPK = new EventoPK();
                myEventoPK.setFecha(myEvento.getEventoPK().getFecha());
                myEventoPK.setHoraFinal(horaf);
                myEventoPK.setHoraInicio(horai);
                myEventoPK.setIdevento(23);
                myEvento.setEventoPK(myEventoPK);
                System.out.println("......................................................");
                System.out.println("todo el objeto " + myEvento.toString());
                boolean resultado = FacadeFactory.getFacade().insertarGenerico(myEvento);
                System.out.println("resultado " + resultado);
                JsonObject o = new JsonObject();
                o.addProperty("respuesta", myGson.toJson(resultado));
                out.write(o.toString());
                out.print(o.toString());
                System.out.println("se envio: " + o.toString());
     */
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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String insertar = "";
            String listar = "";
            String horai = "";
            String horaf = "";
            System.out.println("horai " + horai);
            System.out.println("horaf " + horaf);
            insertar = request.getParameter("insertar");
            horai = request.getParameter("horai");
            horaf = request.getParameter("horaf");
            listar = request.getParameter("listar");
            if (insertar != null) {
                System.out.println("se recibio: " + insertar);
                Gson myGson = new Gson();
                Evento myEvento = (Evento) myGson.fromJson(insertar, Evento.class);
                Evento eventico = new Evento();
                System.out.println(myEvento.toString());
                EventoPK myEventoPK = new EventoPK();
                myEventoPK.setFecha(myEvento.getEventoPK().getFecha());
                myEventoPK.setHoraFinal(horaf);
                myEventoPK.setHoraInicio(horai);

                eventico.setEventoPK(myEventoPK);
                ArrayList<Categoria> myCategoria = (ArrayList<Categoria>) myEvento.getCategoriaCollection();

                ArrayList<Empresa> myEmpresa = (ArrayList<Empresa>) myEvento.getEmpresaCollection();
                ArrayList<Empresa> n = new ArrayList<>();
                System.out.println("myEmpresa " + myEmpresa.size());
                System.out.println("categorias " + myCategoria.size());
                for (int i = 0; i < myEmpresa.size(); i++) {
                    Empresa e = myEmpresa.get(i);
                    Empresa m = new Empresa();
                    EmpresaPK r = new EmpresaPK();
                    m.setEmpresaPK(r);
                    String nit = e.getEmpresaPK().getNitempresa();
                    String v = nit.substring(1, nit.length() - 1);
                    r.setNitempresa(v);
                    r.setUsuario(e.getEmpresaPK().getUsuario());
                    System.out.println("sdfdsf " + v + e.getEmpresaPK().getUsuario());
                    n.add(m);
                }
                eventico.setCategoriaCollection(myCategoria);
                eventico.setEmpresaCollection(n);
                eventico.setDireccion(myEvento.getDireccion());
                eventico.setDescripcion(myEvento.getDescripcion());
                eventico.setFoto(myEvento.getFoto());
                eventico.setNombre(myEvento.getNombre());

                System.out.println("......................................................");
                System.out.println("todo el objeto " + myEvento.toString());
                boolean resultado = FacadeFactory.getFacade().insertaEvento(eventico);
                System.out.println("resultado " + resultado);
                JsonObject o = new JsonObject();
                o.addProperty("respuesta", myGson.toJson(resultado));
                out.write(o.toString());
                out.print(o.toString());
                System.out.println("se envio: " + o.toString());
            } else if (listar != null) {
                System.out.println("se recibio: " + listar);
                Gson myGson = new Gson();
                Evento myEvento = new Evento();
                List<Evento> resultado = FacadeFactory.getFacade().listarGenerico(myEvento);
                System.out.println("resultado " + resultado.toString());
                ArrayList<EventoM> eventos = new ArrayList<>();
                for (int i = 0; i < resultado.size(); i++) {
                    EventoM n = new EventoM();
                    Evento b = resultado.get(i);
                    EventoPKM eventoPK = new EventoPKM();
                    eventoPK.setFecha(b.getEventoPK().getFecha());
                    eventoPK.setHoraFinal(b.getEventoPK().getHoraFinal());
                    eventoPK.setHoraInicio(b.getEventoPK().getHoraInicio());
                    eventoPK.setIdevento(b.getEventoPK().getIdevento());
                    n.setNombre(b.getNombre());
                    n.setEventoPK(eventoPK);
                    n.setFoto(b.getFoto());
                    eventos.add(n);
                }

                JsonObject o = new JsonObject();
                o.addProperty("respuesta", myGson.toJson(eventos));
                out.write(o.toString());
                out.print(o.toString());
                System.out.println("se envio: " + o.toString());
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SERVEvento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SERVEvento.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SERVEvento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SERVEvento.class.getName()).log(Level.SEVERE, null, ex);
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
