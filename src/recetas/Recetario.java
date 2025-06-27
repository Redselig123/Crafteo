package recetas;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Recetario {
	private static final Set<String> BASICOS_VALIDOS = Set.of("Harina", "Carne", "Lechuga", "Tomate", "Bacon", "Queso");

	private boolean esBasicoValido(String nombreItem) {
		return BASICOS_VALIDOS.contains(nombreItem);
	}

	private List<Receta> recetas;

	public Recetario() {
		this.recetas = new ArrayList<>();
	}

	public void agregarReceta(Receta receta) {
		recetas.add(receta);
	}

	public List<Receta> getRecetas() {
		return recetas;
	}

	public Receta getReceta(String nombre) {
		for (Receta receta : recetas) {
			if (receta.getNombre().equalsIgnoreCase(nombre)) {
				return receta;
			}
		}
		return null;
	}

	public void mostrarRecetas() {
		for (Receta receta : recetas) {
			receta.mostrarIngredientes();
		}
	}

	private void descomponerIngredientes(String nombreItem, Map<String, Integer> acumulador) {
		Receta receta = getReceta(nombreItem);

		if (receta == null) {
			if (!esBasicoValido(nombreItem)) {
				System.out.println("⚠️ Advertencia: " + nombreItem + " no tiene receta y no es un básico conocido.");
			}

			acumulador.put(nombreItem, acumulador.getOrDefault(nombreItem, 0) + 1);
			return;
		}

		for (Map.Entry<String, Integer> entry : receta.getIngredientes().entrySet()) {
			String ingrediente = entry.getKey();
			int cantidad = entry.getValue();

			for (int i = 0; i < cantidad; i++) {
				descomponerIngredientes(ingrediente, acumulador);
			}
		}
	}

	public Map<String, Integer> getIngredientesBasicos(String nombreFinal) {
		Map<String, Integer> resultado = new HashMap<>();
		descomponerIngredientes(nombreFinal, resultado);
		return resultado;
	} 

	public void cargarRecetas(String rutaArchivo) {
		try {
			File archivo = new File(rutaArchivo);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();//objeto que sabe parcear XMLs
			DocumentBuilder db = dbf.newDocumentBuilder();//este lee va a leer el archivo y convertirlo a datos
			Document doc = db.parse(archivo);//aca lo lee y lo convierte a un arbol de nodos XML DOM

			doc.getDocumentElement().normalize();

			NodeList recetasXML = doc.getElementsByTagName("receta");//busca las etiquetas "receta" y devuelve un NodeList de todas las encontradas

			for (int i = 0; i < recetasXML.getLength(); i++) {
				Element recetaElem = (Element) recetasXML.item(i);//obtiene la receta actual del for
				String nombreReceta = recetaElem.getAttribute("nombre");//lee su nombre

				Receta receta = new Receta(nombreReceta);//crea la receta

				NodeList ingredientes = recetaElem.getElementsByTagName("ingrediente");//empieza a leer los ingredientes
				for (int j = 0; j < ingredientes.getLength(); j++) {
					Element ingredienteElem = (Element) ingredientes.item(j);
					String nombreIngrediente = ingredienteElem.getAttribute("nombre");
					int cantidad = Integer.parseInt(ingredienteElem.getAttribute("cantidad"));
					receta.agregarIngrediente(nombreIngrediente, cantidad);
				}

				this.agregarReceta(receta);
			}

			System.out.println("Recetas cargadas desde XML correctamente.");

		} catch (Exception e) {
			System.out.println("Error al leer el archivo XML: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
