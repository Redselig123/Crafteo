package Items;

import interfaces.Basico;
import interfaces.Item;
import utils.ConstantesItems;


public class Harina extends ItemComun implements Basico {
	public Harina(int cantidad) {
		super(ConstantesItems.HARINA, cantidad);
	}

	@Override
	public Item crearIntermedio(int cantidad) {
		if(restarCantidad(cantidad))
			return new Pan(cantidad); // Ejemplo simple
		return null;
	}
}
 