package com.example.prueba.Negocio;

import com.example.prueba.exception.ResourceNotFoundException;
import com.example.prueba.modelo.Departamento;
import com.example.prueba.modelo.Empleado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.prueba.repositorio.EmpleadoRepository;

import java.util.Optional;

public class EmpleadoNegocio {


    Logger logger = LoggerFactory.getLogger(EmpleadoNegocio.class);
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Empleado crearEmpleado(Empleado empleado) throws ResourceNotFoundException {

        Empleado empleadoNuevo = null;
        Departamento departamento = null;

        if (empleado != null && empleado.getNombre() != null && empleado.getCargo() != null && empleado.getTimepoCompleto()
                && empleado.getSalario() != null && empleado.getDepartamento() != null
        ) {
            Optional<Empleado> actual = empleadoRepository.findAllActiveDepartamente(empleado.getDepartamento().getNombreDepartamento());
            if(actual == null){
                empleadoNuevo.setNombre(empleado.getNombre());
                empleadoNuevo.setCargo(empleado.getCargo());
                empleadoNuevo.setTimepoCompleto(empleado.getTimepoCompleto());
                empleadoNuevo.setSalario(empleado.getSalario());
                departamento.setNombreDepartamento(empleado.getDepartamento().getNombreDepartamento() );
                empleadoNuevo.setDepartamento(departamento);

                return empleadoNuevo;
            }


        }else {

            throw new ResourceNotFoundException("Ingrese los valores" );
        }


        return empleadoNuevo;
    }

}
