package usuario;

import java.util.ArrayList;
import java.util.List;

import interfaces.Item;

public class Historial {
	private List<String> historial;// deberia ser un string y mostrar nombre y cantidad

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
