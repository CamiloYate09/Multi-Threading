package com.example.prueba.modelo;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50 ,name = "nombre_Departamento", nullable = false)
    private String nombreDepartamento;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "departamento", orphanRemoval = true)
    @JsonManagedReference
    private List<Empleado> departamento = new ArrayList<>();


    public Departamento() {
        super();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public List<Empleado> getDepartamento() {
        return departamento;
    }

    public void setDepartamento(List<Empleado> departamento) {
        this.departamento = departamento;
    }
}
