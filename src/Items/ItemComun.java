package Items;

import interfaces.Item;

public abstract class ItemComun implements Item {
	protected String nombre;
	protected int cantidad;
	protected int tiendoDeCrafteo;
	protected int limiteMaximo = 20;//ponerlo en el constructor y crear ConstantesCantidadMaxima por item... uff
	protected int tiempoCrafteo;

	public ItemComun(String nombre, int cantidad, int tiempo) {
		this.tiendoDeCrafteo = 1;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.tiempoCrafteo = tiempo;
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
	public int getTiempoCrafteo() {
		return this.tiempoCrafteo;
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
	public boolean restarCantidad(int cantidad) {
		if (this.cantidad - cantidad < 0) {
			System.out.println("No hay suficiente cantidad de " + nombre + " para restar " + cantidad);
			return false;
		} else {
			this.cantidad -= cantidad;
			return true;
		}
	}
}
