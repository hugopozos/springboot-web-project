package com.satoyama.satoyama.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_huerto") // Declaramos que vamos a usar la tabla usuarios
@Data
public class Huerto {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //Genera valores unicos de clave primaria de una entidad
    @Column(name = "codigo_huerto")
    private Long codigoHuerto;

    @Column(name = "nombre_huerto")
    private String NombreHuerto;

    @Column(name = "descripcion")
    private String Descripcion;

    @ManyToOne
    @JoinColumn(name = "numero_usuario")
    private Usuario usuario;

}
