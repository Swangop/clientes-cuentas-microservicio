package com.banco.infrastructure.rest.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * DTO para transferencia de datos de Cliente en la API REST.
 */
public class ClienteDTO {

    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private LocalDate fechaNacimiento;
    private List<CuentaBancariaDTO> cuentas;

    public ClienteDTO() {
    }

    public ClienteDTO(String dni, String nombre, String apellido1, String apellido2,
            LocalDate fechaNacimiento, List<CuentaBancariaDTO> cuentas) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.cuentas = cuentas;
    }

    // Getters
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public List<CuentaBancariaDTO> getCuentas() {
        return cuentas;
    }

    // Setters
    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCuentas(List<CuentaBancariaDTO> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ClienteDTO that = (ClienteDTO) o;
        return Objects.equals(dni, that.dni) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(apellido1, that.apellido1) &&
                Objects.equals(apellido2, that.apellido2) &&
                Objects.equals(fechaNacimiento, that.fechaNacimiento) &&
                Objects.equals(cuentas, that.cuentas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre, apellido1, apellido2, fechaNacimiento, cuentas);
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", cuentas=" + cuentas +
                '}';
    }

    // Builder pattern
    public static ClienteDTOBuilder builder() {
        return new ClienteDTOBuilder();
    }

    public static class ClienteDTOBuilder {
        private String dni;
        private String nombre;
        private String apellido1;
        private String apellido2;
        private LocalDate fechaNacimiento;
        private List<CuentaBancariaDTO> cuentas;

        public ClienteDTOBuilder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public ClienteDTOBuilder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public ClienteDTOBuilder apellido1(String apellido1) {
            this.apellido1 = apellido1;
            return this;
        }

        public ClienteDTOBuilder apellido2(String apellido2) {
            this.apellido2 = apellido2;
            return this;
        }

        public ClienteDTOBuilder fechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public ClienteDTOBuilder cuentas(List<CuentaBancariaDTO> cuentas) {
            this.cuentas = cuentas;
            return this;
        }

        public ClienteDTO build() {
            return new ClienteDTO(dni, nombre, apellido1, apellido2, fechaNacimiento, cuentas);
        }
    }
}
