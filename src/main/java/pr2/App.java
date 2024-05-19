package pr2;

import java.util.List;
import org.pr2.Graph;

public class App {
    public static void main(String[] args) {
        // Creamos un grafo de enteros
        Graph<Integer> graph = new Graph<>();

        // Añadimos algunos vértices y arcos
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(5, 4);

        // Mostramos el grafo
        System.out.println("Grafo:");
        System.out.println(graph);

        // Probamos obtener los vértices adyacentes a un vértice
        try {
            System.out.println("Vértices adyacentes a 1: " + graph.obtainAdjacents(1));
            System.out.println("Vértices adyacentes a 3: " + graph.obtainAdjacents(3));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Probamos si el grafo contiene ciertos vértices
        System.out.println("¿El grafo contiene el vértice 1?: " + graph.containsVertex(1));
        System.out.println("¿El grafo contiene el vértice 6?: " + graph.containsVertex(6));

        // Probamos el método shortestPath
        List<Integer> shortestPath = graph.shortestPath(1, 4);
        System.out.println("Camino más corto entre 1 y 4: " + shortestPath);
    }
}