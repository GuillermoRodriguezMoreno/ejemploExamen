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

@WebServlet(name = "BorrarServlet", value = "/BorrarServlet")
public class BorrarServlet extends HttpServlet {

     // DAO
    private ModeloDAO ModeloDAO = new ModeloDAOImpl();

    // doPost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        int ModeloId = -1;
        boolean valida = true;

        // Válida id
        try {

            ModeloId = Integer.parseInt(request.getParameter("codigo"));

        } catch (Exception e) {

            e.printStackTrace();

            valida = false;
        }

        if (!valida) {

            // Error
            request.setAttribute("error", "Error de validación!");
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listado.jsp");

        } else{

            Optional<Modelo> optionalModelo = ModeloDAO.find(ModeloId);

            // Optional presente
            if (optionalModelo.isPresent()) {

                Modelo modelo = optionalModelo.get();

                // Borro
                this.ModeloDAO.delete(modelo.getId());

                // Cargo listado
                List<Modelo> listado = this.ModeloDAO.getAll();

                request.setAttribute("listado", listado);

                // Id borrado
                request.setAttribute("deletedID", modelo.getId());

                // Redireccion a listado
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listado.jsp");

            } else {

                // Setteo error
                request.setAttribute("error", "Error, Modelo no encontrado!");

                // Redireccion listado
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listado.jsp");
            }

            dispatcher.forward(request, response);
        }
    }
}
