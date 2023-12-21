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

@WebServlet(name = "EditarServlet", value = "/EditarServlet")
public class EditarServlet extends HttpServlet {

    // DAO
    private ModeloDAO modeloDAO = new ModeloDAOImpl();

    // doGet y doPost
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean valido = true;
        int id = -1;
        String error = null;

        // Validacion
        try {

            id = Integer.parseInt(request.getParameter("codigo"));

        }catch (Exception e){

            e.printStackTrace();

            valido = false;
            error = "error en la validacion";
        }

        // Si id es valido
        if(valido){

            Optional<Modelo> modeloOptional = modeloDAO.find(id);

            // Si se encuentra Modelo
            if (modeloOptional.isPresent()){

                // inserto datos en request
                Modelo modelo = modeloOptional.get();

                request.setAttribute("modelo", modelo);

            } else{

                error = "Modelo no encontrado";
            }
        }

        // hay error
        if(error != null){

            request.setAttribute("error", error);
        }

        // Redireccion
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEditar.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        Optional<Modelo> optionalModelo = UtilServlet.validaEditar(request);

        // Si esta presente
        if (optionalModelo.isPresent()) {

            Modelo Modelo = optionalModelo.get();

            // Actualizar
            this.modeloDAO.update(Modelo);

            // Setteo listado
            List<Modelo> listado = this.modeloDAO.getAll();

            request.setAttribute("listado", listado);

            // Setteo ID
            request.setAttribute("updatedID", Modelo.getId() );

            // Redireccion
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listado.jsp");
            dispatcher.forward(request,response);

        } else {

            this.doGet(request, response);
        }
    }
}
