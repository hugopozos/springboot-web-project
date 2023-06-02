package com.satoyama.satoyama.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_areas") // Declaramos que vamos a usar la tabla usuarios
@Data
public class Area {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //Genera valores unicos de clave primaria de una entidad
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String Descripcion;

    @ManyToOne
    @JoinColumn(name = "codigo_huerto")
    private Huerto huerto;

    public Area () {

    }
    public Area (Long idArea) {
        this.id = idArea;
        this.nombre = null;
        this.huerto = null;
    }

}