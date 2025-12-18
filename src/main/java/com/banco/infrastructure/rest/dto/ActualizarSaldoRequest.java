package com.banco.infrastructure.rest.dto;

import java.util.Objects;

/**
 * DTO para actualizar el saldo de una cuenta bancaria.
 */
public class ActualizarSaldoRequest {

    private Double total;

    public ActualizarSaldoRequest() {
    }

    public ActualizarSaldoRequest(Double total) {
        this.total = total;
    }

    // Getter
    public Double getTotal() {
        return total;
    }

    // Setter
    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ActualizarSaldoRequest that = (ActualizarSaldoRequest) o;
        return Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total);
    }

    @Override
    public String toString() {
        return "ActualizarSaldoRequest{" +
                "total=" + total +
                '}';
    }

    // Builder pattern
    public static ActualizarSaldoRequestBuilder builder() {
        return new ActualizarSaldoRequestBuilder();
    }

    public static class ActualizarSaldoRequestBuilder {
        private Double total;

        public ActualizarSaldoRequestBuilder total(Double total) {
            this.total = total;
            return this;
        }

        public ActualizarSaldoRequest build() {
            return new ActualizarSaldoRequest(total);
        }
    }
}
