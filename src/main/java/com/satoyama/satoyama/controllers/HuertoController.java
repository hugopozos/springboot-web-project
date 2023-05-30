package com.satoyama.satoyama.controllers;

import com.satoyama.satoyama.dao.HuertoDao;
import com.satoyama.satoyama.dao.UsuarioDao;
import com.satoyama.satoyama.models.Huerto;
import com.satoyama.satoyama.models.Usuario;
import com.satoyama.satoyama.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class HuertoController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private HuertoDao huertoDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    public HuertoController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "api/huertos/{id}", method = RequestMethod.GET)
    public List<Huerto> getHuertos(@PathVariable Long id) {
        return huertoDao.getHuertos(id);
    }


    @PostMapping("/api/registrarHuertos")
    public ResponseEntity<String> registrarHuertos(@RequestBody Map<String, Object> huertoData) {
        String nombreHuerto = (String) huertoData.get("nombreHuerto");
        String descripcion = (String) huertoData.get("descripcion");
        long idUsuario = Long.parseLong(String.valueOf(huertoData.get("idUsuario")));

        System.out.println("Nombre del Huerto: " + nombreHuerto);
        System.out.println("Descripción: " + descripcion);
        System.out.println("ID del Usuario: " + idUsuario);

        Usuario usuario = usuarioDao.obtenerUsuarioPorId(idUsuario);

        if (!Objects.isNull(usuario)) {
            Huerto huerto = new Huerto();
            huerto.setNombreHuerto(nombreHuerto);
            huerto.setDescripcion(descripcion);
            huerto.setUsuario(usuario);

            huertoDao.registrarHuertos(huerto);
        }

        return new ResponseEntity<>("Huerto registrado correctamente", HttpStatus.OK);
    }


}
