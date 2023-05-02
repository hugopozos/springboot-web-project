package com.satoyama.satoyama.controllers;

import com.satoyama.satoyama.dao.UsuarioDao;
import com.satoyama.satoyama.models.Usuario;
import com.satoyama.satoyama.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtill;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {

        Map<String, Object> respuesta = new HashMap<>();
        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLogueado != null) {
            String tokenJWT = jwtUtill.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            respuesta.put("id_usuario", usuarioLogueado.getId());
            respuesta.put("tokenJWT", tokenJWT);
            return tokenJWT;
        }
        return "FAIL";
    }

}