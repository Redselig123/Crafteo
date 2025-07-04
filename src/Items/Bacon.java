package Items;

import interfaces.Basico;
import interfaces.Item;
import utils.ConstantesItems;
import utils.ConstantesTiempo;


public class Bacon extends ItemComun implements Basico{
	public Bacon(int cantidad) {
		super(ConstantesItems.BACON, cantidad, ConstantesTiempo.TIEMPO_BACON);
	}

	@Override
	public Item crearIntermedio(int cantidad) {
		if(restarCantidad(cantidad))
			return new BaconCocido(cantidad);
		return null;
	}
	
}
