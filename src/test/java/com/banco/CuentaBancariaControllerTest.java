package com.banco;

import com.banco.infrastructure.rest.dto.ActualizarSaldoRequest;
import com.banco.infrastructure.rest.dto.CrearCuentaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests de integración para los endpoints REST de CuentaBancaria.
 */
@SpringBootTest
@AutoConfigureMockMvc
class CuentaBancariaControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        @DisplayName("POST /cuentas - Debe crear cuenta para cliente existente")
        void crearCuenta_clienteExistente() throws Exception {
                CrearCuentaRequest request = CrearCuentaRequest.builder()
                                .dniCliente("11111111A")
                                .tipoCuenta("NORMAL")
                                .total(5000.0)
                                .build();

                mockMvc.perform(post("/cuentas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.dniCliente").value("11111111A"))
                                .andExpect(jsonPath("$.tipoCuenta").value("NORMAL"))
                                .andExpect(jsonPath("$.total").value(5000.0))
                                .andExpect(jsonPath("$.id").exists());
        }

        @Test
        @DisplayName("POST /cuentas - Debe crear cuenta y cliente nuevo")
        void crearCuenta_clienteNuevo() throws Exception {
                CrearCuentaRequest request = CrearCuentaRequest.builder()
                                .dniCliente("99999999Z")
                                .tipoCuenta("PREMIUM")
                                .total(10000.0)
                                .build();

                mockMvc.perform(post("/cuentas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.dniCliente").value("99999999Z"))
                                .andExpect(jsonPath("$.tipoCuenta").value("PREMIUM"))
                                .andExpect(jsonPath("$.total").value(10000.0));

                // Verificar que el cliente fue creado
                mockMvc.perform(get("/clientes/99999999Z"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.dni").value("99999999Z"));
        }

        @Test
        @DisplayName("PUT /cuentas/{idCuenta} - Debe actualizar saldo de cuenta existente")
        void actualizarSaldo_cuentaExistente() throws Exception {
                ActualizarSaldoRequest request = ActualizarSaldoRequest.builder()
                                .total(180000.0)
                                .build();

                mockMvc.perform(put("/cuentas/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.total").value(180000.0));
        }

        @Test
        @DisplayName("PUT /cuentas/{idCuenta} - Debe devolver 404 para cuenta inexistente")
        void actualizarSaldo_cuentaNoExiste() throws Exception {
                ActualizarSaldoRequest request = ActualizarSaldoRequest.builder()
                                .total(100.0)
                                .build();

                mockMvc.perform(put("/cuentas/9999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isNotFound());
        }
}
