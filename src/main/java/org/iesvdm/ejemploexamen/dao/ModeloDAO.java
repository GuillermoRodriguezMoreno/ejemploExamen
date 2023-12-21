package org.iesvdm.ejemploexamen.dao;


import org.iesvdm.ejemploexamen.models.Modelo;

import java.util.List;
import java.util.Optional;

public interface ModeloDAO {

    public void create(Modelo modelo);

    public List<Modelo> getAll();
    public Optional<Modelo> find(int id);

    public void update(Modelo modelo);

    public void delete(int id);
}
