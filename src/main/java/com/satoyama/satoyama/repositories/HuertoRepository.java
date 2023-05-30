package com.satoyama.satoyama.repositories;

import com.satoyama.satoyama.models.Huerto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface HuertoRepository extends JpaRepository<Huerto, Long> {
    List<Huerto> findAllByUsuarioId(Long id);

    List<Huerto> findByCodigoHuerto(Long id);
}
