package com.satoyama.satoyama.controllers;

import com.satoyama.satoyama.dao.AreaDao;
import com.satoyama.satoyama.dao.PlantaDao;
import com.satoyama.satoyama.models.Area;
import com.satoyama.satoyama.models.Huerto;
import com.satoyama.satoyama.models.Planta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
public class PlantaController {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private PlantaDao plantaDao;

    @RequestMapping(value = "api/plantas/{id}", method = RequestMethod.GET)
    public List<Planta> getPlantas(@PathVariable Long id) {
        return plantaDao.getPlantas(id);
    }


    @PostMapping("/api/registrarPlantas")
    public ResponseEntity<String> registrarPlantas(@RequestBody Map<String, Object> plantaData) {
        String plantatxt = (String) plantaData.get("planta");
        long idArea = Long.parseLong(String.valueOf(plantaData.get("idArea")));

        System.out.println("plantaData: "+ plantaData);
        System.out.println("Nombre: " + plantatxt);
        System.out.println("ID " + idArea);

        Area area = areaDao.obtenerAreaporId(idArea);

        if (!Objects.isNull(area)) {
            Planta planta = new Planta();
            planta.setNombre(plantatxt);
            planta.setArea(area);
            System.out.println("area: "+area);
            plantaDao.registrarPlanta(planta);
        }

        return new ResponseEntity<>("Planta registrado correctamente", HttpStatus.OK);
    }
}
