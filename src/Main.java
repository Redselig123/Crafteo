import menu.Menu;


public class Main {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Faltan argumentos. Uso: java Main <ruta_recetas.xml> <ruta_inventario.xml>");
			return;
		}

		String rutaRecetas = args[0];
		String rutaInventario = args[1];

		Menu menu = new Menu(rutaRecetas, rutaInventario);
		menu.iniciar();
		/*
		 * Recetario recetario = new Recetario(); String rutaRecetas =
		 * "C:\\Users\\Cosme Fulanito\\eclipse-workspace\\Ingredientes_prueba\\src\\utils\\recetas.xml"
		 * ; String rutaInventarioInicial =
		 * "C:\\Users\\Cosme Fulanito\\eclipse-workspace\\Ingredientes_prueba\\src\\utils\\inventarioInicial.xml"
		 * ; // pasarlos a argumentos a main o a otro lado
		 * recetario.cargarRecetas(rutaRecetas);
		 * 
		 * Usuario usuario = new Usuario("Facu");
		 * usuario.cargarInventario(rutaInventarioInicial);
		 * System.out.println("Usuario:" + usuario.getNombre());
		 * 
		 * usuario.mostrarInventario(); // Obtengo la receta de "Hamburguesa Simple"
		 * Receta recetaHamburguesa = recetario.getReceta("Hamburguesa Simple"); if
		 * (recetaHamburguesa != null) {
		 * System.out.println("Ingredientes directos para Hamburguesa Simple:");
		 * recetaHamburguesa.mostrarIngredientes(); } else {
		 * System.out.println("No se encontró la receta de Hamburguesa Simple"); }
		 * 
		 * System.out.println();
		 * 
		 * // Obtengo los ingredientes básicos necesarios para fabricar desde cero
		 * Map<String, Integer> basicos =
		 * recetario.getIngredientesBasicos("Hamburguesa Simple");
		 * 
		 * System.out.
		 * println("Ingredientes básicos necesarios para fabricar desde cero:"); for
		 * (Map.Entry<String, Integer> entry : basicos.entrySet()) {
		 * System.out.println("- " + entry.getValue() + " x " + entry.getKey()); } //
		 * probar si resta por crear un intermedio Basico item = (Basico)
		 * usuario.buscarItem("Harina");
		 * 
		 * usuario.crearIntermedio("Harina"); usuario.crearIntermedio("Bacon");
		 * usuario.crearIntermedio("Queso"); usuario.mostrarInventario();
		 * usuario.mostrarHistorial();
		 */
	}
}
