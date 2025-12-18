package com.banco.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entidad de dominio Cliente.
 * Representa un cliente del banco con sus datos personales.
 */
public class Cliente {

    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private LocalDate fechaNacimiento;
    private List<CuentaBancaria> cuentas = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String dni, String nombre, String apellido1, String apellido2,
            LocalDate fechaNacimiento, List<CuentaBancaria> cuentas) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.cuentas = cuentas != null ? cuentas : new ArrayList<>();
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

    public List<CuentaBancaria> getCuentas() {
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

    public void setCuentas(List<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }

    /**
     * Calcula la edad del cliente basándose en la fecha de nacimiento.
     * 
     * @return edad en años
     */
    public int getEdad() {
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }

    /**
     * Verifica si el cliente es mayor de edad (>= 18 años).
     * 
     * @return true si es mayor de edad
     */
    public boolean esMayorDeEdad() {
        LocalDate hoy = LocalDate.now();
        LocalDate fechaMayoriaEdad = fechaNacimiento.plusYears(18);
        return !hoy.isBefore(fechaMayoriaEdad);
    }

    /**
     * Calcula el total de todas las cuentas del cliente.
     * 
     * @return suma total de los saldos de todas las cuentas
     */
    public Double getTotalCuentas() {
        return cuentas.stream()
                .mapToDouble(CuentaBancaria::getTotal)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni) &&
                Objects.equals(nombre, cliente.nombre) &&
                Objects.equals(apellido1, cliente.apellido1) &&
                Objects.equals(apellido2, cliente.apellido2) &&
                Objects.equals(fechaNacimiento, cliente.fechaNacimiento) &&
                Objects.equals(cuentas, cliente.cuentas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre, apellido1, apellido2, fechaNacimiento, cuentas);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", cuentas=" + cuentas +
                '}';
    }

    // Builder pattern
    public static ClienteBuilder builder() {
        return new ClienteBuilder();
    }

    public static class ClienteBuilder {
        private String dni;
        private String nombre;
        private String apellido1;
        private String apellido2;
        private LocalDate fechaNacimiento;
        private List<CuentaBancaria> cuentas = new ArrayList<>();

        public ClienteBuilder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public ClienteBuilder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public ClienteBuilder apellido1(String apellido1) {
            this.apellido1 = apellido1;
            return this;
        }

        public ClienteBuilder apellido2(String apellido2) {
            this.apellido2 = apellido2;
            return this;
        }

        public ClienteBuilder fechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public ClienteBuilder cuentas(List<CuentaBancaria> cuentas) {
            this.cuentas = cuentas != null ? cuentas : new ArrayList<>();
            return this;
        }

        public Cliente build() {
            return new Cliente(dni, nombre, apellido1, apellido2, fechaNacimiento, cuentas);
        }
    }
}
