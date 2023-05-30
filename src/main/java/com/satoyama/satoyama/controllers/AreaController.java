package com.satoyama.satoyama.controllers;

import com.satoyama.satoyama.dao.AreaDao;
import com.satoyama.satoyama.dao.HuertoDao;
import com.satoyama.satoyama.dao.UsuarioDao;
import com.satoyama.satoyama.models.Area;
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
public class AreaController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private HuertoDao huertoDao;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    public AreaController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "api/areas/{id}", method = RequestMethod.GET)
    public List<Huerto> getAreas(@PathVariable Long id) {
        return huertoDao.getHuertos(id);
    }

    @RequestMapping(value = "api/miarea/{id}", method = RequestMethod.GET)
    public List<Huerto> getmiArea(@PathVariable Long id) {
        return huertoDao.getmiHuerto(id);
    }


    @PostMapping("/api/registrarAreas")
    public ResponseEntity<String> registrarAreas(@RequestBody Map<String, Object> areaData) {
        String nombreArea = (String) areaData.get("nombreArea");
        String descripcion = (String) areaData.get("descripcion");
        long idHuerto = Long.parseLong(String.valueOf(areaData.get("idHuerto")));

        System.out.println("Nombre: " + nombreArea);
        System.out.println("Descripción: " + descripcion);
        System.out.println("ID " + idHuerto);

        Huerto huerto = huertoDao.obtenerHuertoporId(idHuerto);

        if (!Objects.isNull(huerto)) {
            Area area = new Area();
            area.setNombre(nombreArea);
            area.setDescripcion(descripcion);
            area.setHuerto(huerto);
            areaDao.registrarArea(area);
        }

        return new ResponseEntity<>("Area registrado correctamente", HttpStatus.OK);
    }

    @RequestMapping(value = "api/eliminarArea/{id}", method = RequestMethod.DELETE)
    public void eliminarHuerto(@RequestHeader(value="Authorization")
                               @PathVariable Long id) {
        areaDao.eliminar(id);
    }


}