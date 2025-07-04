package itemCompleto;

import Items.ItemComun;
import utils.ConstantesItems;
import utils.ConstantesTiempo;

public class HamburguesaConLechugaYTomate extends ItemComun{

	public HamburguesaConLechugaYTomate(String nombre, int cantidad) {
		super(ConstantesItems.HAMBURGUESA_CON_LECHUGA_Y_TOMATE, cantidad, ConstantesTiempo.TIEMPO_HAMBURGUESA_LECHUGA_Y_TOMATE);
	}

}
