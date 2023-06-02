package com.satoyama.satoyama.controllers;

import com.satoyama.satoyama.dao.AreaDao;
import com.satoyama.satoyama.dao.HuertoDao;
import com.satoyama.satoyama.models.Area;
import com.satoyama.satoyama.models.Huerto;
import com.satoyama.satoyama.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.*;

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
    public List<Area> getAreas(@PathVariable Long id) {
        return areaDao.getAreas(id);
    }

    @RequestMapping(value = "api/miarea/{id}", method = RequestMethod.GET)
    public Optional<Area> getmiArea(@PathVariable Long id) {
        return areaDao.getmiArea(id);
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
            System.out.println("huerto: "+huerto);
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