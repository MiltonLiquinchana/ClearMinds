package com.clearminds.maquina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.clearminds.componentes.Celda;
import com.clearminds.componentes.Producto;

public class MaquinaDulces {
	private ArrayList<Celda> celdas = new ArrayList<Celda>();;
	public Map<Double, Integer> dineroActual = new HashMap<Double, Integer>();/* Temporalmente public */
	private double saldo;
	private double totalMontoIngresado;
	private double montosValidos[] = { 0.01, 0.05, 0.10, 0.25, 0.50, 1, 5 };

	public void agregarCelda(String codigo) {
		Celda celda = new Celda(codigo);
		celdas.add(celda);
	}

	public void mostrarConfiguracion() {
		for (Celda celda : celdas) {
			System.out.println("Celda: " + celda.getCodigo());
		}
	}

	public Celda buscarCelda(String codigo) {
		Celda celdaRetorna = null;
		for (Celda celda : celdas) {
			if (codigo.equals(celda.getCodigo())) {
				celdaRetorna = celda;

			}
		}

		return celdaRetorna;

	}

	public void cargarProducto(Producto producto, String codigo, int items) {
		Celda celdaRecuperada = buscarCelda(codigo);
		celdaRecuperada.ingresarProducto(producto, items);
	}

	public void mostrarProductos() {
		for (Celda celda : celdas) {
			System.out.println("***********Celda " + celda.getCodigo());

			System.out.println("Stock: " + celda.getStock());
			if (celda.getProducto() != null) {
				System.out.println("Codigo: " + celda.getProducto().getCodigo());
				System.out.println("Nombre: " + celda.getProducto().getNombre());
				System.out.println("Precio: " + celda.getProducto().getPrecio());
			} else {
				System.out.println("La celda no tiene producto");
			}
		}

		System.out.println("Saldo: " + saldo);

	}

	public Producto buscarProductoEnCelda(String codigo) {
		Producto productoRetorna = null;
		for (Celda celda : celdas) {
			if (codigo.equals(celda.getCodigo())) {
				productoRetorna = celda.getProducto();
			}
		}

		return productoRetorna;

	}

	public double consultarPrecio(String codigo) {
		double precio = 0.0;
		for (Celda celda : celdas) {
			if (codigo.equals(celda.getCodigo())) {
				precio = celda.getProducto().getPrecio();

			}

		}

		return precio;
	}

	public Celda buscarCeldaProducto(String codigoProducto) {
		Celda celdaRetornar = null;

		for (Celda celda : celdas) {
			if (celda.getProducto() != null) {
				if (codigoProducto.equals(celda.getProducto().getCodigo())) {
					celdaRetornar = celda;
				}
			}
		}

		return celdaRetornar;

	}

	public void incrementarProductos(String codigoProducto, int items) {
		Celda celdaEncontrada = buscarCeldaProducto(codigoProducto);
		celdaEncontrada.setStock(celdaEncontrada.getStock() + items);
	}

	public void vender(String codigo) {
		Celda celdaEncontrada = buscarCelda(codigo);
		celdaEncontrada.setStock(celdaEncontrada.getStock() - 1);
		double precio = consultarPrecio(codigo);
		saldo += precio;

	}

	public double venderConCambio(String codigo, int valor) {
		Celda celdaEncotrada = buscarCelda(codigo);
		int stock = celdaEncotrada.getStock() - 1;
		celdaEncotrada.setStock(stock);
		double precio = consultarPrecio(codigo);
		saldo += precio;

		return valor - precio;
	}

	public ArrayList<Producto> buscarMenores(double limite) {
		ArrayList<Producto> productosReturn = new ArrayList<>();
		for (Celda celda : celdas) {
			if (celda.getProducto() != null) {
				if (celda.getProducto().getPrecio() < limite) {
					productosReturn.add(celda.getProducto());
				}
			}
		}

		return productosReturn;
	}

