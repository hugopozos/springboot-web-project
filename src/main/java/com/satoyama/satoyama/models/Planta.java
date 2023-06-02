package com.satoyama.satoyama.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_plantas") // Declaramos que vamos a usar la tabla usuarios
@Data
public class Planta {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //Genera valores unicos de clave primaria de una entidad
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_area")
    private Area area;

}