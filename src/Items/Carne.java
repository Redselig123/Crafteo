package Items;

//import Ingredientes.IngredienteBasico;
//import Ingredientes.IngredienteIntermedio;
import interfaces.Basico;
import interfaces.Item;

public class Carne extends ItemComun implements Basico {

	public Carne(int cantidad) {
		super("Carne", cantidad);
	}

	@Override
	public Item crearIntermedio() {
		restarCantidad(1);
		return new CarneCocida(1);
	}
}
