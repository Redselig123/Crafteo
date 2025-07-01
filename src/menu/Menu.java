package menu;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import interfaces.Item;
import recetas.Receta;
import recetas.Recetario;
import usuario.Inventario;
import usuario.Usuario;
import utils.ConstantesItems;

public class Menu {
	private Usuario usuario;
	private Recetario recetario;
	private Scanner scanner;

	private String rutaRecetas;
	private String rutaInventario;

	final List<String> itemsCompl = List.of(ConstantesItems.HAMBURGUESA_SIMPLE, ConstantesItems.HAMBURGUESA_DOBLE,
			ConstantesItems.HAMBURGUESA_TRIPLE, ConstantesItems.HAMBURGUESA_CON_BACON,
			ConstantesItems.HAMBURGUESA_CON_LECHUGA_Y_TOMATE);
	final List<String> itemsI = List.of(ConstantesItems.PAN, ConstantesItems.CARNE_COCIDA, ConstantesItems.BACON_COCIDO,
			ConstantesItems.LECHUGA_LAVADA, ConstantesItems.TOMATE_LAVADO);
	final List<String> itemsB = List.of(ConstantesItems.HARINA, ConstantesItems.CARNE, ConstantesItems.BACON,
			ConstantesItems.LECHUGA, ConstantesItems.TOMATE);

	public Menu(String rutaRecetas, String rutaInventario) {
		this.rutaRecetas = rutaRecetas;
		this.rutaInventario = rutaInventario;
		this.recetario = new Recetario();
		this.scanner = new Scanner(System.in);
	}

	public void iniciar() {
		System.out.print("Ingrese su nombre: ");
		String nombreUsuario = scanner.nextLine();

		usuario = new Usuario(nombreUsuario);

		recetario.cargarRecetas(this.rutaRecetas);
		usuario.cargarInventario(this.rutaInventario);
		System.out.println("Bienvenido, " + usuario.getNombre() + "!");

		mostrarInventarioInicial();

		buclePrincipal();
	}

	private void mostrarInventarioInicial() {
		System.out.println("\nInventario inicial:");
		usuario.mostrarInventario();
	}

	private void buclePrincipal() {
		int opcion = -1;

		while (opcion != 10) {
			mostrarMenu();
			try {
				opcion = Integer.parseInt(scanner.nextLine());
				manejarOpcion(opcion);
			} catch (NumberFormatException e) {
				System.out.println("Por favor, ingrese un número válido.");
			}
		}
		usuario.getHistorial().guardarComoXML("historial.xml");
		System.out.println("inventario.XML generado");

		System.out.println("¡Gracias por jugar!");
	}

	private void mostrarMenu() {
		System.out.println("\n¿Qué deseas hacer?");
		System.out.println("1. Ver inventario");
		System.out.println("2. Ver historial");
		System.out.println("3. Ver recetas");
		System.out.println("4. Ver ingredientes básicos para un objeto");
		System.out.println("5. Ver qué puedo craftear");
		System.out.println("6. Craftear objeto intermedio");
		System.out.println("7. Craftear objeto completo");
		System.out.println("8. Ver ingredientes faltantes (primer nivel)");
		System.out.println("9. Ver qué me falta para craftear un objeto desde cero");
		System.out.println("10. Salir");

		System.out.print("Opción: ");

	}

	public void verIngredientesBasicos() {
		System.out.println("Selecciona el objeto a ver sus ingredientes:");
		mostrarObjetos(itemsCompl);
		System.out.println("Opcion: ");
		try {
			int opcion = Integer.parseInt(scanner.nextLine());

			if (opcion >= 1 && opcion <= itemsCompl.size()) {
				String nombre = itemsCompl.get(opcion - 1);
				Map<String, Integer> ingredientesBasicos = recetario.getIngredientesBasicos(nombre);

				System.out.println("Ingredientes básicos necesarios para fabricar \"" + nombre + "\":");
				for (Map.Entry<String, Integer> entry : ingredientesBasicos.entrySet()) {
					System.out.println("- " + entry.getValue() + " x " + entry.getKey());
				}
			} else {
				System.out.println("Opción inválida.");
			}

		} catch (NumberFormatException e) {
			System.out.println("Entrada inválida. Debe ser un número.");
		}

	}

	private void manejarOpcion(int opcion) {
		switch (opcion) {
		case 1:
			usuario.mostrarInventario();
			break;
		case 2:
			usuario.mostrarHistorial();
			break;
		case 3:
			recetario.mostrarRecetas();
			break;
		case 4:
			verIngredientesBasicos();
			break;
		case 5:
			verCuantosPuedoCraftear();
			break;
		case 6:
			creaftearIntermedio();
			break;
		case 7:
			craftearCompleto();
			break;
		case 8:
			mostrarFaltantesPrimerNivel();
			break;
		case 9:
			mostrarFaltantesDeSegundoNivel();
			break;
		case 10:
			break;

		default:
			System.out.println("Opcion invalida.");
		}
	}

