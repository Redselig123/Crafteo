package Items;

import interfaces.Basico;
import interfaces.Item;
import utils.ConstantesItems;


public class Harina extends ItemComun implements Basico {
	public Harina(int cantidad) {
		super(ConstantesItems.HARINA, cantidad);
	}

	@Override
	public Item crearIntermedio() {
		restarCantidad(1);
		return new Pan(1); // Ejemplo simple
	}
}
 