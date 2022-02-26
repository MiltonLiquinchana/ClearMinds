package com.clearminds.test;

import com.clearminds.componentes.Producto;
import com.clearminds.maquina.MaquinaDulces;

public class TestVenderConEntregaCambio {
	public static void main(String[] args) {

		MaquinaDulces maquina = new MaquinaDulces();
		maquina.agregarCelda("A");
		maquina.agregarCelda("B");
		maquina.agregarCelda("C1");
		maquina.agregarCelda("D");

		Producto producto = new Producto("KE34", "Papitas", 0.26);
		maquina.cargarProducto(producto, "C1", 4);
		maquina.recibirDinero(5);

		System.out.println("Total dinero ingresado: " + maquina.consultarDineroIngresado());
		System.out.println("Cambio a entregar: \n" + maquina.venderConEntregaCambio("C1"));
		System.out.println("Total dinero ingresado despues de resetear: " + maquina.consultarDineroIngresado());
	


	}
}
