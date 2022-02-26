package com.clearminds.maquina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.clearminds.componentes.Celda;
import com.clearminds.componentes.Producto;

public class MaquinaDulces {
	private ArrayList<Celda> celdas = new ArrayList<Celda>();
	public Map<Double, Integer> dineroActual = new HashMap<Double, Integer>();
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

	public void recibirDinero(double monto) {

		if (montosValidos[0] == monto) {
			totalMontoIngresado += monto;
			cargaInicial(monto, 1);
		} else if (montosValidos[1] == monto) {
			totalMontoIngresado += monto;
			cargaInicial(monto, 1);
		} else if (montosValidos[2] == monto) {
			totalMontoIngresado += monto;
			cargaInicial(monto, 1);
		} else if (montosValidos[3] == monto) {
			totalMontoIngresado += monto;
			cargaInicial(monto, 1);
		} else if (montosValidos[4] == monto) {
			totalMontoIngresado += monto;
			cargaInicial(monto, 1);
		} else if (montosValidos[5] == monto) {
			totalMontoIngresado += monto;
			cargaInicial(monto, 1);
		} else if (montosValidos[6] == monto) {
			totalMontoIngresado += monto;
			cargaInicial(monto, 1);
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

	/*
	 * Metodo que calcula el tipo y cuantas monedas respectivamente hay que entregar
	 * de cambio
	 */
	public HashMap<Double, Integer> venderConEntregaCambio(String codigo) {

		Producto producto = this.buscarProductoEnCelda(codigo);

		HashMap<Double, Integer> valorEntregar = new HashMap<Double, Integer>();

		double cambio = consultarDineroIngresado() - producto.getPrecio();

		// Transforma el cambio a centavos
		double centavos = cambio / 0.01;

		// Valores de 5.00
		if (centavos >= 500) {
			int moneda = (int) centavos / 500;
			valorEntregar.put(5.00, moneda);
			centavos -= moneda * 500;
		}

		// Valores de 1.00
		if (centavos >= 100) {
			int moneda = (int) centavos / 100;
			valorEntregar.put(1.00, moneda);
			centavos -= moneda * 100;
		}

		// Valores de 0.50
		if (centavos >= 50) {
			int moneda = (int) centavos / 50;
			valorEntregar.put(0.50, moneda);
			centavos -= moneda * 50;
		}

		// Valores de 0.25
		if (centavos >= 25) {
			int moneda = (int) centavos / 25;
			valorEntregar.put(0.25, moneda);
			centavos -= moneda * 25;
		}

		// Valores de 0.10
		if (centavos >= 10) {
			int moneda = (int) centavos / 10;
			valorEntregar.put(0.10, moneda);
			centavos -= moneda * 10;
		}

		// Valores de 0.05
		if (centavos >= 5) {
			int moneda = (int) centavos / 5;
			valorEntregar.put(0.05, moneda);
			centavos -= moneda * 5;
		}

		// Valores de 0.01
		if (centavos >= 1) {
			int moneda = (int) centavos / 1;
			valorEntregar.put(0.01, moneda);
			centavos -= moneda * 1;
		}
		resetearDinero();
		return valorEntregar;

	}

	public void cargaInicial(double valorMoneda, int cantidad) {
		if (dineroActual.containsKey(valorMoneda)) {
			dineroActual.put(valorMoneda, dineroActual.get(valorMoneda) + cantidad);
		} else {
			dineroActual.put(valorMoneda, cantidad);
		}

	}

	// Vender con cambio controlado
	public HashMap<Double, Integer> venderConCambioControlado(String codigo) {
		Producto producto = this.buscarProductoEnCelda(codigo);

		HashMap<Double, Integer> valorEntregar = new HashMap<Double, Integer>();

		double cambio = consultarDineroIngresado() - producto.getPrecio();

		// Transforma el cambio a centavos
		double centavos = cambio / 0.01;

		// Verificamos si hay cantidades de 5 dolares
		if (this.dineroActual.containsKey(5.00) && centavos >= 500) {
			for (int i = 1; i <= dineroActual.get(5.00); i++) {
				if (centavos >= 500) {
					centavos -= 500;
					valorEntregar.put(5.00, i);
					this.dineroActual.put(5.00, dineroActual.get(5.00) - 1);
				} else {
					break;
				}
			}
		}

		// Verificamos si hay valores de 1 en la maquina
		if (this.dineroActual.containsKey(1.00) && centavos >= 100) {
			for (int i = 1; i <= dineroActual.get(1.00); i++) {
				if (centavos >= 100) {
					centavos -= 100;
					valorEntregar.put(1.00, i);
					this.dineroActual.put(1.00, dineroActual.get(1.00) - 1);
				} else {
					break;
				}
			}
		}

		// Verificamos si hay valores de 0.50 en la maquina
		if (this.dineroActual.containsKey(0.50) && centavos >= 50) {
			for (int i = 1; i <= dineroActual.get(0.50); i++) {
				if (centavos >= 50) {
					centavos -= 50;
					valorEntregar.put(0.50, i);
					this.dineroActual.put(0.50, dineroActual.get(0.50) - 1);
				} else {
					break;
				}
			}
		}

		// Verificamos si hay valores de 0.25 en la maquina
		if (this.dineroActual.containsKey(0.25) && centavos >= 25) {
			for (int i = 1; i <= dineroActual.get(0.25); i++) {
				if (centavos >= 25) {
					centavos -= 25;
					valorEntregar.put(0.25, i);
					this.dineroActual.put(0.25, dineroActual.get(0.25) - 1);
				} else {
					break;
				}
			}
		}

		// Verificamos si hay valores de 0.10 en la maquina
		if (this.dineroActual.containsKey(0.10) && centavos >= 10) {
			for (int i = 1; i <= dineroActual.get(0.10); i++) {
				if (centavos >= 10) {
					centavos -= 10;
					valorEntregar.put(0.10, i);
					this.dineroActual.put(0.10, dineroActual.get(0.10) - 1);
				} else {
					break;
				}
			}
		}

		// Verificamos si hay valores de 0.05 en la maquina
		if (this.dineroActual.containsKey(0.05) && centavos >= 5) {
			for (int i = 1; i <= dineroActual.get(0.05); i++) {
				if (centavos >= 5) {
					centavos -= 5;
					valorEntregar.put(0.05, i);
					this.dineroActual.put(0.05, dineroActual.get(0.05) - 1);
				} else {
					break;
				}
			}
		}

		// Verificamos si hay valores de 0.01 en la maquina
		if (this.dineroActual.containsKey(0.01) && centavos >= 1) {
			for (int i = 1; i <= dineroActual.get(0.01); i++) {
				if (centavos >= 1) {
					centavos -= 1;
					valorEntregar.put(0.01, i);
					this.dineroActual.put(0.01, dineroActual.get(0.01) - 1);
				} else {
					break;
				}
			}
		}

		return valorEntregar;

	}
}
