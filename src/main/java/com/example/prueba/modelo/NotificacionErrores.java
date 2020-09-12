package com.example.prueba.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "NOTIFICACION_ERRORES")
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionErrores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 40 ,name = "TIPO", nullable = false)
    private String tipo;

    @Column(length = 50 ,name = "MENSAJE_ERROR", nullable = false)
    private String mensajeError;

    @Column(length = 50 ,name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGISTRO_ERROR", nullable = false)
    private Date registroError;

}
