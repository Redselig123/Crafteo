package Items;

import interfaces.Basico;
import interfaces.Item;

public class Lechuga extends ItemComun implements Basico {
	public Lechuga(int cantidad) {
		super("Lechuga", cantidad);
	}

	@Override
	public Item crearIntermedio() {
		restarCantidad(1);
		return new LechugaLavada(1);
	}

}
