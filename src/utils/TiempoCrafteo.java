package utils;

public class TiempoCrafteo {
    private int totalSegundos;

    public void sumar(int segundos) {
        this.totalSegundos += segundos;
    }

    public String mostrarTiempo() {
        int minutos = totalSegundos / 60;
        int segundos = totalSegundos % 60;
        return minutos + "m " + segundos + "s";
    }
    public void reiniciar() {
    	this.totalSegundos = 0;
    }
}
