package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Huerto;
import com.satoyama.satoyama.repositories.HuertoRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Repository
@Transactional
public class HuertoDaoImp implements HuertoDao{

    @PersistenceContext
    EntityManager entityManager; // EntityManager permite interactuar con una base de datos

    @Autowired
    HuertoRepository huertoRepository;

    @Override
    @Transactional
    public List<Huerto> getHuertos(Long id) {
        return huertoRepository.findAllByUsuarioId(id);
    }

    @Override
    @Transactional
    public List<Huerto> getmiHuerto(Long id) {
        return huertoRepository.findByCodigoHuerto(id);
    }

    @Override
    public void registrarHuertos(Huerto huerto) {
        huertoRepository.save(huerto);
    }

    @Override
    public void eliminar(Long id) {
        Huerto huerto = entityManager.find(Huerto.class,id);
        entityManager.remove(huerto);
    }

    @Override
    public Huerto obtenerHuertoporId(Long idHuerto) {return huertoRepository.findById(idHuerto).orElse(null);}

}
