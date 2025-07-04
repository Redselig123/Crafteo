package Items;

import interfaces.Basico;
import interfaces.Item;
import utils.ConstantesItems;
import utils.ConstantesTiempo;

public class Tomate extends ItemComun implements Basico {
	public Tomate(int cantidad) {
		super(ConstantesItems.TOMATE, cantidad, ConstantesTiempo.TIEMPO_TOMATE);
	}

	@Override
	public Item crearIntermedio(int cantidad) {
		if(restarCantidad(cantidad))
			return new TomateLavado(cantidad);
		return null;
	}
}