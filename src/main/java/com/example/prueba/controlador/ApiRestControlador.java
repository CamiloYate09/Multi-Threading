package com.example.prueba.controlador;


import com.example.prueba.Negocio.EmpleadoNegocio;
import com.example.prueba.modelo.Departamento;
import com.example.prueba.modelo.Empleado;
import com.example.prueba.repositorio.DepartamentoRepository;
import com.example.prueba.repositorio.EmpleadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Rest encargado de servir las solicitudes
 */
@RestController
@RequestMapping("api1")
public class ApiRestControlador {
    Logger logger = LoggerFactory.getLogger(ApiRestControlador.class);

    @Autowired
    private EmpleadoNegocio empleadoNegocio;

    @Autowired
    private EmpleadoRepository empleadoRepository;


    @Autowired
    private DepartamentoRepository departamentoRepository;

    /*
Crear Intereses Compuestos
* */
    @PostMapping(value = "/crearEmpleado", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity crearEmpleado(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        logger.info("Ingreso al método crearEmpleado");

        for (MultipartFile file : files) {
            empleadoNegocio.crearEmpleados(file);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @GetMapping(value = "/todos", produces = "application/json")
    public CompletableFuture<ResponseEntity<List<Empleado>>> findAllEmpleados() {
        return empleadoNegocio.findAllEmpleados().thenApply(ResponseEntity::ok);
    }


    @GetMapping(value = "/salario", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List> empeladosSalario() {
        logger.info("Ingreso al método empeladosSalario");
        ResponseEntity<List> respuesta = null;
        List empleado = empleadoRepository.empleadosSalarioTop();
        logger.info("Ingreso al método empeladosSalario {}", empleado);
        return respuesta.ok().body(empleado);


    }

    @GetMapping(value = "/salarioDeparta", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List> empleadosSalarioDepartamento() {
        logger.info("Ingreso al método empleadosSalarioDepartamento");
        ResponseEntity<List> respuesta = null;
        List empleado = empleadoRepository.empleadosSalarioDepartamento();
        logger.info("Ingreso al método empleadosSalarioDepartamento {}", empleado);
        return respuesta.ok().body(empleado);


    }


    @GetMapping("/departamento/{departamentoId}")
    public Page<Departamento> empleadosDepartamentoID(@PathVariable (value = "departamentoId") Long departamentoId,
                                                     Pageable pageable) {
        logger.info("Ingreso al método empleadosDepartamentoID");
        logger.info("departamentoId {} ",departamentoId);
        return departamentoRepository.empleadosDepartamentoID(departamentoId, pageable);
    }

}
