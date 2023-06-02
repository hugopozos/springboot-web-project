package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Area;
import com.satoyama.satoyama.models.Huerto;
import com.satoyama.satoyama.models.Planta;

import java.util.List;

public interface PlantaDao {

    List<Planta> getPlantas(Long id);

    void registrarPlanta(Planta planta);
}
