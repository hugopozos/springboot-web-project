package com.satoyama.satoyama.repositories;

import com.satoyama.satoyama.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepositoy extends JpaRepository<Area, Long> {

    List<Area> findAreaByHuerto_CodigoHuerto(Long id);


}
