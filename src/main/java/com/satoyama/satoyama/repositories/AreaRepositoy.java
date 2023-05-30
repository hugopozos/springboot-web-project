package com.satoyama.satoyama.repositories;

import com.satoyama.satoyama.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AreaRepositoy extends JpaRepository<Area, Long> {

    List<Area> findAllByHuertoCodigoHuerto(Long id);

    Optional<Area> findById(Long id);


}
