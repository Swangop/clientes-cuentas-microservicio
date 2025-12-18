package com.banco.domain.model;

import java.util.Objects;

/**
 * Entidad de dominio CuentaBancaria.
 * Representa una cuenta bancaria asociada a un cliente.
 */
public class CuentaBancaria {

    private Long id;
    private String dniCliente;
    private String tipoCuenta;
    private Double total;

    public CuentaBancaria() {
    }

    public CuentaBancaria(Long id, String dniCliente, String tipoCuenta, Double total) {
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
        CuentaBancaria that = (CuentaBancaria) o;
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
        return "CuentaBancaria{" +
                "id=" + id +
                ", dniCliente='" + dniCliente + '\'' +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", total=" + total +
                '}';
    }

    // Builder pattern
    public static CuentaBancariaBuilder builder() {
        return new CuentaBancariaBuilder();
    }

    public static class CuentaBancariaBuilder {
        private Long id;
        private String dniCliente;
        private String tipoCuenta;
        private Double total;

        public CuentaBancariaBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CuentaBancariaBuilder dniCliente(String dniCliente) {
            this.dniCliente = dniCliente;
            return this;
        }

        public CuentaBancariaBuilder tipoCuenta(String tipoCuenta) {
            this.tipoCuenta = tipoCuenta;
            return this;
        }

        public CuentaBancariaBuilder total(Double total) {
            this.total = total;
            return this;
        }

        public CuentaBancaria build() {
            return new CuentaBancaria(id, dniCliente, tipoCuenta, total);
        }
    }
}
