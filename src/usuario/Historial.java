package usuario;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Historial {
	private List<String> historial;

	public Historial() {
		this.historial = new ArrayList<>();
	}

	public void registrar(String entrada) {
		historial.add(entrada);
	}

	public void mostrar() {
		System.out.println("Historial de acciones:");
		for (String item : historial) {
			System.out.println(item);
		}
	}

	public void guardarComoXML(String rutaArchivo) {
	    try {
	        // Crear documento XML
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc = builder.newDocument();

	        // Elemento raíz <historial>
	        Element root = doc.createElement("historial");
	        doc.appendChild(root);

	        // Agregar cada entrada como <entrada>
	        for (String entrada : historial) {
	            Element item = doc.createElement("entrada");
	            item.setTextContent(entrada);
	            root.appendChild(item);
	        }

	        // Guardar el XML en archivo
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // formato legible
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File(rutaArchivo));
	        transformer.transform(source, result);

	        System.out.println("✅ Historial guardado en: " + rutaArchivo);
	    } catch (Exception e) {
	        System.out.println("❌ Error al guardar historial en XML: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


}
