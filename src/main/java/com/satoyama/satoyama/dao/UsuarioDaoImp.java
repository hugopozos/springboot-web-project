package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

    /* @PersistenceContext es una anotacion que se utiliza para inyectar EntityManager
    *  @Transactional indica que un metodo de una clase debe ser ejecutado dentro de una base de datos
    *  @Repository indica que una clase es un objeto de acceso de datos DAO */

    @PersistenceContext
    EntityManager entityManager; // EntityManager permite interactuar con una base de datos

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    
    @Override
    public void registrar(Usuario usuario) {
        // .merge() Sincroniza el estado de un objeto de entidad con el estado correspondiente en la base de datos
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        return null;
    }
}
