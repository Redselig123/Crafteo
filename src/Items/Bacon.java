package Items;

import interfaces.Basico;
import interfaces.Item;
import utils.ConstantesItems;


public class Bacon extends ItemComun implements Basico{
	public Bacon(int cantidad) {
		super(ConstantesItems.BACON, cantidad);
	}

	@Override
	public Item crearIntermedio() {
		if(restarCantidad(1))
			return new BaconCocido(1);
		return null;
	}
	
}