	// podria mandar el metodo a llamar y no repetir codigo
	private void mostrarFaltantesPrimerNivel() {
		System.out.print("Ingrese el nombre del objeto a craftear: ");
		mostrarObjetos(itemsCompl);
		int num = Integer.parseInt(scanner.nextLine());
		if (num > 0 && num <= itemsCompl.size())
			usuario.mostrarFaltantesParaCraftear(itemsCompl.get(num - 1), recetario);
	}

	private void mostrarFaltantesDeSegundoNivel() {
		System.out.print("Ingrese el nombre del objeto: ");
		mostrarObjetos(itemsCompl);
		int num = Integer.parseInt(scanner.nextLine());
		if (num > 0 && num <= itemsCompl.size())
			usuario.mostrarFaltantesdesdeCero(itemsCompl.get(num - 1), recetario);
	}

	private void mostrarObjetos(List<String> l) {
		for (int i = 0; i < l.size(); i++) {
			System.out.printf("%d. %s%n", i + 1, l.get(i));
		}
	}

	private void verCuantosPuedoCraftear() {
		System.out.println("Selecciona el objeto Completo a ver cuantos puede craftear:");
		mostrarObjetos(itemsCompl);
		int num = Integer.parseInt(scanner.nextLine());
		if (num > 0 && num <= itemsCompl.size()) {
			Receta receta = recetario.getReceta(itemsCompl.get(num - 1));
			int cant = cuantosPuedoCraftear(usuario, receta, recetario);

			if (cant != Integer.MAX_VALUE) {
				System.out.println("Se pueden craftear " + cant + " " + receta.getNombre());
			} else {
				System.out.println("no se puede craftear el objeto.");
			}
		} else {
			System.out.println("opcion invalida.");
		}

	}

	private void craftearCompleto() {
		System.out.println("Selecciona el objeto Completo a craftear:");
		mostrarObjetos(itemsCompl);

		System.out.println("Opcion: ");
		try {
			int opcion = Integer.parseInt(scanner.nextLine());

			if (opcion >= 1 && opcion <= itemsCompl.size()) {
				String nombre = itemsCompl.get(opcion - 1);
				usuario.fabricarCompleto(nombre, recetario);
			} else {
				System.out.println("Opción inválida.");
			}

		} catch (NumberFormatException e) {
			System.out.println("Entrada inválida. Debe ser un número.");
		}
	}

	private void creaftearIntermedio() {
		System.out.println("Selecciona el objeto intermedio a craftear:");
		mostrarObjetos(itemsI);

		System.out.println("Opcion: ");
		try {
			int opcion = Integer.parseInt(scanner.nextLine());

			if (opcion >= 1 && opcion <= itemsB.size()) {
				String nombre = itemsB.get(opcion - 1);
				System.out.print("¿Cuántos querés crear?: ");
				int cantidad = Integer.parseInt(scanner.nextLine());
				usuario.crearIntermedio(nombre, cantidad);
			} else {
				System.out.println("Opción inválida.");
			}

		} catch (NumberFormatException e) {
			System.out.println("Entrada inválida. Debe ser un número.");
		}

	}

	public static int cuantosPuedoCraftear(Usuario user, Receta receta, Recetario recetario) {
		return calcularCantidadCrafteable(user.getInventario(), receta, recetario);
	}

	private static int calcularCantidadCrafteable(Inventario inventario, Receta receta, Recetario recetario) {
		int maxCrafteos = Integer.MAX_VALUE;// cambiar por otra macro

		for (Map.Entry<String, Integer> entrada : receta.getIngredientes().entrySet()) {
			String nombreIngrediente = entrada.getKey();
			int cantidadNecesaria = entrada.getValue();

			Item itemInventario = inventario.buscarPorNombre(nombreIngrediente);

			if (itemInventario != null && itemInventario.getCantidad() >= cantidadNecesaria) {
				// ingrediente basico disponible en inventario
				int crafteosPosibles = itemInventario.getCantidad() / cantidadNecesaria;
				maxCrafteos = Math.min(maxCrafteos, crafteosPosibles);
			} else {
				// ver si es un ingrediente crafteable
				Receta subReceta = recetario.getReceta(nombreIngrediente);

				if (subReceta != null) {
					// calcular cuantas veces puedo craftear este ingrediente
					int crafteosIntermedio = calcularCantidadCrafteable(inventario, subReceta, recetario);
					int crafteosUsandoIntermedio = crafteosIntermedio / cantidadNecesaria;
					maxCrafteos = Math.min(maxCrafteos, crafteosUsandoIntermedio);
				} else {
					return 0;
				}
			}
		}
		return maxCrafteos == Integer.MAX_VALUE ? 0 : maxCrafteos;
	}

}