package org.iesvdm.ejemploexamen.servlet;

import jakarta.servlet.http.HttpServletRequest;
import org.iesvdm.ejemploexamen.models.Modelo;

import java.util.Objects;
import java.util.Optional;

public class UtilServlet {

    public static Optional<Modelo> validaGrabar(HttpServletRequest request) {

        String nombre = null;
        int estatura = -1;
        int edad = -1;
        String localidad = null;
        String flag = null;

        try {

            flag = "nombre";

            Objects.requireNonNull(request.getParameter("nombre"));

            if (request.getParameter("nombre").isBlank())
                throw new RuntimeException("Parámetro vacío o todo espacios blancos.");

            nombre = request.getParameter("nombre");

            flag = "estatura";

            estatura = Integer.parseInt(request.getParameter("estatura"));

            flag = "edad";

            edad = Integer.parseInt(request.getParameter("edad"));

            flag = "localidad";

            Objects.requireNonNull(request.getParameter("localidad"));

            if (request.getParameter("localidad").isBlank())
                throw new RuntimeException("Parámetro vacío o todo espacios blancos.");

            localidad = request.getParameter("localidad");

            return Optional.of(new Modelo(-1, nombre, estatura, edad, localidad));

        } catch (Exception ex) {

            ex.printStackTrace();
            request.setAttribute("error", "error de validadcion en el campo " + flag);

            return Optional.empty();
        }
    }

    public static Optional<Modelo> validaEditar(HttpServletRequest request){

        // Valido ID de Modelo
        int ModeloID = -1;
        boolean valido = true;
        Optional<Modelo> modeloOptional = Optional.empty();

        try {

            ModeloID = Integer.parseInt(request.getParameter("codigo"));

            // valido resto parametros
            modeloOptional = validaGrabar(request);

        }catch (Exception e){

            e.printStackTrace();
            valido = false;

            // Setteo error de id
            request.setAttribute("error", "error en la validacion de ID");
        }

        // Si ID y Modelo son correctos
        if(valido && modeloOptional.isPresent()){

            // obtengo Modelo
            Modelo modelo = modeloOptional.get();
            // Setteo id
            modelo.setId(ModeloID);
            // Vuelvo a envolver
            modeloOptional = Optional.of(modelo);

            return modeloOptional;

        }else{

            return modeloOptional;
        }
    }
}
