package Rutas;

import java.util.*;

public class Grafo {

    // Lista de adyacencia
    private Map<String, List<Arista>> adyacencia;

    public Grafo() {
        adyacencia = new HashMap<>();
    }

    // Registrar lugar
    public void registrarLugar(String lugar) {
        adyacencia.putIfAbsent(lugar, new ArrayList<>());
    }

    // Registrar conexión (con validaciones)
    public void registrarConexion(String origen, String destino, double distancia) {

        if (origen.equals(destino)) {
            System.out.println("No se puede conectar un lugar consigo mismo.");
            return;
        }

        if (conexionExiste(origen, destino)) {
            System.out.println("Esta conexión ya existe.");
            return;
        }

        adyacencia.get(origen).add(new Arista(destino, distancia));
        adyacencia.get(destino).add(new Arista(origen, distancia));
    }

    // Verificar conexión duplicada
    private boolean conexionExiste(String origen, String destino) {
        for (Arista a : adyacencia.get(origen)) {
            if (a.getDestino().equals(destino)) {
                return true;
            }
        }
        return false;
    }

    // Mostrar mapa mejorado
    public void mostrarMapa() {

        System.out.println("\n========== MAPA DE LUGARES ==========");

        for (String lugar : adyacencia.keySet()) {

            System.out.println("\nLugar: " + lugar);

            for (Arista a : adyacencia.get(lugar)) {
                System.out.println("   -> " + a.getDestino()
                        + " (" + a.getPeso() + " km)");
            }
        }

        System.out.println("\nTotal de lugares: " + adyacencia.size());
    }

    // Dijkstra
    public ResultadoRuta calcularRutaMasCorta(String origen, String destino) {

        Map<String, Double> distancias = new HashMap<>();
        Map<String, String> anteriores = new HashMap<>();
        PriorityQueue<NodoDistancia> cola = new PriorityQueue<>();

        for (String lugar : adyacencia.keySet()) {
            distancias.put(lugar, Double.MAX_VALUE);
        }

        distancias.put(origen, 0.0);
        cola.add(new NodoDistancia(origen, 0.0));

        while (!cola.isEmpty()) {

            NodoDistancia actual = cola.poll();

            if (actual.getLugar().equals(destino)) break;

            for (Arista arista : adyacencia.get(actual.getLugar())) {

                double nuevaDistancia =
                        distancias.get(actual.getLugar()) + arista.getPeso();

                if (nuevaDistancia < distancias.get(arista.getDestino())) {

                    distancias.put(arista.getDestino(), nuevaDistancia);
                    anteriores.put(arista.getDestino(), actual.getLugar());

                    cola.add(new NodoDistancia(
                            arista.getDestino(),
                            nuevaDistancia
                    ));
                }
            }
        }

        List<String> recorrido = new ArrayList<>();
        String actual = destino;

        if (!anteriores.containsKey(destino) && !origen.equals(destino)) {
            return new ResultadoRuta(recorrido, Double.MAX_VALUE);
        }

        while (actual != null) {
            recorrido.add(actual);
            actual = anteriores.get(actual);
        }

        Collections.reverse(recorrido);

        return new ResultadoRuta(recorrido, distancias.get(destino));
    }

    public boolean existeLugar(String lugar) {
        return adyacencia.containsKey(lugar);
    }

    public int cantidadLugares() {
        return adyacencia.size();
    }

    // MAPA DE EJEMPLO (para exposición)
    public void cargarMapaEjemplo() {

        registrarLugar("Casa");
        registrarLugar("Mercado");
        registrarLugar("Universidad");
        registrarLugar("Hospital");
        registrarLugar("Paradero");

        registrarConexion("Casa", "Mercado", 2);
        registrarConexion("Casa", "Paradero", 6);
        registrarConexion("Mercado", "Universidad", 4);
        registrarConexion("Mercado", "Hospital", 3);
        registrarConexion("Hospital", "Universidad", 1);
        registrarConexion("Universidad", "Paradero", 2);
    }
}