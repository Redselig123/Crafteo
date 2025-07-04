package Items;

//import Ingredientes.IngredienteBasico;
//import Ingredientes.IngredienteIntermedio;
import interfaces.Basico;
import interfaces.Item;
import utils.ConstantesItems;
import utils.ConstantesTiempo;


public class Carne extends ItemComun implements Basico {

	public Carne(int cantidad) {
		super(ConstantesItems.CARNE, cantidad, ConstantesTiempo.TIEMPO_CARNE);
	}

	@Override
	public Item crearIntermedio(int cantidad) {
		if(restarCantidad(cantidad))
			return new CarneCocida(cantidad);
		return null;
	}
}
