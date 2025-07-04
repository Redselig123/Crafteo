package itemCompleto;

import Items.ItemComun;
import utils.ConstantesItems;
import utils.ConstantesTiempo;

public class HamburguesaConBacon extends ItemComun {

	public HamburguesaConBacon(String nombre, int cantidad) {
		super(ConstantesItems.HAMBURGUESA_CON_BACON, cantidad, ConstantesTiempo.TIEMPO_HAMBURGUESA_CON_BACON);
	}

}
