package Rutas;

import java.util.List;

public class ResultadoRuta {

    private List<String> recorrido;
    private double distanciaTotal;

    public ResultadoRuta(List<String> recorrido, double distanciaTotal) {
        this.recorrido = recorrido;
        this.distanciaTotal = distanciaTotal;
    }

    public List<String> getRecorrido() {
        return recorrido;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }
}