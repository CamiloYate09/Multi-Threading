package com.example.prueba.repositorio;

import com.example.prueba.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>  {


    @Query("SELECT u.id FROM Departamento u WHERE u.nombreDepartamento = ?1 ")
    Optional<Empleado> findAllActiveDepartamente(String nombre);

}
