package interfaces;

public interface Item {
    String getNombre();
    int getCantidad();
    void sumarCantidad(int cantidad);
    void restarCantidad(int cantidad);
}
