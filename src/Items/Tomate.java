package Items;

import interfaces.Basico;
import interfaces.Item;
import utils.ConstantesItems;

public class Tomate extends ItemComun implements Basico {
	public Tomate(int cantidad) {
		super(ConstantesItems.TOMATE, cantidad);
	}

	@Override
	public Item crearIntermedio() {
		if(restarCantidad(1))
			return new TomateLavado(1);
		return null;
	}
}