package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Huerto;
import com.satoyama.satoyama.models.Usuario;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface HuertoDao {

    List<Huerto> getHuertos(Long id);
    List<Huerto> getmiHuerto(Long id);

    void registrarHuertos(Huerto huerto);

    void eliminar(Long id);

    Huerto obtenerHuertoporId(Long idHuerto);
}
