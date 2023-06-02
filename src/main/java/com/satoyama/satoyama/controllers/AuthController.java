package com.satoyama.satoyama.controllers;

import com.satoyama.satoyama.dao.UsuarioDao;
import com.satoyama.satoyama.models.Usuario;
import com.satoyama.satoyama.utils.JWTUtil;
import jakarta.servlet.http.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class AuthController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtill;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {

        Map<String, Object> respuesta = new HashMap<>();
        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLogueado != null) {
            String tokenJWT = jwtUtill.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            respuesta.put("id_usuario", usuarioLogueado.getId());
            respuesta.put("tokenJWT", tokenJWT);

            // Almacenar el token en el HttpServletRequest
            request.setAttribute("tokenJWT", tokenJWT);

            HttpSession session = request.getSession();
            logger.info("Session: {}", session);

            session.setAttribute("tokenJWT", tokenJWT);
            logger.info("Session: {}", session);

            // Mostrar el token en el log
            logger.info("Token JWT almacenado: {}", tokenJWT);
            String KeyToken = jwtUtill.getKey(tokenJWT);
            logger.info("Key Token: {}", KeyToken);

            return tokenJWT;
        }
        return "FAIL";
    }

    @RequestMapping(value = "api/obtenerToken", method = RequestMethod.GET)
    public String obtenerToken(HttpServletRequest request) {
        HttpSession session = request.getSession();
        logger.info("Session: {}", session);
        String token = (String) session.getAttribute("tokenJWT");
        logger.info("token: {}", token);
        if (token != null && jwtUtill.validateToken(token)) {
            String key = jwtUtill.getKey(token);
            logger.info("Token key: {}", key);
            return "Token key: " + key;
        } else {
            return "Invalid token";
        }
    }

}



