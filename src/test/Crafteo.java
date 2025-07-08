package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.Item;
import menu.Menu;
import recetas.Receta;
import recetas.Recetario;
import usuario.Usuario;
import utils.ConstantesItems;

class Crafteo {
	Menu menuTest;
	Usuario userTest = new Usuario("fulano");

	private Recetario recetario;
	private Receta receta;

	@Test
	void testCrearObjetoCompletoRecetaIncorrecta() {
		Item resultado = userTest.fabricarCompleto("Hamburguesa Con Huevo", recetario, null);

		assertNull(resultado, "No existe hamburguesa con huevo en el recetario");
	}

	@Test
	void testCrearObjetoCompletoCantidadInsuficiente() {
		// agrego objetos intermedios al inventario
		userTest.crearIntermedio("Harina", 1);
		userTest.crearIntermedio("Bacon", 5);
		// userTest.crearIntermedio("Carne", 1);
		// No agregamos Carne cocida al inventario, por lo tanto debe fallar y dar null

		Item resultado = userTest.fabricarCompleto("Hamburguesa Con Bacon", recetario, null);

		assertNull(resultado, "No se debería fabricar el ítem si faltan ingredientes");
	}

	@Test
	void testCrearObjetoCompletoConCantidadSuficiente() {
		// creo objetos intermedios nececsarios: 1 pan, 5 bacon cocido, 1 carne cocida.
		userTest.crearIntermedio("Harina", 1);
		userTest.crearIntermedio("Bacon", 5);
		userTest.crearIntermedio("Carne", 1);

		Item resultado = userTest.fabricarCompleto("Hamburguesa Con Bacon", recetario, null);

		assertNotNull(resultado, "objeto creado, no debe ser null");
	}

	@Test
	void testFaltantesEnInventarioParaCraftearConRecetaEncontrada() {
		userTest.crearIntermedio("Harina", 1);
		userTest.crearIntermedio("Bacon", 5);
		userTest.crearIntermedio("Carne", 1);

		boolean resultado = userTest.mostrarFaltantesParaCraftear(ConstantesItems.HAMBURGUESA_CON_BACON, recetario);

		assertTrue(resultado);
	}

	@Test
	void testFaltantesEnInventarioParaCraftearConRecetaNoEncontrada() {
		// no existe hamburguesa con huevo en el recetario, no deberia importar si hay
		// objetos intermedios o no
		boolean resultado = userTest.mostrarFaltantesParaCraftear("Hamburguesa Con Huevo", recetario);

		assertFalse(resultado);
	}

	@Test
	void testCuantosPuedoCraftearConRecetaValida() {
		receta = recetario.getReceta("Pan");
		int resultado = Menu.cuantosPuedoCraftear(userTest, receta, recetario);

		// mayor a cero, se puede craftear el objeto
		assertTrue(resultado > 0);
	}

	@Test
	void testCuantosPuedoCraftearConRecetaInvalida() {
		receta = recetario.getReceta("Pan Danes");
		// receta es null
		// corregir cuantosPuedoCraftear para manejar cuando una receta es null
		int resultado = Menu.cuantosPuedoCraftear(userTest, receta, recetario);

		// si es igual a esta macro, no se encontro la receta
		assertTrue(resultado == Integer.MAX_VALUE);
	}

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final InputStream originalIn = System.in;

	@BeforeEach
	void setUp() {
		System.setOut(new PrintStream(outContent));
		// Redirige System.out a outContent
		// seteo menu para tests
		// cambiar rutas!
		menuTest = new Menu(
				"C:\\Users\\Cosme Fulanito\\eclipse-workspace\\Ingredientes_prueba\\src\\utils\\recetas.xml",
				"C:\\Users\\Cosme Fulanito\\eclipse-workspace\\Ingredientes_prueba\\src\\utils\\inventarioInicial.xml");
		menuTest.setScanner(new Scanner(System.in));
	}

	@AfterEach
	void tearDown() {
		// para restaurar la consola como estaba antes de iniciar cualquier test
		System.setOut(originalOut);
		System.setIn(originalIn);
	}

	// para verIngredientesBasicos
	@Test
	public void ingresoLetraEnOpcionesDeIngreBasico() {
		// simulo el ingreso de una letra
		String simulatedInput = "a\n";
		System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
		menuTest.setScanner(new Scanner(System.in));

		// ejectuo el metodo con la letra simulada de entrada.
		menuTest.verIngredientesBasicos();

		// capturo la salida y verifico
		String salida = outContent.toString();

		// este texto se muestra cuando se ingresa una letra
		assertTrue(salida.contains("Entrada inválida. Debe ser un número."));
	}

	@Test
	public void ingresoFueraDeRangoEnOpcionesDeIngreBasico() {
		// simulo el ingreso de un numero fuera de rango
		String simulatedInput = "6\n";
		System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
		menuTest.setScanner(new Scanner(System.in));

		// ejectuo el metodo con la letra simulada de entrada.
		menuTest.verIngredientesBasicos();

		// capturo la salida y verifico
		String salida = outContent.toString();

		// esto se muestra cuando se ingresa un numero fuera de rango
		assertTrue(salida.contains("Opción inválida."));
	}

	// para craftear intermedio
	@Test
	public void ingresoLetraOpcionInvalidaCraftearIntermedio() {
		String simulatedInput = "a\n";
		System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
		menuTest.setScanner(new Scanner(System.in));

		menuTest.creaftearIntermedio();

		String salida = outContent.toString();
		assertTrue(salida.contains("Entrada inválida. Debe ser un número."));
	}

	@Test
	public void ingresoNumeroOpcionInvalidaCraftearIntermedio() {
		String simulatedInput = "100\n";
		System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
		menuTest.setScanner(new Scanner(System.in));

		menuTest.creaftearIntermedio();

		String salida = outContent.toString();
		assertTrue(salida.contains("Opción inválida."));
	}

}
