package com.banco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests de integración para los endpoints REST de Cliente.
 */
@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /clientes - Debe devolver los 5 clientes iniciales")
    void obtenerTodosLosClientes_debeDevolver5Clientes() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[*].dni", containsInAnyOrder(
                        "11111111A", "22222222B", "33333333C", "44444444D", "55555555E")));
    }

    @Test
    @DisplayName("GET /clientes - Debe incluir las cuentas bancarias de cada cliente")
    void obtenerTodosLosClientes_debeIncluirCuentas() throws Exception {
        mockMvc.perform(get("/clientes/11111111A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cuentas", hasSize(2)));
    }

    @Test
    @DisplayName("GET /clientes/mayores-de-edad - Debe devolver solo clientes >= 18 años")
    void obtenerClientesMayoresDeEdad_debeExcluirMenores() throws Exception {
        mockMvc.perform(get("/clientes/mayores-de-edad"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4))) // Elena (2010) es menor
                .andExpect(jsonPath("$[*].dni", not(contains("33333333C"))));
    }

    @Test
    @DisplayName("GET /clientes/con-cuenta-superior-a/{cantidad} - Debe filtrar correctamente")
    void obtenerClientesConCuentaSuperiorA_debeFiltar() throws Exception {
        // Juan tiene 170000 (150000 + 20000)
        // María tiene 120000
        // Raquel tiene 75000
        // Raúl tiene 50300
        // Elena tiene 300
        mockMvc.perform(get("/clientes/con-cuenta-superior-a/100000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].dni", containsInAnyOrder("11111111A", "55555555E")));
    }

    @Test
    @DisplayName("GET /clientes/con-cuenta-superior-a/{cantidad} - Cantidad alta sin resultados")
    void obtenerClientesConCuentaSuperiorA_sinResultados() throws Exception {
        mockMvc.perform(get("/clientes/con-cuenta-superior-a/500000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /clientes/{dni} - Debe devolver cliente existente")
    void obtenerClientePorDni_clienteExistente() throws Exception {
        mockMvc.perform(get("/clientes/11111111A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni").value("11111111A"))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido1").value("Pérez"))
                .andExpect(jsonPath("$.cuentas", hasSize(2)));
    }

    @Test
    @DisplayName("GET /clientes/{dni} - Debe devolver 404 para cliente inexistente")
    void obtenerClientePorDni_clienteNoExiste() throws Exception {
        mockMvc.perform(get("/clientes/99999999Z"))
                .andExpect(status().isNotFound());
    }
}
