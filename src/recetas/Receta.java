package recetas;

import java.util.HashMap;
import java.util.Map;

public class Receta {
	private String nombre;
	private Map<String, Integer> ingredientes;

	public Receta(String nombre) {
		this.nombre = nombre;
		this.ingredientes = new HashMap<>();
	}

	public void agregarIngrediente(String nombreIngrediente, int cantidad) {
		ingredientes.put(nombreIngrediente, cantidad);
	}

	public Map<String, Integer> getIngredientes() {
		return ingredientes;
	}

	public String getNombre() {
		return nombre;
	}

	public void mostrarIngredientes() {
		System.out.println("Receta: " + nombre);
		for (Map.Entry<String, Integer> entry : ingredientes.entrySet()) {
			System.out.println("- " + entry.getValue() + " x " + entry.getKey());
		}
	}
}
