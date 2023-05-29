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

    @RequestMapping(value = "api/huertos", method = RequestMethod.GET)
    public List<Huerto> getHuertos(@RequestHeader(value="Authorization") String token) {
        if (!validarToken(token)) { return null; }
        return huertoDao.getHuertos();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @PostMapping("/api/registrarHuertos")
    public void registrarHuertos(@RequestBody Map<String, Object> huertoData) {
        String nombreHuerto = (String) huertoData.get("nombreHuerto");
        String descripcion = (String) huertoData.get("descripcion");
        long idUsuario = Long.parseLong(String.valueOf(huertoData.get("idUsuario")));

        System.out.println("Nombre del Huerto: " + nombreHuerto);
        System.out.println("Descripción: " + descripcion);
        System.out.println("ID del Usuario: " + idUsuario);

        Usuario usuario = usuarioDao.obtenerUsuarioPorId(idUsuario);

        Huerto huerto = new Huerto();
        huerto.setNombreHuerto(nombreHuerto);
        huerto.setDescripcion(descripcion);
        huerto.setNumeroUsuario(usuario.getId());

        huertoDao.registrarHuertos(huerto);
        // Aquí puedes realizar otras acciones con los datos recibidos

        // Por ejemplo, puedes retornar una respuesta al cliente
        // return new ResponseEntity<>("Huerto registrado correctamente", HttpStatus.OK);
    }


}
