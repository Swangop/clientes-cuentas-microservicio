package com.banco.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad JPA para persistencia de Cliente.
 */
@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    private String dni;

    private String nombre;
    private String apellido1;
    private String apellido2;
    private LocalDate fechaNacimiento;

    public ClienteEntity() {
    }

    public ClienteEntity(String dni, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ClienteEntity that = (ClienteEntity) o;
        return Objects.equals(dni, that.dni) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(apellido1, that.apellido1) &&
                Objects.equals(apellido2, that.apellido2) &&
                Objects.equals(fechaNacimiento, that.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre, apellido1, apellido2, fechaNacimiento);
    }

    @Override
    public String toString() {
        return "ClienteEntity{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }

    // Builder pattern
    public static ClienteEntityBuilder builder() {
        return new ClienteEntityBuilder();
    }

    public static class ClienteEntityBuilder {
        private String dni;
        private String nombre;
        private String apellido1;
        private String apellido2;
        private LocalDate fechaNacimiento;

        public ClienteEntityBuilder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public ClienteEntityBuilder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public ClienteEntityBuilder apellido1(String apellido1) {
            this.apellido1 = apellido1;
            return this;
        }

        public ClienteEntityBuilder apellido2(String apellido2) {
            this.apellido2 = apellido2;
            return this;
        }

        public ClienteEntityBuilder fechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public ClienteEntity build() {
            return new ClienteEntity(dni, nombre, apellido1, apellido2, fechaNacimiento);
        }
    }
}
