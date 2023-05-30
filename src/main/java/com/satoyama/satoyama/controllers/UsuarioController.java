package com.satoyama.satoyama.controllers;

import com.satoyama.satoyama.dao.UsuarioDao;
import com.satoyama.satoyama.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    /* @RestController marca la clase como controlador RESTful que maneja solicitudes HTTP y devuelve respuestas HTTP, JSON o XML
       @Autowired realiza inyeccion de dependencias automaticamente
       @RequestMaping mapea las solicitudes HTTP asignando una URL a ese metodo
     */

    @Autowired
    private UsuarioDao usuarioDao;
    /*@Autowired
    private JWTUtil jwtUtil;  */


    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) {
        // Argon es una dependencia para encriptacion HASH
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);
    }



}
