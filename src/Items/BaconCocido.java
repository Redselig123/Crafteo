package Items;

import utils.ConstantesItems;
import utils.ConstantesTiempo;


public class BaconCocido extends ItemComun {
	public BaconCocido(int cantidad) {
		super(ConstantesItems.BACON_COCIDO, cantidad, ConstantesTiempo.TIEMPO_BACON_COCIDO);
	}
}