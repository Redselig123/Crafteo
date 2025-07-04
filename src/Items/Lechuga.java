package Items;

import interfaces.Basico;
import interfaces.Item;
import utils.ConstantesItems;
import utils.ConstantesTiempo;


public class Lechuga extends ItemComun implements Basico {
	public Lechuga(int cantidad) {
		super(ConstantesItems.LECHUGA, cantidad, ConstantesTiempo.TIEMPO_LECHUGA);
	}

	@Override
	public Item crearIntermedio(int cantidad) {
		if(restarCantidad(cantidad))
			return new LechugaLavada(cantidad);
		return null;
	}

}
