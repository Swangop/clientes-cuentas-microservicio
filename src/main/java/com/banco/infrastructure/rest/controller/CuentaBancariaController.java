package com.banco.infrastructure.rest.controller;

import com.banco.domain.port.in.CuentaBancariaUseCase;
import com.banco.infrastructure.rest.dto.ActualizarSaldoRequest;
import com.banco.infrastructure.rest.dto.CrearCuentaRequest;
import com.banco.infrastructure.rest.dto.CuentaBancariaDTO;
import com.banco.infrastructure.rest.mapper.RestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para operaciones de CuentaBancaria.
 */
@RestController
@RequestMapping("/cuentas")
@Tag(name = "Cuentas Bancarias", description = "API para gestión de cuentas bancarias")
public class CuentaBancariaController {

        private final CuentaBancariaUseCase cuentaUseCase;
        private final RestMapper mapper;

        public CuentaBancariaController(CuentaBancariaUseCase cuentaUseCase, RestMapper mapper) {
                this.cuentaUseCase = cuentaUseCase;
                this.mapper = mapper;
        }

        @PostMapping
        @Operation(summary = "Crear nueva cuenta bancaria", description = "Crea una nueva cuenta bancaria. Si el cliente no existe, se crea automáticamente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Cuenta creada correctamente"),
                        @ApiResponse(responseCode = "400", description = "Datos inválidos")
        })
        public ResponseEntity<CuentaBancariaDTO> crearCuenta(@RequestBody CrearCuentaRequest request) {
                var cuenta = mapper.toDomain(request);
                var cuentaCreada = cuentaUseCase.crearCuenta(cuenta);
                return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(cuentaCreada));
        }

        @PutMapping("/{idCuenta}")
        @Operation(summary = "Actualizar saldo de cuenta", description = "Actualiza el saldo de una cuenta bancaria existente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Saldo actualizado correctamente"),
                        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
        })
        public ResponseEntity<CuentaBancariaDTO> actualizarSaldo(
                        @Parameter(description = "ID de la cuenta") @PathVariable Long idCuenta,
                        @RequestBody ActualizarSaldoRequest request) {
                return cuentaUseCase.actualizarSaldo(idCuenta, request.getTotal())
                                .map(mapper::toDTO)
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }
}
