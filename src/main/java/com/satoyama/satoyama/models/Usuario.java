package com.satoyama.satoyama.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_usuarios") // Declaramos que vamos a usar la tabla usuarios
public class Usuario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //Genera valores unicos de clave primaria de una entidad
    @Getter @Setter @Column(name = "id")
    private Long id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "password")
    private String password;
}
