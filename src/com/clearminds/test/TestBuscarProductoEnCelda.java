package com.clearminds.test;

import com.clearminds.componentes.Producto;
import com.clearminds.maquina.MaquinaDulces;

public class TestBuscarProductoEnCelda {
	public static void main(String[] args) {
		MaquinaDulces maquina = new MaquinaDulces();
		maquina.agregarCelda("A");
		maquina.agregarCelda("B");
		maquina.agregarCelda("C");
		maquina.agregarCelda("D");

		Producto producto = new Producto("KE34", "Papitas", 0.85);
		maquina.cargarProducto(producto, "B", 4);

		Producto productoEncontrado = maquina.buscarProductoEnCelda("B");
		System.out.println("Producto encontrado:" + productoEncontrado.getNombre());

		Producto prod = maquina.buscarProductoEnCelda("A");
		System.out.println("Producto encontrado:" + prod);

	}
}
