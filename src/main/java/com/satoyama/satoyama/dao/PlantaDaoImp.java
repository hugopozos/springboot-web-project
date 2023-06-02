package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Area;
import com.satoyama.satoyama.models.Huerto;
import com.satoyama.satoyama.models.Planta;
import com.satoyama.satoyama.repositories.HuertoRepository;
import com.satoyama.satoyama.repositories.PlantaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PlantaDaoImp implements PlantaDao{

    @PersistenceContext
    EntityManager entityManager; // EntityManager permite interactuar con una base de datos

    @Autowired
    PlantaRepository plantaRepository;


    @Override
    @Transactional
    public List<Planta> getPlantas(Long id) {
        return plantaRepository.findAllByAreaId(id);
    }


    @Override
    public void registrarPlanta(Planta planta) {
        plantaRepository.save(planta);
    }



}
