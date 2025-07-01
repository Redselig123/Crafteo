package Items;

import interfaces.Item;
import interfaces.ItemCreator;
import itemCompleto.HamburguesaConBacon;
import itemCompleto.HamburguesaConLechugaYTomate;
import itemCompleto.HamburguesaDoble;
import itemCompleto.HamburguesaSimple;
import itemCompleto.HamburguesaTriple;

import java.util.HashMap;
import java.util.Map;
import utils.ConstantesItems;

public class ItemCompletoFactory {
	private static final Map<String, ItemCreator> creadores = new HashMap<>();

	static {
		creadores.put(ConstantesItems.HAMBURGUESA_SIMPLE, cantidad -> new HamburguesaSimple());
		creadores.put(ConstantesItems.HAMBURGUESA_CON_BACON,
				cantidad -> new HamburguesaConBacon(ConstantesItems.HAMBURGUESA_CON_BACON, cantidad));
		creadores.put(ConstantesItems.HAMBURGUESA_DOBLE,
				cantidad -> new HamburguesaDoble(ConstantesItems.HAMBURGUESA_DOBLE, cantidad));
		creadores.put(ConstantesItems.HAMBURGUESA_TRIPLE,
				cantidad -> new HamburguesaTriple(ConstantesItems.HAMBURGUESA_TRIPLE, cantidad));
		creadores.put(ConstantesItems.HAMBURGUESA_CON_LECHUGA_Y_TOMATE,
				cantidad -> new HamburguesaConLechugaYTomate(ConstantesItems.HAMBURGUESA_CON_LECHUGA_Y_TOMATE,
						cantidad));

	}

	public static Item crear(String nombre) {
		return crear(nombre, 1);
	}

	public static Item crear(String nombre, int cantidad) {
		ItemCreator creator = creadores.get(nombre);
		if (creator == null) {
			System.out.println("❌ No se encontró constructor para: " + nombre);
			return null;
		}
		return creator.create(cantidad);
	}
}
