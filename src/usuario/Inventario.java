package usuario;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Items.Bacon;
import Items.Carne;
import Items.Harina;
import Items.ItemCompletoFactory;
import Items.Lechuga;
import Items.Queso;
import Items.Tomate;
import interfaces.Basico;
import interfaces.Item;
import recetas.Receta;
import recetas.Recetario;

public class Inventario {
	private List<Item> items;

	public Inventario() {
		this.items = new ArrayList<>();
	}

	public void agregarItem(Item nuevoItem) {
		for (Item item : items) {
			if (item.getNombre().equals(nuevoItem.getNombre())) {
				item.sumarCantidad(nuevoItem.getCantidad());
				return;
			}
		}
		items.add(nuevoItem);
	}

	public List<Item> getItems() {
		return items;
	}

	public void mostrar() {
		System.out.println("Inventario:");
		for (Item item : items) {
			System.out.println(item.getNombre() + ": " + item.getCantidad());
		}
	}

	public Item buscarPorNombre(String nombre) {
		for (Item item : items) {
			if (item.getNombre().equals(nombre)) {
				return item;
			}
		}
		System.out.println("No se encontró el ítem: " + nombre);
		return null;
	}

	public void cargarInventarioInicial(String rutaArchivo) {
		try {
			File archivo = new File(rutaArchivo);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();// objeto que sabe parcear XMLs
			DocumentBuilder db = dbf.newDocumentBuilder();// este lee va a leer el archivo y convertirlo a datos
			Document doc = db.parse(archivo);// aca lo lee y lo convierte a un arbol de nodos XML DOM

			doc.getDocumentElement().normalize();

			NodeList items = doc.getElementsByTagName("item");// busca las etiquetas "receta" y devuelve un
																// NodeList de todas las encontradas

			for (int i = 0; i < items.getLength(); i++) {
				Element itemElem = (Element) items.item(i);// obtiene el item actual del for
				String nombre = itemElem.getAttribute("nombre");// lee su nombre
				int cantidad = Integer.parseInt(itemElem.getAttribute("cantidad"));

				Item item = crearItemPorNombre(nombre, cantidad);
				if (item != null) {
					this.agregarItem(item);// agregar que entre al historial?
				} else {
					System.out.println("No se pudo crear el item: " + nombre);
				}
			}

			System.out.println("Inventario inicial cargado desde XML correctamente.");
		} catch (Exception e) {
			System.out.println("Error al leer el archivo de inventario: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public Item fabricarIntermedio(String nombreItemBasico, int cantidad) {
		for (Item item : items) {
			if (item.getNombre().equals(nombreItemBasico) && item instanceof Basico) {
				Basico basico = (Basico) item;
				Item intermedio = basico.crearIntermedio(cantidad); // Esto ya llama a restarCantidad internamente
				if (intermedio != null) {
					agregarItem(intermedio); // Agregamos el nuevo item intermedio
					return intermedio;
				}
				return null;
			}
		}
		System.out.println("No se encontró el item básico " + nombreItemBasico + " en el inventario");
		return null;
	}

	public int getCantidad(String nombreItem) {
		for (Item item : items) {
			if (item.getNombre().equalsIgnoreCase(nombreItem)) {
				return item.getCantidad();
			}
		}
		return 0;
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

			Item itemEnInventario = ((Inventario) items).buscarPorNombre(nombreIngrediente);

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

	public Item fabricarCompleto(String nombre, Recetario recetario) {
	    if (mostrarFaltantesParaCraftear(nombre, recetario)) {
	        Receta receta = recetario.getReceta(nombre);
	        if (receta == null) return null;

	        // Restar cantidades de ingredientes
	        for (Map.Entry<String, Integer> entry : receta.getIngredientes().entrySet()) {
	            String nombreIngrediente = entry.getKey();
	            int cantidad = entry.getValue();
	            Item item = buscarPorNombre(nombreIngrediente);
	            if (item != null) {
	                item.restarCantidad(cantidad);
	            }
	        }

	        // Crear ítem completo con el factory
	        return ItemCompletoFactory.crear(nombre);
	    }

	    return null;
	}

	private Item crearItemPorNombre(String nombre, int cantidad) {
		switch (nombre) {
		case "Harina":
			return new Harina(cantidad);
		case "Carne":
			return new Carne(cantidad);
		case "Bacon":
			return new Bacon(cantidad);
		case "Queso":
			return new Queso(cantidad);
		case "Lechuga":
			return new Lechuga(cantidad);
		case "Tomate":
			return new Tomate(cantidad);
		default:
			return null;
		}
	}

}
