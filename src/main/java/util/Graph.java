package util;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

public class Graph<V>{

    //Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /**
     * Añade el vértice `v` al grafo.
     *
     * @param v vértice a añadir.
     * @return `true` si no estaba anteriormente y `false` en caso contrario.
     */
    public boolean addVertex(V v){
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new HashSet<>());
            return true;
        }
        return false;
    }


    /**
     * Añade un arco entre los vértices `v1` y `v2` al grafo. En caso de
     * que no exista alguno de los vértices, lo añade también.
     *
     * @param v1 el origen del arco.
     * @param v2 el destino del arco.
     * @return `true` si no existía el arco y `false` en caso contrario.
     */
    public boolean addEdge(V v1, V v2){
        if (!adjacencyList.containsKey(v1)) {
            addVertex(v1);
        }
        if (!adjacencyList.containsKey(v2)) {
            addVertex(v2);
        }
        adjacencyList.get(v1).add(v2);
        return true;
    }

    /**
     * Obtiene el conjunto de vértices adyacentes a `v`.
     *
     * @param v vértice del que se obtienen los adyacentes.
     * @return conjunto de vértices adyacentes.
     */
    public Set<V> obtainAdjacents(V v) throws Exception{
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("Vertice no encontrado");
        }
        return adjacencyList.get(v);
    }

    /**
     * Comprueba si el grafo contiene el vértice dado. 
     *
     * @param v vértice para el que se realiza la comprobación.
     * @return `true` si `v` es un vértice del grafo.
     */
    public boolean containsVertex(V v){
        return adjacencyList.containsKey(v);
    }

    /**
     * Método `toString()` reescrito para la clase `Grafo.java`.
     * @return una cadena de caracteres con la lista de adyacencia.
     */
    @Override
    public String toString(){
        return adjacencyList.toString();
    }

    /**
     * Obtiene, en caso de que exista, el camino más corto entre
     * `v1` y `v2`. En caso contrario, devuelve `null`.
     * 
     * @param v1 el vértice origen.
     * @param v2 el vértice destino.
     * @return lista con la secuencia de vértices del camino más corto
     * entre `v1` y `v2`
     **/
    public List<V> shortestPath(V v1, V v2){
        if (!adjacencyList.containsKey(v1) || !adjacencyList.containsKey(v2)) {
            return null;
        }
        Map<V, V> previousVertices = new HashMap<>();
        Queue<V> queue = new LinkedList<>();
        Set<V> visited = new HashSet<>();

        queue.add(v1);
        visited.add(v1);
        boolean found = false;

        while (!queue.isEmpty()) {
            V currentVertex = queue.poll();
            if (currentVertex.equals(v2)) {
                found = true;
                break;
            }
            for (V neighbor : adjacencyList.get(currentVertex)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    previousVertices.put(neighbor, currentVertex);
                }
            }
        }
        if (!found) {
            return null;
        }
        return reconstructPath(v1, v2, previousVertices);
    }


    /**
     * Reconstruye  el camino mas corto entre v1 y v2 utilizando el mapa de padres.
     *
     * @param v1     el vértice origen.
     * @param v2     el vértice destino.
     * @param parentMap el mapa de padres.
     * @return lista con la secuencia de vértices del camino mas corto entre "v1" y "v2".
     */

    private List<V> reconstructPath(V v1,V v2, Map<V, V> parentMap) {
        List<V> path = new ArrayList<>();
        V current = v2;
        while (current !=null && !current.equals(v1)) {
            path.add(current);
            current = parentMap.get(current);
        }
        if (current == null) {
            return null;
        }

        path.add(v1);
        Collections.reverse(path);
        return path;
    }
}



