package Items;

//import Ingredientes.IngredienteBasico;
//import Ingredientes.IngredienteIntermedio;
import interfaces.Basico;
import interfaces.Item;
import utils.ConstantesItems;


public class Carne extends ItemComun implements Basico {

	public Carne(int cantidad) {
		super(ConstantesItems.CARNE, cantidad);
	}

	@Override
	public Item crearIntermedio(int cantidad) {
		if(restarCantidad(cantidad))
			return new CarneCocida(cantidad);
		return null;
	}
}
