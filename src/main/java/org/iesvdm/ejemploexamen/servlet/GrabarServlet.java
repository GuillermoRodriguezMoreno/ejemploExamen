package org.iesvdm.ejemploexamen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.ejemploexamen.dao.ModeloDAO;
import org.iesvdm.ejemploexamen.dao.ModeloDAOImpl;
import org.iesvdm.ejemploexamen.models.Modelo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "GrabarServlet", value = "/GrabarServlet")
public class GrabarServlet extends HttpServlet {

    // DAO
    private ModeloDAO modeloDAO = new ModeloDAOImpl();

    // doGet y doPost
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Redireccion a formulario
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioGrabar.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        Optional<Modelo> optionalModelo = UtilServlet.validaGrabar(request);

        // Si presente
        if (optionalModelo.isPresent()) {

            Modelo modelo = optionalModelo.get();

            // Inserto en BBDD

            this.modeloDAO.create(modelo);

            // Setteo listado
            List<Modelo> listado = this.modeloDAO.getAll();
            request.setAttribute("listado", listado);

            // Setteo id
            request.setAttribute("newID", modelo.getId() );

            // Redireccion a listado
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listado.jsp");

        } else {

            // Si esta Optional vacio
            request.setAttribute("error", "Error de validación!");

            // Redireccion a formuario                                                                   V
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formulario.jsp");
        }

        dispatcher.forward(request,response);
    }
}