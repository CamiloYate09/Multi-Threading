package com.example.prueba.repositorio;

import com.example.prueba.modelo.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {


    @Query("SELECT u FROM Departamento u WHERE u.nombreDepartamento = ?1")
    Departamento findAllActiveDepartamenteIndex(String nombre);
}
