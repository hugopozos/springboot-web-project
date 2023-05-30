package com.satoyama.satoyama.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_huerto") // Declaramos que vamos a usar la tabla usuarios
public class Huerto {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //Genera valores unicos de clave primaria de una entidad
    @Getter @Setter @Column(name = "CodigoHuerto")
    private Long CodigoHuerto;

    @Getter @Setter @Column(name = "NombreHuerto")
    private String NombreHuerto;

    @Getter @Setter @Column(name = "Descripcion")
    private String Descripcion;

    @Getter @Setter @Column(name = "NumeroUsuario")
    private Long NumeroUsuario;

}
