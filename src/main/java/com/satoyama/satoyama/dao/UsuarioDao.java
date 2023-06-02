package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    //DAO significa "Data Access Object" (Objeto de Acceso a Datos)

    List<Usuario> getUsuarios();

    void registrar (Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);

    Usuario obtenerUsuarioPorId(long idUsuario);

}
