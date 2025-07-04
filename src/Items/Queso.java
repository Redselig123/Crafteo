package Items;

import utils.ConstantesItems;
import utils.ConstantesTiempo;

public class Queso extends ItemComun{
	public Queso(int cantidad) {
		super(ConstantesItems.QUESO, cantidad, ConstantesTiempo.TIEMPO_QUESO);
	}
}
