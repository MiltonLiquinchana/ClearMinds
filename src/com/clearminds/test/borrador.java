package com.clearminds.test;

import com.clearminds.componentes.Producto;
import com.clearminds.maquina.MaquinaDulces;

public class borrador {

	public static void main(String[] args) {

		MaquinaDulces maquina = new MaquinaDulces();
		maquina.agregarCelda("A");
		maquina.agregarCelda("B");
		maquina.agregarCelda("C1");
		maquina.agregarCelda("D");

		Producto producto = new Producto("KE34", "Papitas", 0.26);
		maquina.cargarProducto(producto, "C1", 4);
		maquina.cargaInicial(0.25, 10);
		maquina.cargaInicial(0.01, 100);
		maquina.recibirDinero(1);
//		System.out.println(maquina.dineroActual);

		System.out.println(maquina.venderConCambioControlado("C1"));
	}

}