package com.api.rest.v1.controllers;


/*
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.api.rest.v1.entities.UsuarioEntity;
import com.api.rest.v1.services.productos.I_ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/api/v1/productos")
@CrossOrigin()
public class ProductoController {

	@Autowired
	I_ProductoService iProductoService;

	// ===============================================
	// ============= MÉTODOS HTTP CRUD ==============
	// ===============================================

	// ================
	// ===== POST =====
	// =================
	@Operation(summary = "Inserción de un Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Insertado el Producto Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Insertar el Producto. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "La Inserción del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@PostMapping("/")
	public ResponseEntity<?> addProducto(@RequestBody ProductoEntity producto) {
		try {
			iProductoService.addProducto(producto);
			return new ResponseEntity<ProductoEntity>(producto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}

	// ==============
	// ===== PUT=====
	// ==============
	@Operation(summary = "Actualización de un Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Actualizado el Producto Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Actualizar el Producto. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "La Actualización del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@PutMapping("/")
	public ResponseEntity<?> updateProducto(@RequestBody ProductoEntity producto) {
		try {
			iProductoService.updateProducto(producto);
			return new ResponseEntity<ProductoEntity>(producto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}

	// ==================
	// ===== DELETE =====
	// ==================
	@Operation(summary = "Eliminación de un Producto por su Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Eliminado el Producto Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Eliminar el Producto. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable("id") String id) {
		try {
			iProductoService.deleteProducto(id);
			return new ResponseEntity<UsuarioEntity>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}

	// ===================
	// ===== GET ALL =====
	// ===================
	// ---LISTADO PAGINADO---
	@Operation(summary = "Listado Paginado de Productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer el Listado de Productos. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/listado")
	public Page<ProductoEntity> getAll(Pageable pageable) {
		return iProductoService.getAllProductos(pageable);
	}

	// ==================================================
	// ============= MÉTODOS HTTP BÚSQUEDA =============
	// ==================================================

	// ===================
	// ===== GET BY ID ===
	// ===================
	// ---PRODUCTO POR ID---
	@Operation(summary = "Producto según su Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Producto según su ID Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer el Producto según su ID. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Producto según su ID no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/id/{id}")
	public ProductoEntity getById(@PathVariable("id") String id) {
		return iProductoService.getById(id);
	}

	// =======================
	// ===== GET BY CODIGO ===
	// =======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR CODIGO---
	@Operation(summary = "Listado de Productos o Producto según su Código")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su CODIGO Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su CODIGO. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su CODIGO no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/codigo/{codigo}")
	public Page<ProductoEntity> getByCodigo(@PathVariable("codigo") String codigo, Pageable pageable) {
		return iProductoService.getByCodigo(codigo, pageable);
	}

	// =======================
	// ===== GET BY NOMBRE ===
	// =======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR NOMBRE---
	@Operation(summary = "Listado de Productos o Producto según su Nombre")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su NOMBRE Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su NOMBRE. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su NOMBRE no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/nombre/{nombre}")
	public Page<ProductoEntity> getByNombre(@PathVariable("nombre") String nombre, Pageable pageable) {
		return iProductoService.getByNombre(nombre, pageable);
	}

	// ============================
	// ===== GET BY DESCRIPCION ===
	// ============================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR DESCRIPCION---
	@Operation(summary = "Listado de Productos o Producto según su Descripción")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su DESCRIPCION Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su DESCRIPCION. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su DESCRIPCION no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/descripcion/{descripcion}")
	public Page<ProductoEntity> getByDescripcion(@PathVariable("descripcion") String descripcion, Pageable pageable) {
		return iProductoService.getByDescripcion(descripcion, pageable);
	}

	// ============================
	// ===== GET BY MARCA ===
	// ============================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR MARCA---
	@Operation(summary = "Listado de Productos o Producto según su Marca")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su MARCA Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su MARCA. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su MARCA no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/marca/{marca}")
	public Page<ProductoEntity> getByMarca(@PathVariable("marca") String marca, Pageable pageable) {
		return iProductoService.getByMarca(marca, pageable);
	}

	// =======================
	// ===== GET BY IMAGEN ===
	// =======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR IMAGEN---
	@Operation(summary = "Listado de Productos o Producto según su Imagen")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su IMAGEN Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su IMAGEN. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su IMAGEN no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/imagen/{imagen}")
	public Page<ProductoEntity> getByImagen(@PathVariable("imagen") String imagen, Pageable pageable) {
		return iProductoService.getByImagen(imagen, pageable);
	}

	// ==============================
	// ===== GET BY HOJA DE DATOS ===
	// ==============================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR HOJA DE DATOS---
	@Operation(summary = "Listado de Productos o Producto según su Hoja De Datos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su HOJA DE DATOS Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su HOJA DE DATOS. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su HOJA DE DATOS no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/hojaDatos/{hojaDatos}")
	public Page<ProductoEntity> getByHojaDatos(@PathVariable("hojaDatos") String hojaDatos, Pageable pageable) {
		return iProductoService.getByHojaDatos(hojaDatos, pageable);
	}

	// ======================
	// ===== GET BY STOCK ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR STOCK---
	@Operation(summary = "Listado de Productos o Producto según su Stock")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su STOCK Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su STOCK. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su STOCK no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/stock/{stock}")
	public Page<ProductoEntity> getByStock(@PathVariable("stock") int stock, Pageable pageable) {
		return iProductoService.getByStock(stock, pageable);
	}

	// ======================
	// ===== GET BY PRECIO ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR PRECIO---
	@Operation(summary = "Listado de Productos o Producto según su Precio")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su PRECIO Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su PRECIO. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su PRECIO no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/precio/{precio}")
	public Page<ProductoEntity> getByPrecio(@PathVariable("precio") int precio, Pageable pageable) {
		return iProductoService.getByPrecio(precio, pageable);
	}

	// ======================
	// ===== GET BY FECHA ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR FECHA---
	@Operation(summary = "Listado de Productos o Producto según su Fecha")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su FECHA Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su FECHA. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su FECHA no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/fecha/{fecha}")
	public Page<ProductoEntity> getByFecha(@PathVariable("fecha") String fecha, Pageable pageable) {
		return iProductoService.getByFecha(fecha, pageable);
	}

	// ======================
	// ===== GET BY HORA ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR HORA---
	@Operation(summary = "Listado de Productos o Producto según su Hora")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su HORA Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su HORA. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su HORA no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/hora/{hora}")
	public Page<ProductoEntity> getByHora(@PathVariable("hora") String hora, Pageable pageable) {
		return iProductoService.getByHora(hora, pageable);
	}

	// ========================================
	// ============= GRÁFICOS ==============
	// ========================================

	// ---LISTADO DE PRODUCTOS O PRODUCTO POR HORA---
	@Operation(summary = "Diccionario de Productos en relación al Stock y Marca")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Diccionario de Productos en relación al Stock y Marca Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el  Diccionario de Productos en relación al Stock y Marca. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos  Diccionario de Productos en relación al Stock y Marca no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	
	@GetMapping("/grafico-stock-marca")
	public ResponseEntity<?> graficoStockCateg(Pageable pageable) {

		Page<ProductoEntity> listProd = iProductoService.getAllProductos(pageable);

		// ----------- VARIABLES MARCA ---------------
		String gener = "Generica";
		String hp = "HP";
		String sams = "Samsung";
		String asus = "Asus";
		String hukio = "Hukio";
		String xperia = "Xperia";
		String ard = "Arduino";
		String celet = "Celetron";
		String cisc = "Cisco";
		String lora = "Lora";
		String nulib = "Nulibash";
		String juui = "Juui";
		String apple = "Apple";
		String gezat = "Gezatek";

		// ------------- STOCK POR MARCA ------------------

		// MARCA GENERICA
		int stockGener = iProductoService.getStockPorMarca(listProd, gener);
		int stockHp = iProductoService.getStockPorMarca(listProd, hp);
		int stockSams = iProductoService.getStockPorMarca(listProd, sams);
		int stockAsus = iProductoService.getStockPorMarca(listProd, asus);
		int stockHukio = iProductoService.getStockPorMarca(listProd, hukio);
		int stockXperia = iProductoService.getStockPorMarca(listProd, xperia);
		int stockArd = iProductoService.getStockPorMarca(listProd, ard);
		int stockCelet = iProductoService.getStockPorMarca(listProd, celet);
		int stockCisc = iProductoService.getStockPorMarca(listProd, cisc);
		int stockLora = iProductoService.getStockPorMarca(listProd, lora);
		int stockNulib = iProductoService.getStockPorMarca(listProd, nulib);
		int stockJuui = iProductoService.getStockPorMarca(listProd, juui);
		int stockApple = iProductoService.getStockPorMarca(listProd, apple);
		int stockGezat = iProductoService.getStockPorMarca(listProd, gezat);

		// --------- SET DE STOCK POR CATEGORIA ------------

		Map<String, Integer> data = new LinkedHashMap<>();

		data.put(gener, stockGener);
		data.put(hp, stockHp);
		data.put(sams, stockSams);
		data.put(asus, stockAsus);
		data.put(hukio, stockHukio);
		data.put(xperia, stockXperia);
		data.put(ard, stockArd);
		data.put(celet, stockCelet);
		data.put(cisc, stockCisc);
		data.put(lora, stockLora);
		data.put(nulib, stockNulib);
		data.put(juui, stockJuui);
		data.put(apple, stockApple);
		data.put(gezat, stockGezat);

		return new ResponseEntity<>(data, HttpStatus.OK);

	}

}
*/