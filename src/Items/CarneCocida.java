package Items;

import utils.ConstantesItems;
import utils.ConstantesTiempo;

public class CarneCocida extends ItemComun {
	public CarneCocida(int cantidad) {
		super(ConstantesItems.CARNE_COCIDA, cantidad,ConstantesTiempo.TIEMPO_CARNE_COCIDA);
	}
}
