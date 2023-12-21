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

@WebServlet(name = "ListarServlet", value = "/ListarServlet")
public class ListarServlet extends HttpServlet {

    // DAO
    private ModeloDAO modeloDAO = new ModeloDAOImpl();

    // doGet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Redireccion a listado
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listado.jsp");

        // Obtengo listado
        List<Modelo> listado = this.modeloDAO.getAll();

        // Setteo listado
        request.setAttribute("listado", listado);

        // Redireccion
        dispatcher.forward(request, response);

    }

}
