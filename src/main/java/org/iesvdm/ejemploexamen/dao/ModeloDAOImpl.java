package org.iesvdm.ejemploexamen.dao;

import org.iesvdm.ejemploexamen.models.Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModeloDAOImpl extends AbstractDAOImpl implements ModeloDAO {
    @Override
    public void create(Modelo modelo) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {

            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO modelo (nombre, estatura, edad, localidad) VALUES (?, ? , ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, modelo.getNombre());
            ps.setInt(idx++, modelo.getEstatura());
            ps.setInt(idx++, modelo.getEdad());
            ps.setString(idx++, modelo.getLocalidad());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("INSERT de modelo con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();

            if (rsGenKeys.next())
                modelo.setId(rsGenKeys.getInt(1));

        } catch (SQLException | ClassNotFoundException  e) {

            e.printStackTrace();

        } finally {

            closeDb(conn, ps, rs);
        }
    }

    @Override
    public List<Modelo> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Modelo> listModelo = new ArrayList<>();

        try {

            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM modelo");

            while (rs.next()) {

                Modelo modelo = new Modelo();

                modelo.setId(rs.getInt("modeloID"));
                modelo.setNombre(rs.getString("nombre"));
                modelo.setEstatura(rs.getInt("estatura"));
                modelo.setEdad(rs.getInt("edad"));
                modelo.setLocalidad(rs.getString("localidad"));

                listModelo.add(modelo);
            }

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

        } finally {

            closeDb(conn, s, rs);
        }

        return listModelo;
    }

    @Override
    public Optional<Modelo> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM modelo WHERE modeloID = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                Modelo modelo = new Modelo();

                modelo.setId(rs.getInt("modeloID"));
                modelo.setNombre(rs.getString("nombre"));
                modelo.setEstatura(rs.getInt("estatura"));
                modelo.setEdad(rs.getInt("edad"));
                modelo.setLocalidad(rs.getString("localidad"));

                return Optional.of(modelo);
            }

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

        } finally {

            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }

    @Override
    public void update(Modelo modelo) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = connectDB();

            ps = conn.prepareStatement("UPDATE modelo SET nombre = ?, estatura = ?, edad = ?, localidad = ?  WHERE modeloID = ?");

            int idx = 1;

            ps.setString(idx++, modelo.getNombre());
            ps.setInt(idx++, modelo.getEstatura());
            ps.setInt(idx++, modelo.getEdad());
            ps.setString(idx++, modelo.getLocalidad());
            ps.setInt(idx, modelo.getId());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de modelo con 0 registros actualizados.");

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

        } finally {

            closeDb(conn, ps, rs);
        }
    }

    @Override
    public void delete(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM modelo WHERE modeloID = ?");

            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de modelo con 0 registros eliminados.");

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

        } finally {

            closeDb(conn, ps, rs);
        }
    }
}
