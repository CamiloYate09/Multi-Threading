package com.example.prueba.repositorio;

//import com.example.prueba.modelo.Departamento;

import com.example.prueba.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>  {



}
