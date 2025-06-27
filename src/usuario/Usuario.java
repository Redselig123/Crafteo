package usuario;

import interfaces.Item;

public class Usuario {
	private String nombre;
	private Inventario inventario;
	private Historial historial;

	public Usuario(String nombre) {
		this.nombre = nombre;
		this.inventario = new Inventario();
		this.historial = new Historial();
	}

	public String getNombre() {
		return this.nombre;
	}

	public void agregarItem(Item item) {
		inventario.agregarItem(item);
		historial.registrar(item.getNombre() + ":" + item.getCantidad());
		System.out.println("Se agregó " + item.getNombre() + ": " + item.getCantidad());
	}

	public void mostrarHistorial() {
		historial.mostrar();
	}

	public void mostrarInventario() {
		inventario.mostrar();
	}

	public Inventario getInventario() {
		return inventario;
	}

	public Historial getHistorial() {
		return historial;
	}

	public Item buscarItem(String nombre) {
		return inventario.buscarPorNombre(nombre);
	}

	public void cargarInventario(String nombre) {
		inventario.cargarInventarioInicial(nombre);
	}
	public void crearIntermedio(String nombreBasico) {
		Item item = inventario.fabricarIntermedio(nombreBasico);
		if(item != null) {
			historial.registrar(item.getNombre() + ":" + item.getCantidad());
		}
		else {
			System.out.println("No se pudo crear intermedio desde " + nombreBasico);
		}
	}

}
