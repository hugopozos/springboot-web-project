package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Huerto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

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
    public void registrarHuertos(Huerto huerto) {
        entityManager.merge(huerto);
    }


}
