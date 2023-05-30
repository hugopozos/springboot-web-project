package com.satoyama.satoyama.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_usuarios") // Declaramos que vamos a usar la tabla usuarios
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //Genera valores unicos de clave primaria de una entidad
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

     @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public Usuario () {

    }
    public Usuario (Long id) {
        this.id = id;
        this.nombre = null;
        this.apellido = null;
        this.email = null;
        this.password = null;
    }
}
