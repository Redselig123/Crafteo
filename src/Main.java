import menu.Menu;

public class Main {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Faltan argumentos. Uso: java Main <ruta_recetas.xml> <ruta_inventario.xml>");
			return;
		}

		Menu menu = new Menu(args[0], args[1]);
		menu.iniciar();

	}
}
