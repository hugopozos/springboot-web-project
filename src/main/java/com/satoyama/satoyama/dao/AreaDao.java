package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Area;
import com.satoyama.satoyama.models.Huerto;

import java.util.List;
import java.util.Optional;

public interface AreaDao {

    List<Area> getAreas(Long id);
    Optional<Area> getmiArea(Long id);

    void registrarArea(Area area);

    void eliminar(Long id);

    Area obtenerAreaporId(Long idArea);
}
