package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDaoImp.class);

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
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        LOGGER.debug("Query para obtenerUsuarioPorCredenciales: {}", query);
        LOGGER.debug("Parametros para obtenerUsuarioPorCredenciales: email={}, password={}", usuario.getEmail(), usuario.getPassword());
        LOGGER.debug("Resultados para obtenerUsuarioPorCredenciales: {}", lista);

        if(lista.isEmpty()){
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHashed, usuario.getPassword())){
            return lista.get(0);
        }
        return null;

    }

    public Usuario obtenerUsuarioPorId(long idUsuario) {
        String query = "FROM Usuario WHERE id = :idUsuario";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("idUsuario", idUsuario)
                .getResultList();

        if (!lista.isEmpty()) {
            return lista.get(0);
        }

        return null;
    }


}
