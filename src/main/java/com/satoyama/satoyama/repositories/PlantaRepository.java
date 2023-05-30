package com.satoyama.satoyama.repositories;
import com.satoyama.satoyama.models.Huerto;
import com.satoyama.satoyama.models.Planta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantaRepository extends JpaRepository<Planta, Long> {

    List<Planta> findAllByAreaId(Long id);

    //List<Planta> findByCodigoHuerto(Long id);
}
