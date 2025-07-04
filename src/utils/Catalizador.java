package utils;

import Items.ItemComun;

public class Catalizador extends ItemComun {
	private Tipo tipo;

	public enum Tipo {
		COCCION, HORNEADO, LAVADO
	}

	public Catalizador(String nombre, Tipo tipo, int cantidad) {
		super(nombre, cantidad, ConstantesTiempo.TIEMPO_CATALIZADOR);
		this.tipo = tipo;
	}

	public Tipo getTipo() {
		return tipo;
	}
}
