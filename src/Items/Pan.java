package Items;

import utils.ConstantesItems;
import utils.ConstantesTiempo;

public class Pan extends ItemComun {
    public Pan(int cantidad) {
        super(ConstantesItems.PAN, cantidad, ConstantesTiempo.TIEMPO_PAN);
    }
}
