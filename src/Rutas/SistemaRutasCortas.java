package Rutas;

import java.util.Scanner;

public class SistemaRutasCortas {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Grafo grafo = new Grafo();

        int opcion;

        do {

            System.out.println("\n====================================");
            System.out.println(" SISTEMA DE RUTAS CORTAS (DIJKSTRA)");
            System.out.println("====================================");
            System.out.println("1. Registrar lugar");
            System.out.println("2. Registrar conexión");
            System.out.println("3. Mostrar mapa");
            System.out.println("4. Calcular ruta más corta");
            System.out.println("5. Cargar mapa de ejemplo");
            System.out.println("6. Salir");
            System.out.print("Seleccione opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Nombre del lugar: ");
                    String lugar = scanner.nextLine();

                    if (grafo.existeLugar(lugar)) {
                        System.out.println("El lugar ya existe.");
                    } else {
                        grafo.registrarLugar(lugar);
                        System.out.println("Lugar registrado.");
                    }
                    break;

                case 2:
                    System.out.print("Origen: ");
                    String origen = scanner.nextLine();

                    System.out.print("Destino: ");
                    String destino = scanner.nextLine();

                    if (!grafo.existeLugar(origen) || !grafo.existeLugar(destino)) {
                        System.out.println("Error: ambos deben existir.");
                        break;
                    }

                    System.out.print("Distancia: ");
                    double distancia = scanner.nextDouble();
                    scanner.nextLine();

                    if (distancia <= 0) {
                        System.out.println("Distancia inválida.");
                    } else {
                        grafo.registrarConexion(origen, destino, distancia);
                        System.out.println("Conexión registrada.");
                    }
                    break;

                case 3:
                    grafo.mostrarMapa();
                    break;

                case 4:
                    System.out.print("Origen: ");
                    String ini = scanner.nextLine();

                    System.out.print("Destino: ");
                    String fin = scanner.nextLine();

                    if (!grafo.existeLugar(ini) || !grafo.existeLugar(fin)) {
                        System.out.println("Error: lugares no existen.");
                        break;
                    }

                    ResultadoRuta r = grafo.calcularRutaMasCorta(ini, fin);

                    if (r.getDistanciaTotal() == Double.MAX_VALUE) {
                        System.out.println("No existe ruta.");
                    } else {

                        System.out.println("\n========= RUTA MÁS CORTA =========");

                        for (int i = 0; i < r.getRecorrido().size(); i++) {

                            System.out.print(r.getRecorrido().get(i));

                            if (i < r.getRecorrido().size() - 1) {
                                System.out.print(" -> ");
                            }
                        }

                        System.out.println("\nDistancia total: "
                                + r.getDistanciaTotal() + " km");
                    }
                    break;

                case 5:
                    grafo.cargarMapaEjemplo();
                    System.out.println("Mapa de ejemplo cargado.");
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 6);

        scanner.close();
    }
}