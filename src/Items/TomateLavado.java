package Items;

import utils.ConstantesItems;
import utils.ConstantesTiempo;

public class TomateLavado extends ItemComun {
    public TomateLavado(int cantidad) {
        super(ConstantesItems.TOMATE_LAVADO, cantidad, ConstantesTiempo.TIEMPO_TOMATE_LAVADO);
    }
}
