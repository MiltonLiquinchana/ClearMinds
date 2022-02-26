package com.clearminds.test;

import com.clearminds.componentes.Producto;
import com.clearminds.maquina.MaquinaDulces;

public class TestVenderConCambioControlado {

	public static void main(String[] args) {

		MaquinaDulces maquina = new MaquinaDulces();
		maquina.agregarCelda("A");
		maquina.agregarCelda("B");
		maquina.agregarCelda("C1");
		maquina.agregarCelda("D");

		Producto producto = new Producto("KE34", "Papitas", 0.26);
		maquina.cargarProducto(producto, "C1", 4);
		maquina.cargaInicial(0.25, 20);
		maquina.cargaInicial(0.01, 500);
		maquina.recibirDinero(5);
		maquina.recibirDinero(0.25);
		System.out.println("Dinero en maquina: " + maquina.dineroActual);

		System.out.println("Cambio a entregar: \n" + maquina.venderConCambioControlado("C1"));

		System.out.println("Dinero en maquina despues de vender: " + maquina.dineroActual);
	}

}