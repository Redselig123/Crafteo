package usuario;

import java.util.HashMap;
import java.util.Map;

import Items.ItemCompletoFactory;
import interfaces.Item;
import recetas.Receta;
import recetas.Recetario;

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

	public void crearIntermedio(String nombreBasico, int cantidad) {
		Item item = inventario.fabricarIntermedio(nombreBasico, cantidad);
		if (item != null) {
			System.out.println("Intermedio creado: " + item.getNombre() + ": " + item.getCantidad());
			this.agregarItem(item);
			//historial.registrar(item.getNombre() + ":" + item.getCantidad());
		} else {
			System.out.println("No se pudo crear intermedio desde " + nombreBasico);
		}
	}

	public Item fabricarCompleto(String nombreCompleto, Recetario recetario) {
		Receta receta = recetario.getReceta(nombreCompleto);

		if (receta == null) {
			System.out.println("❌ No se encontró la receta de " + nombreCompleto);
			return null;
		}

		// Paso 1: Verificar que tenga todos los ingredientes
		for (Map.Entry<String, Integer> entry : receta.getIngredientes().entrySet()) {
			String nombreIngrediente = entry.getKey();
			int cantidadNecesaria = entry.getValue();

			Item itemEnInventario = inventario.buscarPorNombre(nombreIngrediente);
			if (itemEnInventario == null || itemEnInventario.getCantidad() < cantidadNecesaria) {
				System.out.println("❌ No tienes suficiente de: " + nombreIngrediente);
				return null;
			}
		}
		// Paso 2: Restar del inventario
		for (Map.Entry<String, Integer> entry : receta.getIngredientes().entrySet()) {
			String nombreIngrediente = entry.getKey();
			int cantidadNecesaria = entry.getValue();

			Item itemEnInventario = inventario.buscarPorNombre(nombreIngrediente);
			itemEnInventario.restarCantidad(cantidadNecesaria);
		}
		Item nuevoItem = ItemCompletoFactory.crear(nombreCompleto, 1);

		//agregarItem(nuevoItem);
		this.agregarItem(nuevoItem);
		return nuevoItem;
	}

	public Boolean mostrarFaltantesParaCraftear(String nombreItem, Recetario recetario) {
		Receta receta = recetario.getReceta(nombreItem);

		if (receta == null) {
			System.out.println("❌ No se encontró una receta para: " + nombreItem);
			return false;
		}

		Map<String, Integer> faltantes = new HashMap<>();

		for (Map.Entry<String, Integer> entry : receta.getIngredientes().entrySet()) {
			String nombreIngrediente = entry.getKey();
			int cantidadNecesaria = entry.getValue();

			Item itemEnInventario = inventario.buscarPorNombre(nombreIngrediente);

			if (itemEnInventario == null) {
				faltantes.put(nombreIngrediente, cantidadNecesaria);
			} else {
				int cantidadFaltante = cantidadNecesaria - itemEnInventario.getCantidad();
				if (cantidadFaltante > 0) {
					faltantes.put(nombreIngrediente, cantidadFaltante);
				}
			}
		}

		if (faltantes.isEmpty()) {
			System.out.println("Tienes todos los ingredientes necesarios para fabricar " + nombreItem + ".");
			return true;
		} else {
			System.out.println("Faltan los siguientes ingredientes para fabricar " + nombreItem + ":");
			for (Map.Entry<String, Integer> entry : faltantes.entrySet()) {
				System.out.println("- " + entry.getValue() + " x " + entry.getKey());
			}
			return false;
		}
	}

	public void mostrarFaltantesdesdeCero(String nombreItemComp, Recetario recetario) {
		Map<String, Integer> necesarios = recetario.getIngredientesBasicos(nombreItemComp);
		Map<String, Integer> faltantes = new HashMap<>();

		for (Map.Entry<String, Integer> entry : necesarios.entrySet()) {
			String nombre = entry.getKey();
			int necesariosCantidad = entry.getValue();
			int enInventario = inventario.getCantidad(nombre);
			if (enInventario < necesariosCantidad) {
				faltantes.put(nombre, necesariosCantidad - enInventario);

			}

		}
		if (faltantes.isEmpty()) {
			System.out.println(
					"Tienes todos los ingredientes básicos necesarios para fabricar \"" + nombreItemComp + "\".");
		} else {
			System.out.println(
					"❌ Te faltan los siguientes ingredientes básicos para fabricar \"" + nombreItemComp + "\":");
			for (Map.Entry<String, Integer> entry : faltantes.entrySet()) {
				System.out.println("- " + entry.getValue() + " x " + entry.getKey());

			}
		}
	}

}
