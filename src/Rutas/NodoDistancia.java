package Rutas;

public class NodoDistancia implements Comparable<NodoDistancia> {

    private String lugar;
    private double distancia;

    public NodoDistancia(String lugar, double distancia) {
        this.lugar = lugar;
        this.distancia = distancia;
    }

    public String getLugar() {
        return lugar;
    }

    public double getDistancia() {
        return distancia;
    }

    @Override
    public int compareTo(NodoDistancia otro) {
        return Double.compare(this.distancia, otro.distancia);
    }
}
