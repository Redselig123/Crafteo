package itemCompleto;

import Items.ItemComun;
import utils.ConstantesItems;
import utils.ConstantesTiempo;

public class HamburguesaDoble extends ItemComun{

	public HamburguesaDoble(String nombre, int cantidad) {
		super(ConstantesItems.HAMBURGUESA_DOBLE, cantidad, ConstantesTiempo.TIEMPO_HAMBURGUESA_DOBLE);
	}

}
