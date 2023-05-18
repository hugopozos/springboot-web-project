package com.satoyama.satoyama.dao;

import com.satoyama.satoyama.models.Huerto;
import com.satoyama.satoyama.models.Usuario;

import java.util.List;

public interface HuertoDao {

    List<Huerto> getHuertos();

    void registarHuertos (Huerto huerto);
}