	/* Metodos de evaluacion java standar */
	public void recibirDinero(double monto) {

		if (montosValidos[0] == monto) {
			totalMontoIngresado += monto;
		} else if (montosValidos[1] == monto) {
			totalMontoIngresado += monto;
		} else if (montosValidos[2] == monto) {
			totalMontoIngresado += monto;
		} else if (montosValidos[3] == monto) {
			totalMontoIngresado += monto;
		} else if (montosValidos[4] == monto) {
			totalMontoIngresado += monto;
		} else if (montosValidos[5] == monto) {
			totalMontoIngresado += monto;
		} else if (montosValidos[6] == monto) {
			totalMontoIngresado += monto;
		} else {
			System.out.println("Billete o moneda rechazado: " + monto);
		}

	}

	public double consultarDineroIngresado() {
		return this.totalMontoIngresado;
	}

	public void resetearDinero() {
		this.totalMontoIngresado = 0;
	}

	public HashMap<Double, Integer> venderConEntregaCambio(String codigoCelda) {
		Producto producto = buscarProductoEnCelda(codigoCelda);
		double totalCambio = consultarDineroIngresado() - producto.getPrecio();
		return cambio(totalCambio);

	}

	private HashMap<Double, Integer> cambio(double totalCambio) {
		double resto = 0;
		int monedasCinco = monedas(totalCambio, 5);
		resto = resto(totalCambio, 5);
		int monedasUno = monedas(resto, 1);
		resto = resto(resto, 1);
		int monedasCeroCincuenta = monedas(resto, 0.50);
		resto = resto(resto, 0.50);
		int monedasCeroVeintiCinco = monedas(resto, 0.25);
		resto = resto(resto, 0.25);
		int monedasCeroDies = monedas(resto, 0.10);
		resto = resto(resto, 0.10);
		int monedasCeroCinco = monedas(resto, 0.05);
		resto = resto(resto, 0.05);
		int monedasCeroUno = monedas(resto, 0.01);
		resto = resto(resto, 0.01);
		HashMap<Double, Integer> entregaVuelto = new HashMap<Double, Integer>();

		if (monedasCinco > 0) {
			entregaVuelto.put(montosValidos[6], monedasCinco);
		}
		if (monedasUno > 0) {
			entregaVuelto.put(montosValidos[5], monedasUno);
		}
		if (monedasCeroCincuenta > 0) {
			entregaVuelto.put(montosValidos[4], monedasCeroCincuenta);
		}
		if (monedasCeroVeintiCinco > 0) {
			entregaVuelto.put(montosValidos[3], monedasCeroVeintiCinco);
		}
		if (monedasCeroDies > 0) {
			entregaVuelto.put(montosValidos[2], monedasCeroDies);
		}
		if (monedasCeroCinco > 0) {
			entregaVuelto.put(montosValidos[1], monedasCeroCinco);
		}
		if (monedasCeroUno > 0) {
			entregaVuelto.put(montosValidos[0], monedasCeroUno);
		}
		resetearDinero();
		return entregaVuelto;
	}

	private int monedas(double cantidad, double tipo) {

		int numeroMonedas = (int) (Math.ceil(cantidad * 100) / Math.ceil(tipo * 100));
		if (dineroActual.containsKey(tipo)) {
			return numeroMonedas;
		} else {
			return 0;
		}

//		return numeroMonedas;
	}

	private double resto(double cantidad, double tipo) {
		double residuo = cantidad % tipo;
		if (dineroActual.containsKey(tipo)) {
			return residuo;
		} else {
			return cantidad;
		}
//		return residuo;
	}

	public void cargaInicial(double valorMoneda, int cantidad) {
		dineroActual.put(valorMoneda, cantidad);// 0.25(3), 0.10, 0.50(0) =0.75
	}

	public void venderConCambioControlado(String codigoCelda) {

		System.out.println("Cambio a entregar: " + cambio(0.74));
	}
}
