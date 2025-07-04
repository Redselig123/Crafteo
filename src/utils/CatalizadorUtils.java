package utils;

import java.util.List;
import java.util.Map;

public class CatalizadorUtils {
    public static final Map<Catalizador.Tipo, List<String>> INGREDIENTES_POR_TIPO = Map.of(
        Catalizador.Tipo.COCCION, List.of(ConstantesItems.CARNE_COCIDA),
        Catalizador.Tipo.HORNEADO, List.of(ConstantesItems.BACON_COCIDO, ConstantesItems.PAN),
        Catalizador.Tipo.LAVADO, List.of(ConstantesItems.TOMATE_LAVADO, ConstantesItems.LECHUGA_LAVADA)
    );
}
