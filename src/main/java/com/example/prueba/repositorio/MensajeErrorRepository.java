package com.example.prueba.repositorio;

import com.example.prueba.modelo.NotificacionErrores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Interfaz que nos pemrite por medio del Objecto de com.example.prueba.modelo tener todas las
 * operaciones CRUD de persistencia.
 */

@Repository
public interface MensajeErrorRepository extends JpaRepository<NotificacionErrores, Long> {
}
