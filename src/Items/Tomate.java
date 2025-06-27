package Items;

import interfaces.Basico;
import interfaces.Item;

public class Tomate extends ItemComun implements Basico {
	public Tomate(int cantidad) {
		super("Tomate", cantidad);
	}

	@Override
	public Item crearIntermedio() {
		restarCantidad(1);
		return new TomateLavado(1);
	}
}