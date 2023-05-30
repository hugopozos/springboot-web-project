package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Area;
import com.satoyama.satoyama.models.Huerto;

import java.util.List;

public interface AreaDao {

    List<Area> getAreas(Long id);
    List<Area> getmiArea(Long id);

    void registrarArea(Area area);

    void eliminar(Long id);
}
