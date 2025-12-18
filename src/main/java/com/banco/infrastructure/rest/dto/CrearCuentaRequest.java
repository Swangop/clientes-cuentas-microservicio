package com.banco.infrastructure.rest.dto;

import java.util.Objects;

/**
 * DTO para crear una nueva cuenta bancaria.
 */
public class CrearCuentaRequest {

    private String dniCliente;
    private String tipoCuenta;
    private Double total;

    public CrearCuentaRequest() {
    }

    public CrearCuentaRequest(String dniCliente, String tipoCuenta, Double total) {
        this.dniCliente = dniCliente;
        this.tipoCuenta = tipoCuenta;
        this.total = total;
    }

    // Getters
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
        CrearCuentaRequest that = (CrearCuentaRequest) o;
        return Objects.equals(dniCliente, that.dniCliente) &&
                Objects.equals(tipoCuenta, that.tipoCuenta) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dniCliente, tipoCuenta, total);
    }

    @Override
    public String toString() {
        return "CrearCuentaRequest{" +
                "dniCliente='" + dniCliente + '\'' +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", total=" + total +
                '}';
    }

    // Builder pattern
    public static CrearCuentaRequestBuilder builder() {
        return new CrearCuentaRequestBuilder();
    }

    public static class CrearCuentaRequestBuilder {
        private String dniCliente;
        private String tipoCuenta;
        private Double total;

        public CrearCuentaRequestBuilder dniCliente(String dniCliente) {
            this.dniCliente = dniCliente;
            return this;
        }

        public CrearCuentaRequestBuilder tipoCuenta(String tipoCuenta) {
            this.tipoCuenta = tipoCuenta;
            return this;
        }

        public CrearCuentaRequestBuilder total(Double total) {
            this.total = total;
            return this;
        }

        public CrearCuentaRequest build() {
            return new CrearCuentaRequest(dniCliente, tipoCuenta, total);
        }
    }
}
