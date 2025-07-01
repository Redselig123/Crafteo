package Items;

import interfaces.Basico;
import interfaces.Item;
import utils.ConstantesItems;


public class Lechuga extends ItemComun implements Basico {
	public Lechuga(int cantidad) {
		super(ConstantesItems.LECHUGA, cantidad);
	}

	@Override
	public Item crearIntermedio() {
		restarCantidad(1);
		return new LechugaLavada(1);
	}

}
