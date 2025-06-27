package Items;

import interfaces.Basico;
import interfaces.Item;

public class Bacon extends ItemComun implements Basico{
	public Bacon(int cantidad) {
		super("Bacon", cantidad);
	}

	@Override
	public Item crearIntermedio() {
		restarCantidad(1);
		return new BaconCocido(1);
	}
	
}
