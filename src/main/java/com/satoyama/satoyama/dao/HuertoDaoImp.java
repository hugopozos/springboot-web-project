package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Huerto;
import com.satoyama.satoyama.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class HuertoDaoImp implements HuertoDao{

    @PersistenceContext
    EntityManager entityManager; // EntityManager permite interactuar con una base de datos

    @Override
    @Transactional
    public List<Huerto> getHuertos() {
        String query = "FROM Huerto ";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void registarHuertos(Huerto huerto) {

    }

}
