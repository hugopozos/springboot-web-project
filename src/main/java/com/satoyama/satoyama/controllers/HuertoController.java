package com.satoyama.satoyama.controllers;

import com.satoyama.satoyama.dao.HuertoDao;
import com.satoyama.satoyama.dao.UsuarioDao;
import com.satoyama.satoyama.models.Huerto;
import com.satoyama.satoyama.models.Usuario;
import com.satoyama.satoyama.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HuertoController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private HuertoDao huertoDao;


    @RequestMapping(value = "api/huertos", method = RequestMethod.GET)
    public List<Huerto> getHuertos(@RequestHeader(value="Authorization") String token) {
        if (!validarToken(token)) { return null; }
        return huertoDao.getHuertos();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }


}
