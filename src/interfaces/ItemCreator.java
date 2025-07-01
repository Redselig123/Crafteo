package interfaces;

@FunctionalInterface
public interface ItemCreator {
    Item create(int cantidad);
}
