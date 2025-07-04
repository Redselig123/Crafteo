package itemCompleto;

import Items.ItemComun;
import utils.ConstantesItems;
import utils.ConstantesTiempo;

public class HamburguesaTriple extends ItemComun {

	public HamburguesaTriple(String nombre, int cantidad) {
		super(ConstantesItems.HAMBURGUESA_TRIPLE, cantidad,  ConstantesTiempo.TIEMPO_HAMBURGUESA_TRIPLE);
	}

}
