package Items;

import utils.ConstantesItems;
import utils.ConstantesTiempo;

public class LechugaLavada extends ItemComun {
    public LechugaLavada(int cantidad) {
        super(ConstantesItems.LECHUGA_LAVADA, cantidad, ConstantesTiempo.TIEMPO_LECHUGA);
    }
}
