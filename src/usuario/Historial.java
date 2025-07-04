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



}
