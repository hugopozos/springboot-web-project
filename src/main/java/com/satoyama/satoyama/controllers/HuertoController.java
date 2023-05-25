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

    @PostMapping("/registrarHuertos")
    public ResponseEntity<String> registrarHuertos(@RequestBody Huerto huerto, HttpServletRequest request) {
        // Obtener el token de autorización del encabezado de la solicitud
        String authorizationHeader = request.getHeader("Authorization");

        // Verificar si el token existe y comienza con "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Eliminar "Bearer " del encabezado

            // Validar el token y obtener el ID del usuario
            int userId = jwtUtil.getUserId(token);
            if (userId != -1) {
                // Realizar las operaciones de registro de huertos aquí

                // Mostrar el ID del usuario en el debugger
                System.out.println("ID del usuario: " + userId);

                // Retornar una respuesta exitosa
                return ResponseEntity.ok("Huerto guardado correctamente");
            }
        }

        // Si el token es inválido o no se proporciona, retornar una respuesta de error
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error al guardar huerto");
    }
}
