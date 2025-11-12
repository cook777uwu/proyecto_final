// Ejemplo más simple de arbol Binario en Java
class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;

    Nodo(int valor) {
        this.valor = valor;
        izquierdo = null;
        derecho = null;
    }
}

class ArbolBinario {
    Nodo raiz;

    // Insertar un nuevo nodo en el árbol
    void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    Nodo insertarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }
        //aquí se evita insertar valores duplicados
        //también se entiende que los valores menores van a la izquierda y los mayores a la derecha
        if (valor < actual.valor) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }

        return actual;
    }

    // Recorrido Inorden (izquierda, raíz, derecha)
    void inorden(Nodo nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            inorden(nodo.derecho);
        }
    }
}

public class ArbolDemo {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();

        // Insertamos algunos valores
        arbol.insertar(5);
        arbol.insertar(3);
        arbol.insertar(7);
        arbol.insertar(2);
        arbol.insertar(4);
        arbol.insertar(6);
        arbol.insertar(8);
        arbol.insertar(16);
        arbol.insertar(0);

        System.out.print("Recorrido Inorden del árbol: ");
        arbol.inorden(arbol.raiz);
    }
}