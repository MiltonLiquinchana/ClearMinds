package com.clearminds.test;

import com.clearminds.maquina.MaquinaDulces;

public class TestRecibirDinero {

	public static void main(String[] args) {
		MaquinaDulces maquina = new MaquinaDulces();
		maquina.recibirDinero(0.25);
		maquina.recibirDinero(0.40);
		maquina.recibirDinero(0.50);
		System.out.println("Total dinero ingresado: " + maquina.consultarDineroIngresado());

	}

}
