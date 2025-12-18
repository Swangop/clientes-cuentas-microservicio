package com.banco.infrastructure.rest.dto;

import java.util.Objects;

/**
 * DTO para transferencia de datos de CuentaBancaria en la API REST.
 */
public class CuentaBancariaDTO {

    private Long id;
    private String dniCliente;
    private String tipoCuenta;
    private Double total;

    public CuentaBancariaDTO() {
    }

    public CuentaBancariaDTO(Long id, String dniCliente, String tipoCuenta, Double total) {
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
        CuentaBancariaDTO that = (CuentaBancariaDTO) o;
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
        return "CuentaBancariaDTO{" +
                "id=" + id +
                ", dniCliente='" + dniCliente + '\'' +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", total=" + total +
                '}';
    }

    // Builder pattern
    public static CuentaBancariaDTOBuilder builder() {
        return new CuentaBancariaDTOBuilder();
    }

    public static class CuentaBancariaDTOBuilder {
        private Long id;
        private String dniCliente;
        private String tipoCuenta;
        private Double total;

        public CuentaBancariaDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CuentaBancariaDTOBuilder dniCliente(String dniCliente) {
            this.dniCliente = dniCliente;
            return this;
        }

        public CuentaBancariaDTOBuilder tipoCuenta(String tipoCuenta) {
            this.tipoCuenta = tipoCuenta;
            return this;
        }

        public CuentaBancariaDTOBuilder total(Double total) {
            this.total = total;
            return this;
        }

        public CuentaBancariaDTO build() {
            return new CuentaBancariaDTO(id, dniCliente, tipoCuenta, total);
        }
    }
}
