
package com.api.rest.v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.v1.entities.ProductoEntity;
import com.api.rest.v1.services.productos.I_ProductoService;
import com.api.rest.v1.utils.http.JsonResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/v1/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

	@Autowired
	I_ProductoService iProductoService;

	// ===============================================
	// ============= MÉTODOS HTTP CRUD ==============
	// ===============================================

	// ================
	// ===== POST =====
	// =================
	@ApiOperation(value = "Inserción de un Producto", notes = "Devolución del producto agregado")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se ha Insertado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Insertado el Producto Correctamente"),
			@ApiResponse(code = 400, message = "No se pudo Insertar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para insertar un producto. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido insertar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "La Inserción del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addProducto(@RequestBody ProductoEntity producto) {
		try {
			iProductoService.addProducto(producto);
			return new ResponseEntity<ProductoEntity>(producto, HttpStatus.OK);
		} catch (Exception e) {
			JsonResponse jsonResponseMsg = new JsonResponse();
			jsonResponseMsg.setMessage(e.getMessage());
			return new ResponseEntity<JsonResponse>(jsonResponseMsg,HttpStatus.BAD_REQUEST);

		}
	}

	// ==============
	// ===== PUT=====
	// ==============
	@ApiOperation(value = "Actualización de un Producto", notes = "Devolución del producto Actualizado")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400, message = "No se pudo Actualizar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "La Actualización del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateProducto(@PathVariable String id, @RequestBody ProductoEntity producto) {
		try {
			iProductoService.updateProducto(id, producto);
			return new ResponseEntity<ProductoEntity>(producto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}

	// ==================
	// ===== DELETE =====
	// ==================
	@ApiOperation(value = "Eliminación de un Producto", notes = "Devolución del producto Eliminado")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 400, message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Eliminar el producto. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Eliminar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "La Eliminacion del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteProducto(@PathVariable("id") String id) {
		try {
			iProductoService.deleteProducto(id);
			return new ResponseEntity<ProductoEntity>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}

	// ===================
	// ===== GET ALL =====
	// ===================
	// ---LISTADO PAGINADO---
	@ApiOperation(value = "Listado Paginado de Productos", notes = "Devolución del Listado Paginado de Productos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se han Listado los Productos Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos Correctamente"),
			@ApiResponse(code = 400, message = "No se ha podido Listar los Productos. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/listado")
	public Page<ProductoEntity> getAll(Pageable pageable) {
		return iProductoService.getAllProductos(pageable);
	}

	// =========================
	// ===== GET ALL FILTER =====
	// ==========================
	// ---LISTADO PAGINADO FILTROS---
	@ApiOperation(value = "Listado Paginado de Productos con Filtro", notes = "Devolución del Listado Paginado de Productos con Filtro")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se han Listado los Productos con Filtro Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos con Filtro Correctamente"),
			@ApiResponse(code = 400, message = "No se ha podido Listar los Productos con Filtro. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos con Filtro. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos con Filtro. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/listado-filter/{filtro}")
	public Page<ProductoEntity> getAllFilter(@PathVariable String filtro, Pageable pageable) {
		return iProductoService.getAllProductosFilter(filtro, pageable);
	}
	
	// ==================================
	// ===== GET ALL EXCLUDE FILTER =====
	// ==================================
	// ---LISTADO PAGINADO CON FILTROS EXCLUIDOS---
	@ApiOperation(value = "Listado Paginado de Productos con Filtro excluido", notes = "Devolución del Listado Paginado de Productos con Filtro")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se han Listado los Productos con Filtro excluido Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos con Filtro excluido Correctamente"),
			@ApiResponse(code = 400, message = "No se ha podido Listar los Productos con Filtro excluido. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos con Filtro excluido. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos con Filtro excluido. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/listado-excluir-filtro/{excluirFiltro}")
	public Page<ProductoEntity> getAllExcludeFilter(@PathVariable String excluirFiltro, Pageable pageable) {
		return iProductoService.getAllProductosExcludeFilter(excluirFiltro, pageable);
	}

	// ==================================================
	// ============= MÉTODOS HTTP BÚSQUEDA =============
	// ==================================================

	// ===================
	// ===== GET BY ID ===
	// ===================
	// ---PRODUCTO POR ID---
	@ApiOperation(value = "Producto según su ID", notes = "Devolución del Producto  según su ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se ha Listado el Producto según su ID Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Listado el Producto según su ID Correctamente"),
			@ApiResponse(code = 400, message = "No se ha podido Listar el Producto  según su ID. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar el Producto según su ID. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar el Producto según su ID. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Producto según su ID no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@GetMapping("/id/{id}")
	public ProductoEntity getById(@PathVariable("id") String id) {
		return iProductoService.getById(id);
	}

	// =======================
	// ===== GET BY CODIGO ===
	// =======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR CODIGO---
	@ApiOperation(value = "Listado Paginado de Productos según su Código", notes = "Devolución del Listado Paginado de Productos según su Código")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Código Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Código Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Código. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Código. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Código. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/codigo/{codigo}")
	public Page<ProductoEntity> getByCodigo(@PathVariable("codigo") String codigo, Pageable pageable) {
		return iProductoService.getByCodigo(codigo, pageable);
	}

	// =======================
	// ===== GET BY NOMBRE ===
	// =======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR NOMBRE---
	@ApiOperation(value = "Listado Paginado de Productos según su Nombre", notes = "Devolución del Listado Paginado de Productos según su Nombre")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Nombre Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Nombre Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Nombre. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Nombre. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Nombre. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/nombre/{nombre}")
	public Page<ProductoEntity> getByNombre(@PathVariable("nombre") String nombre, Pageable pageable) {
		return iProductoService.getByNombre(nombre, pageable);
	}

	// ============================
	// ===== GET BY DESCRIPCION ===
	// ============================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR DESCRIPCION---
	@ApiOperation(value = "Listado Paginado de Productos según su Descripción", notes = "Devolución del Listado Paginado de Productos según su Descripción")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Descripción Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Descripción Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Descripción. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Descripción. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Descripción. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/descripcion/{descripcion}")
	public Page<ProductoEntity> getByDescripcion(@PathVariable("descripcion") String descripcion, Pageable pageable) {
		return iProductoService.getByDescripcion(descripcion, pageable);
	}

	// ============================
	// ===== GET BY CATEGORIA ===
	// ============================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR CATEGORIA---
	@ApiOperation(value = "Listado Paginado de Productos según su Categoría", notes = "Devolución del Listado Paginado de Productos según su Categoría")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Categoría Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Categoría Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Categoría. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Categoría. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Categoría. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/categoria/{categoria}")
	public Page<ProductoEntity> getByCategoria(@PathVariable("categoria") String categoria, Pageable pageable) {
		return iProductoService.getByCategoria(categoria, pageable);
	}

	// ============================
	// ===== GET BY MARCA ===
	// ============================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR MARCA---
	@ApiOperation(value = "Listado Paginado de Productos según su Marca", notes = "Devolución del Listado Paginado de Productos según su Marca")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Marca Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Marca Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Marca. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Marca. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Marca. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@GetMapping("/marca/{marca}")
	public Page<ProductoEntity> getByMarca(@PathVariable("marca") String marca, Pageable pageable) {
		return iProductoService.getByMarca(marca, pageable);
	}

	// =======================
	// ===== GET BY IMAGEN ===
	// =======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR IMAGEN---
	@ApiOperation(value = "Listado Paginado de Productos según su Imagen", notes = "Devolución del Listado Paginado de Productos según su Marca")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Imagen Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Imagen Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Imagen. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Imagen. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Imagen. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/imagen/{imagen}")
	public Page<ProductoEntity> getByImagen(@PathVariable("imagen") String imagen, Pageable pageable) {
		return iProductoService.getByImagen(imagen, pageable);
	}

	// ==============================
	// ===== GET BY HOJA DE DATOS ===
	// ==============================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR HOJA DE DATOS---
	@ApiOperation(value = "Listado Paginado de Productos según su Hoja de Datos", notes = "Devolución del Listado Paginado de Productos según su Hoja de Datos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Hoja de Datos Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Hoja de Datos Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Hoja de Datos. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Hoja de Datos. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Hoja de Datos. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/hoja-datos/{hojaDatos}")
	public Page<ProductoEntity> getByHojaDatos(@PathVariable("hojaDatos") String hojaDatos, Pageable pageable) {
		return iProductoService.getByHojaDatos(hojaDatos, pageable);
	}

	// ======================
	// ===== GET BY STOCK ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR STOCK---
	@ApiOperation(value = "Listado Paginado de Productos según su Stock", notes = "Devolución del Listado Paginado de Productos según su Stock")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Stock Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Stock Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Stock. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Stock. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Stock. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/stock/{stock}")
	public Page<ProductoEntity> getByStock(@PathVariable("stock") int stock, Pageable pageable) {
		return iProductoService.getByStock(stock, pageable);
	}

	// =====================================
	// ===== GET BY STOCK FILTER (MAX)======
	// =====================================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR STOCK CON FILTRO---
	@ApiOperation(value = "Listado Paginado de Productos según su Stock Máximo", notes = "Devolución del Listado Paginado de Productos según su Stock Máximo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Stock Máximo Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Stock Máximo Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Stock Máximo. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Stock Máximo. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Stock Máximo. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/stock-filter/{maxStock}")
	public Page<ProductoEntity> getByStockFilter(@PathVariable("maxStock") int maxStock, Pageable pageable) {
		return iProductoService.getByStockFilter(maxStock, pageable);
	}
	
	// ===========================================
	// ===== GET BY STOCK FILTER (MIN & MAX)======
	// ===========================================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR STOCK CON FILTROS (MIN & MAX)---
	@ApiOperation(value = "Listado Paginado de Productos según su Stock Mínimo y Máximo", notes = "Devolución del Listado Paginado de Productos según su Stock Mínimo y Máximo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Stock Mínimo y Máximo"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Stock Mínimo y Máximo"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Stock Mínimo y Máximo. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Stock Mínimo y Máximo. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Stock Mínimo y Máximo. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/stock-filter/{minStock}/{maxStock}")
	public Page<ProductoEntity> getByStockFilter(@PathVariable("minStock") int minStock, @PathVariable("maxStock") int maxStock, Pageable pageable) {
		return iProductoService.getByStockFilter(minStock, maxStock, pageable);
	}

	// ======================
	// ===== GET BY PRECIO ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR PRECIO---
	@ApiOperation(value = "Listado Paginado de Productos según su Precio", notes = "Devolución del Listado Paginado de Productos según su Precio")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Precio Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Precio Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Precio. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Precio. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Precio. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/precio/{precio}")
	public Page<ProductoEntity> getByPrecio(@PathVariable("precio") int precio, Pageable pageable) {
		return iProductoService.getByPrecio(precio, pageable);
	}

	// ===================================
	// ===== GET BY PRECIO FILTER (MÁX)===
	// ===================================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR PRECIO MÁX---
	@ApiOperation(value = "Listado Paginado de Productos según su Precio Máximo", notes = "Devolución del Listado Paginado de Productos según su Precio Máximo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Precio Máximo con Filtro Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Precio Máximo con Filtro Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Precio Máximo con Filtro. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Precio Máximo con Filtro. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Precio Máximo con Filtro. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/precio-filter/{maxPrecio}")
	public Page<ProductoEntity> getByPrecioFilter(@PathVariable("maxPrecio") int maxPrecio, Pageable pageable) {
		return iProductoService.getByPrecioFilter(maxPrecio, pageable);
	}
	

	// =========================================
	// ===== GET BY PRECIO FILTER (MÍN & MÁX)===
	// =========================================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR PRECIO MÍN Y MÁX---
	@ApiOperation(value = "Listado Paginado de Productos según su Precio Mínimo y Máximo", notes = "Devolución del Listado Paginado de Productos según su Precio Mínimo y Máximo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Precio Mínimo y Máximo con Filtro Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Precio Mínimo y Máximo con Filtro Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Precio Mínimo y Máximo con Filtro. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Precio Mínimo y Máximo con Filtro. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según su Precio Mínimo y Máximo con Filtro. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/precio-filter/{minPrecio}/{maxPrecio}")
	public Page<ProductoEntity> getByPrecioFilter(@PathVariable("minPrecio") int minPrecio, @PathVariable("maxPrecio") int maxPrecio, Pageable pageable) {
		return iProductoService.getByPrecioFilter(minPrecio, maxPrecio, pageable);
	}

	// ======================
	// ===== GET BY FECHA ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR FECHA---
	@ApiOperation(value = "Listado Paginado de Productos según su Fecha", notes = "Devolución del Listado Paginado de Productos según su Fecha")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Fecha Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Fecha Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Fecha. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Fecha. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según Fecha. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/fecha/{fecha}")
	public Page<ProductoEntity> getByFecha(@PathVariable("fecha") String fecha, Pageable pageable) {
		return iProductoService.getByFecha(fecha, pageable);
	}

	// ======================
	// ===== GET BY HORA ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR HORA---
	@ApiOperation(value = "Listado Paginado de Productos según su Hora", notes = "Devolución del Listado Paginado de Productos según su Hora")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Hora Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Hora Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Hora. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Hora. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según Hora. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/hora/{hora}")
	public Page<ProductoEntity> getByHora(@PathVariable("hora") String hora, Pageable pageable) {
		return iProductoService.getByHora(hora, pageable);
	}
	
	
	// =============================
	// ===== GET BY FECHA & HORA ===
	// =============================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR FECHA Y HORA---
	@ApiOperation(value = "Listado Paginado de Productos según su Fecha y Hora", notes = "Devolución del Listado Paginado de Productos según su Fecha y Hora")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Productos según su Fecha y Hora Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Productos según su Fecha y Hora Correctamente"),
			@ApiResponse(code = 400, message = "No se han podido Listar los Productos según su Fecha y Hora. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Listar los Productos según su Fecha y Hora. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Listar los Productos según Fecha y Hora. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/fecha-hora/{fecha}/{hora}")
	public Page<ProductoEntity> getByFechaHora(@PathVariable("fecha") String fecha, @PathVariable("hora") String hora, Pageable pageable) {
		return iProductoService.getByFechaHora(fecha, hora, pageable);
	}

	// ===============================================
	// ========= MÉTODOS PARA GRAFICO ==============
	// ===============================================

	// ============================
	// ===== GET BY STOCK-CATEG ===
	// ============================

	// ------ STOCK POR CATEGORIA ------
	@ApiOperation(value = "Stock de Productos según su categoría", notes = "Devolución del Stock de Productos según su Categoría")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha devuelto el Stock de Productos según su Categoría Correctamente"),
			@ApiResponse(code = 201, message = "Se ha devuelto el Stock de Productos según su Categoría Correctamente"),
			@ApiResponse(code = 400, message = "No se ha podido devolver el Stock de Productos según su Categoría. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para obtener el Stock de Productos según su Categoría. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido devolver el Stock de Productos según su Categoría. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Stock de Productos según su Categoría no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/stock-por-categoria/{categoria}")
	public int getStockByCategory(@PathVariable("categoria") String categoria, Pageable pageable) {
		return iProductoService.getStockByCategory(categoria, pageable);

	}

	// ============================
	// ===== GET BY STOCK-MARCA ===
	// ============================

	// ------ STOCK POR MARCA ------
	@ApiOperation(value = "Stock de Productos según su marca", notes = "Devolución del Stock de Productos según su marca")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha devuelto el Stock de Productos según su marca Correctamente"),
			@ApiResponse(code = 201, message = "Se ha devuelto el Stock de Productos según su marca Correctamente"),
			@ApiResponse(code = 400, message = "No se ha podido devolver el Stock de Productos según su marca. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para obtener el Stock de Productos según su marca. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido devolver el Stock de Productos según su marca. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Stock de Productos según su marca no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/stock-por-marca/{marca}")
	public int getStockByMarca(@PathVariable("marca") String marca, Pageable pageable) {
		return iProductoService.getStockByMarca(marca, pageable);

	}

	// ============================
	// ===== GET BY STOCK-FECHA ===
	// ============================

	// ------ STOCK POR FECHA ------
	@ApiOperation(value = "Stock de Productos según su fecha", notes = "Devolución del Stock de Productos según su fecha")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha devuelto el Stock de Productos según su fecha Correctamente"),
			@ApiResponse(code = 201, message = "Se ha devuelto el Stock de Productos según su fecha Correctamente"),
			@ApiResponse(code = 400, message = "No se ha podido devolver el Stock de Productos según su fecha. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para obtener el Stock de Productos según su fecha. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido devolver el Stock de Productos según su fecha. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El Stock de Productos según su fecha no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405, message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407, message = "La autenticación debe estar realizada a partir de un proxy."),
			@ApiResponse(code = 408, message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409, message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410, message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422, message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500, message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503, message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505, message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507, message = "Almacenamiento Insuficiente por parte del Servidor.") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/stock-por-fecha/{fecha}")
	public int getStockByFecha(@PathVariable("fecha") String fecha, Pageable pageable) {
		return iProductoService.getStockByFecha(fecha, pageable);

	}

}
