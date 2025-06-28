package menu;

import java.util.Map;
import java.util.Scanner;

import recetas.Recetario;
import usuario.Usuario;

public class Menu {
	private Usuario usuario;
	private Recetario recetario;
	private Scanner scanner;

	private String rutaRecetas;
	private String rutaInventario;

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

		while (opcion != 8) {
			mostrarMenu();
			try {
				opcion = Integer.parseInt(scanner.nextLine());
				manejarOpcion(opcion);
			} catch (NumberFormatException e) {
				System.out.println("Por favor, ingrese un número válido.");
			}
		}

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
		System.out.println("8. Salir");
		System.out.print("Opción: ");
	}

	public void verIngredientesBasicos() {
		Map<String, Integer> basicos = recetario.getIngredientesBasicos("Hamburguesa Simple");

		System.out.println("Ingredientes básicos necesarios para fabricar desde cero hamburguesa simple:");
		for (Map.Entry<String, Integer> entry : basicos.entrySet()) {
			System.out.println("- " + entry.getValue() + " x " + entry.getKey());
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
			System.out.print("Ingrese el nombre del objeto: ");
			String nombreItem = scanner.nextLine();
			Map<String, Integer> ingredientesBasicos = recetario.getIngredientesBasicos(nombreItem);

			System.out.println("Ingredientes básicos necesarios para fabricar \"" + nombreItem + "\":");
			for (Map.Entry<String, Integer> entry : ingredientesBasicos.entrySet()) {
				System.out.println("- " + entry.getValue() + " x " + entry.getKey());
			}
			break;
		case 5:
			// verCrafteables();
			break;
		case 6:
			System.out.print("Ingrese el nombre del objeto basico: ");
			String nombreBasico = scanner.nextLine();
			usuario.crearIntermedio(nombreBasico);
			break;
		case 7:
			// craftearCompleto();
			break;
		case 8:
			break;
		default:
			System.out.println("Opción inválida.");
		}
	}
}