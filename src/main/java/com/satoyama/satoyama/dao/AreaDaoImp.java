package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Area;
import com.satoyama.satoyama.models.Huerto;
import com.satoyama.satoyama.repositories.AreaRepositoy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AreaDaoImp implements AreaDao{

    @PersistenceContext
    EntityManager entityManager; // EntityManager permite interactuar con una base de datos

    @Autowired
    AreaRepositoy areaRepository;

    @Override
    @Transactional
    public List<Area> getAreas(Long id) {
        return areaRepository.findAllByHuertoCodigoHuerto(id);
    }

    @Override
    @Transactional
    public Optional<Area> getmiArea(Long id) {return areaRepository.findById(id);}

    @Override
    public void registrarArea(Area area) { areaRepository.save(area);}

    @Override
    public void eliminar(Long id) {

    }
    @Override
    public Area obtenerAreaporId(Long idArea){return areaRepository.findById(idArea).orElse(null);}
}
