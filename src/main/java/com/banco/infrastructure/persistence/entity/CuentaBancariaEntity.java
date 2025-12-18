package com.banco.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Entidad JPA para persistencia de CuentaBancaria.
 */
@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancariaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dni_cliente")
    private String dniCliente;

    @Column(name = "tipo_cuenta")
    private String tipoCuenta;

    private Double total;

    public CuentaBancariaEntity() {
    }

    public CuentaBancariaEntity(Long id, String dniCliente, String tipoCuenta, Double total) {
        this.id = id;
        this.dniCliente = dniCliente;
        this.tipoCuenta = tipoCuenta;
        this.total = total;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public Double getTotal() {
        return total;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CuentaBancariaEntity that = (CuentaBancariaEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dniCliente, that.dniCliente) &&
                Objects.equals(tipoCuenta, that.tipoCuenta) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dniCliente, tipoCuenta, total);
    }

    @Override
    public String toString() {
        return "CuentaBancariaEntity{" +
                "id=" + id +
                ", dniCliente='" + dniCliente + '\'' +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", total=" + total +
                '}';
    }

    // Builder pattern
    public static CuentaBancariaEntityBuilder builder() {
        return new CuentaBancariaEntityBuilder();
    }

    public static class CuentaBancariaEntityBuilder {
        private Long id;
        private String dniCliente;
        private String tipoCuenta;
        private Double total;

        public CuentaBancariaEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CuentaBancariaEntityBuilder dniCliente(String dniCliente) {
            this.dniCliente = dniCliente;
            return this;
        }

        public CuentaBancariaEntityBuilder tipoCuenta(String tipoCuenta) {
            this.tipoCuenta = tipoCuenta;
            return this;
        }

        public CuentaBancariaEntityBuilder total(Double total) {
            this.total = total;
            return this;
        }

        public CuentaBancariaEntity build() {
            return new CuentaBancariaEntity(id, dniCliente, tipoCuenta, total);
        }
    }
}
