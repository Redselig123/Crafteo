package Items;

import interfaces.Item;

public abstract class ItemComun implements Item {
	protected String nombre;
	protected int cantidad;
	protected int limiteMaximo = 20;

	public ItemComun(String nombre, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public int getCantidad() {
		return cantidad;
	}

	@Override
	public void sumarCantidad(int cantidad) {
		if (this.cantidad + cantidad > limiteMaximo) {
			System.out.println("No se puede agregar más de " + limiteMaximo + " unidades de " + nombre);
			this.cantidad = limiteMaximo;
		} else {
			this.cantidad += cantidad;
		}
	}

	@Override
	public void restarCantidad(int cantidad) {
		if (this.cantidad - cantidad < 0) {
			System.out.println("No hay suficiente cantidad de " + nombre + " para restar " + cantidad);
		} else {
			this.cantidad -= cantidad;
		}
	}
}
