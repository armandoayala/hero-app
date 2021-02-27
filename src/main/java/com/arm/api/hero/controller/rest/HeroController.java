package com.arm.api.hero.controller.rest;

import com.arm.api.hero.aop.AuditableOperationRestApi;
import com.arm.api.hero.controller.BaseController;
import com.arm.api.hero.model.dto.request.CreateHeroReq;
import com.arm.api.hero.model.dto.request.UpdateHeroReq;
import com.arm.api.hero.model.dto.response.CacheableHeroResp;
import com.arm.api.hero.model.dto.response.CreateHeroResp;
import com.arm.api.hero.model.dto.response.GenericResp;
import com.arm.api.hero.model.dto.response.GetAllHeroesResp;
import com.arm.api.hero.model.dto.response.GetHeroeByIdResp;
import com.arm.api.hero.model.dto.response.UpdateHeroResp;
import com.arm.api.hero.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hero")
public class HeroController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(HeroController.class);

    @Autowired
    private HeroService service;

    @Secured({"ROLE_OPERADOR", "ROLE_ADMIN"})
    @AuditableOperationRestApi(
            operation = "Create hero"
    )
    @ApiOperation(value = "Crea un héroe", nickname = "create", notes = "Crea un héroe", response = CreateHeroResp.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Operación exitosa",
                    response = CreateHeroResp.class
            ),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @PostMapping
    public ResponseEntity<Object> create(@ApiParam(required = true, name = "req", value = "Parámetros para crear el héroe")
                                         @RequestBody CreateHeroReq req) {
        GenericResp genericResp = new GenericResp();
        try {
            CreateHeroResp result = service.create(req);
            genericResp.setResult(result);

            return new ResponseEntity<>(genericResp, HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error("Exception at create: " + ex.getMessage(), ex);
            return processException(ex);
        }
    }

    @Secured({"ROLE_OPERADOR", "ROLE_ADMIN"})
    @AuditableOperationRestApi(
            operation = "Update hero"
    )
    @ApiOperation(value = "Actualiza un héroe", nickname = "update", notes = "Actualiza un héroe", response = UpdateHeroResp.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Operación exitosa",
                    response = UpdateHeroResp.class
            ),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @ApiParam(required = true, name = "id", value = "Identificador de héroe a actualizar")
            @PathVariable("id") long id,
            @ApiParam(required = true, name = "req", value = "Valores para actualizar el héroe")
            @RequestBody UpdateHeroReq req) {
        GenericResp genericResp = new GenericResp();
        try {
            CacheableHeroResp result = service.update(id, req);
            genericResp.setResult(result);

            return new ResponseEntity<>(genericResp, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Exception at update: " + ex.getMessage(), ex);
            return processException(ex);
        }
    }

    @Secured({"ROLE_ADMIN"})
    @AuditableOperationRestApi(
            operation = "Delete hero by id"
    )
    @ApiOperation(value = "Elimina un héroe", nickname = "delete", notes = "Elimina un héroe por id")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message = "Operación exitosa"
            ),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @ApiParam(required = true, name = "id", value = "Identificador de héroe a eliminar")
            @PathVariable("id") long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            logger.error("Exception at delete: " + ex.getMessage(), ex);
            return processException(ex);
        }
    }

    @Secured({"ROLE_OPERADOR", "ROLE_ADMIN"})
    @AuditableOperationRestApi(
            operation = "Get all heroes"
    )
    @ApiOperation(value = "Obtiene héroes", nickname = "getAll", notes = "Obtiene todos los héroes", response = GetAllHeroesResp.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Operación exitosa",
                    response = GetAllHeroesResp.class
            ),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping
    public ResponseEntity<Object> getAll() {
        GenericResp genericResp = new GenericResp();
        try {
            GetAllHeroesResp result = service.getAll();
            genericResp.setResult(result);
            return new ResponseEntity<>(genericResp, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Exception at getAll: " + ex.getMessage(), ex);
            return processException(ex);
        }
    }

    @Secured({"ROLE_OPERADOR", "ROLE_ADMIN"})
    @AuditableOperationRestApi(
            operation = "Get hero by id"
    )
    @ApiOperation(value = "Obtiene un héroe por id", nickname = "getById", notes = "Obtiene un héroe por id", response = GetHeroeByIdResp.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Operación exitosa",
                    response = GetHeroeByIdResp.class
            ),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(
            @ApiParam(required = true, name = "id", value = "Identificador de héroe a obtener")
            @PathVariable("id") long id) {
        GenericResp genericResp = new GenericResp();
        try {
            CacheableHeroResp result = service.getById(id);
            genericResp.setResult(result);
            return new ResponseEntity<>(genericResp, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Exception at getById: " + ex.getMessage(), ex);
            return processException(ex);
        }
    }

    @Secured({"ROLE_OPERADOR", "ROLE_ADMIN"})
    @AuditableOperationRestApi(
            operation = "Filter hero by name"
    )
    @ApiOperation(value = "Obtiene héroes por nombre", nickname = "getByName", notes = "Obtiene héroes que contengan en su nombre el valor del parámetro name", response = GetAllHeroesResp.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Operación exitosa",
                    response = GetAllHeroesResp.class
            ),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping("/filter")
    public ResponseEntity<Object> getByName(
            @ApiParam(required = true, name = "name", value = "Nombre para filtrar héroes")
            @RequestParam(value = "name") String name) {
        GenericResp genericResp = new GenericResp();
        try {
            GetAllHeroesResp result = service.getByName(name);
            genericResp.setResult(result);
            return new ResponseEntity<>(genericResp, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Exception at getByName: " + ex.getMessage(), ex);
            return processException(ex);
        }
    }

}
