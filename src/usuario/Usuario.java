package usuario;

import java.util.HashMap;
import java.util.Map;

import Items.ItemCompletoFactory;
import interfaces.Item;
import recetas.Receta;
import recetas.Recetario;
import utils.Catalizador;
import utils.TiempoCrafteo;
import static utils.CatalizadorUtils.INGREDIENTES_POR_TIPO;
public class Usuario {
	private String nombre;
	private Inventario inventario;
	private Historial historial;
	private TiempoCrafteo tiempo;


	public Usuario(String nombre) {
		this.nombre = nombre;
		this.inventario = new Inventario();
		this.historial = new Historial();
		this.tiempo = new TiempoCrafteo();
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
			this.agregarItem(item);
			tiempo.sumar(item.getTiempoCrafteo() * cantidad);
			System.out.println("✅ Intermedio creado: " + item.getNombre() + ": " + item.getCantidad() + " en: "
					+ tiempo.mostrarTiempo());
			tiempo.reiniciar();
		} else {
			System.out.println("No se pudo crear intermedio desde " + nombreBasico);
		}
	}

	public Item fabricarCompleto(String nombreCompleto, Recetario recetario,  Catalizador catalizador) {
		Receta receta = recetario.getReceta(nombreCompleto);

		if (receta == null) {
			System.out.println("❌ No se encontró la receta de " + nombreCompleto);
			return null;
		}
		Map<String, Integer> ingredientesReales = new HashMap<>();

		// Aplicar catalizador si corresponde
	    for (Map.Entry<String, Integer> entry : receta.getIngredientes().entrySet()) {
	        String nombreIngrediente = entry.getKey();
	        int cantidad = entry.getValue();

	        if (catalizador != null && INGREDIENTES_POR_TIPO.get(catalizador.getTipo()).contains(nombreIngrediente)) {
	            cantidad = Math.max(1, (int) Math.ceil(cantidad / 2.0)); // nunca menos de 1
	            tiempo.sumar(catalizador.getTiempoCrafteo());
	            System.out.println("🧪 Catalizador aplicado: Se requiere solo " + cantidad + " de " + nombreIngrediente);
	        }

	        ingredientesReales.put(nombreIngrediente, cantidad);
	    }

		// Paso 1: Verificar que tenga todos los ingredientes
		for (Map.Entry<String, Integer> entry : ingredientesReales.entrySet()) {
		    String nombreIngrediente = entry.getKey();
		    int cantidadNecesaria = entry.getValue();

		    Item itemEnInventario = inventario.buscarPorNombre(nombreIngrediente);
		    if (itemEnInventario == null || itemEnInventario.getCantidad() < cantidadNecesaria) {
		        System.out.println("❌ No tienes suficiente de: " + nombreIngrediente);
		        return null;
		    }
		}
		// Paso 2: Restar del inventario
		for (Map.Entry<String, Integer> entry : ingredientesReales.entrySet()) {
			String nombreIngrediente = entry.getKey();
			int cantidadNecesaria = entry.getValue();

			Item itemEnInventario = inventario.buscarPorNombre(nombreIngrediente);
			if (itemEnInventario != null)
				itemEnInventario.restarCantidad(cantidadNecesaria);
		}
		Item nuevoItem = ItemCompletoFactory.crear(nombreCompleto, 1);

		if (nuevoItem != null) {
			this.agregarItem(nuevoItem);
			tiempo.sumar(nuevoItem.getTiempoCrafteo());
			System.out.println("✅ Completo creado: " + nuevoItem.getNombre() + ": " + nuevoItem.getCantidad() + " en: "
					+ tiempo.mostrarTiempo());
			tiempo.reiniciar();
		}
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
