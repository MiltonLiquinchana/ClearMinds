package com.clearminds.componentes;

public class Celda {

	private String codigo;
	private int stock;
	private Producto producto;
	

	public Celda(String codigo) {
		this.codigo = codigo;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	

	public void ingresarProducto(Producto producto, int stock) {
		this.producto = producto;
		this.stock = stock;
	}

	
}
