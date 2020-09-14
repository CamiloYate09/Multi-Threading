package com.example.prueba.Negocio;

//import com.example.prueba.modelo.Departamento;

import com.example.prueba.modelo.Departamento;
import com.example.prueba.modelo.Empleado;
import com.example.prueba.repositorio.DepartamentoRepository;
import com.example.prueba.repositorio.EmpleadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;


@Service
public class EmpleadoNegocio {


    Logger logger = LoggerFactory.getLogger(EmpleadoNegocio.class);
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Async
    public CompletableFuture<List<Empleado>> crearEmpleados(MultipartFile archivo) throws Exception {

        long start = System.currentTimeMillis();
        List<Empleado> empleadoInsertar = new ArrayList<>();
        List<Empleado> empleadoLeido = parseCSVArchivo(archivo);
        logger.info("Guardar Lista de Usuario con size {}", empleadoLeido.size(), "" + Thread.currentThread().getName());
        for (Empleado empleado : empleadoLeido) {
            Departamento idDepartment = departamentoRepository.findAllActiveDepartamenteIndex(empleado.getNombreDepartamento());
            if (Objects.isNull(idDepartment)) {
                Departamento valor = new Departamento();
                valor.setNombreDepartamento(empleado.getNombreDepartamento());
                departamentoRepository.save(valor);
            }
            Departamento idDepartamentoBusqueda = departamentoRepository.findAllActiveDepartamenteIndex(empleado.getNombreDepartamento());
            if (idDepartamentoBusqueda != null) {
                empleado.setDepartamento(idDepartamentoBusqueda);
                empleadoInsertar.add((Empleado) empleado);
                empleadoInsertar = empleadoRepository.saveAll(empleadoInsertar);
            }
        }

        long end = System.currentTimeMillis();
        logger.info("Tiempo Total {}", (end - start));
        return CompletableFuture.completedFuture(empleadoInsertar);


    }

    @Async
    public CompletableFuture<List<Empleado>> findAllEmpleados() {
        logger.info("Obteniedo lista de todos los empleados" + Thread.currentThread().getName());
        List<Empleado> empleado = empleadoRepository.findAll();
        return CompletableFuture.completedFuture(empleado);
    }

    private List<Empleado> parseCSVArchivo(final MultipartFile archivo) throws Exception {
        final List<Empleado> empleados = new ArrayList<>();
        try {

            try (final BufferedReader br = new BufferedReader(new InputStreamReader(archivo.getInputStream()))) {
                String linea;

                while ((linea = br.readLine()) != null) {
                    final String[] data = linea.split(",");
                    final Empleado empleado1 = new Empleado();
                    Departamento departamento = new Departamento();
                    empleado1.setNombre(data[0]);
                    empleado1.setCargo(data[1]);
                    empleado1.setSalario(Double.valueOf(data[2]));
                    empleado1.setTimepoCompleto(Boolean.valueOf(data[3]));
//                    empleado1.setDepartamento(data[4]);
                    empleado1.setNombreDepartamento(data[4]);
                    empleados.add(empleado1);

                }
                return empleados;
            }


        } catch (final IOException e) {
            logger.error("Fallo la conversion del archivo CSV {}", e);
            throw new Exception("Fallo la conversion del archivo CSV {}", e);
        }

    }


}
