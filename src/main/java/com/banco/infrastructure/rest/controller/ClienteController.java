package com.banco.infrastructure.rest.controller;

import com.banco.domain.port.in.ClienteUseCase;
import com.banco.infrastructure.rest.dto.ClienteDTO;
import com.banco.infrastructure.rest.mapper.RestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operaciones de Cliente.
 */
@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "API para gestión de clientes")
public class ClienteController {

        private final ClienteUseCase clienteUseCase;
        private final RestMapper mapper;

        public ClienteController(ClienteUseCase clienteUseCase, RestMapper mapper) {
                this.clienteUseCase = clienteUseCase;
                this.mapper = mapper;
        }

        @GetMapping
        @Operation(summary = "Obtener todos los clientes", description = "Devuelve la lista de todos los clientes con sus cuentas bancarias")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida correctamente")
        })
        public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
                return ResponseEntity.ok(
                                mapper.toClienteDTOList(clienteUseCase.obtenerTodosLosClientes()));
        }

        @GetMapping("/mayores-de-edad")
        @Operation(summary = "Obtener clientes mayores de edad", description = "Devuelve la lista de clientes mayores de 18 años")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de clientes mayores de edad obtenida correctamente")
        })
        public ResponseEntity<List<ClienteDTO>> obtenerClientesMayoresDeEdad() {
                return ResponseEntity.ok(
                                mapper.toClienteDTOList(clienteUseCase.obtenerClientesMayoresDeEdad()));
        }

        @GetMapping("/con-cuenta-superior-a/{cantidad}")
        @Operation(summary = "Obtener clientes con cuentas superiores a cantidad", description = "Devuelve los clientes cuya suma de cuentas supera la cantidad indicada")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida correctamente")
        })
        public ResponseEntity<List<ClienteDTO>> obtenerClientesConCuentaSuperiorA(
                        @Parameter(description = "Cantidad mínima") @PathVariable Double cantidad) {
                return ResponseEntity.ok(
                                mapper.toClienteDTOList(clienteUseCase.obtenerClientesConCuentaSuperiorA(cantidad)));
        }

        @GetMapping("/{dni}")
        @Operation(summary = "Obtener cliente por DNI", description = "Devuelve el cliente con el DNI indicado y todas sus cuentas")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
                        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
        })
        public ResponseEntity<ClienteDTO> obtenerClientePorDni(
                        @Parameter(description = "DNI del cliente") @PathVariable String dni) {
                return clienteUseCase.obtenerClientePorDni(dni)
                                .map(mapper::toDTO)
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }
}
