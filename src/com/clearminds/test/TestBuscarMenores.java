package com.clearminds.test;

import com.clearminds.componentes.Producto;
import com.clearminds.maquina.MaquinaDulces;

public class TestBuscarMenores {

	public static void main(String[] args) {
		MaquinaDulces maquina = new MaquinaDulces();

		maquina.agregarCelda("A1");
		maquina.agregarCelda("B1");
		maquina.agregarCelda("C1");
		maquina.agregarCelda("D1");
		maquina.agregarCelda("E1");
		maquina.agregarCelda("F1");

		Producto producto = new Producto("KE34", "Papitas", 0.85);
		maquina.cargarProducto(producto, "A1", 4);

		Producto producto2 = new Producto("D456", "Doritos", 0.70);
		maquina.cargarProducto(producto2, "B1", 6);

		Producto producto3 = new Producto("D457", "Banano", 0.70);
		maquina.cargarProducto(producto3, "C1", 8);

		Producto producto4 = new Producto("D458", "Caramelo", 0.70);
		maquina.cargarProducto(producto4, "D1", 5);

		Producto producto5 = new Producto("D459", "Manzana", 0.70);
		maquina.cargarProducto(producto5, "E1", 6);

		Producto producto6 = new Producto("D455", "Pera", 0.50);
		maquina.cargarProducto(producto6, "F1", 9);
		
		System.out.println(maquina.buscarMenores(0.55));
	}
}
