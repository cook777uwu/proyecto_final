class Pila {
    private int[] elementos;
    private int tope;
    private int capacidad;

    // Constructor
    public Pila(int tamano) {
        capacidad = tamano;
        elementos = new int[capacidad];
            tope = -1;
        }

    // Apilar
    public void push(int valor) {
        if (tope == capacidad - 1) {
            System.out.println("La pila está llena");
            return;
        }
        elementos[++tope] = valor;
    }

    // Desapilar
    public int pop() {
        if (estaVacia()) {
            System.out.println("La pila está vacía");
            return -1;
        }
        return elementos[tope--];
    }

    // Ver el tope
    public int peek() {
        if (estaVacia()) {
            System.out.println("La pila está vacía");
            return -1;
        }
        return elementos[tope];
    }

    // Verificar si está vacía
    public boolean estaVacia() {
        return tope == -1;
    }
}

public class PilaManual {
    public static void main(String[] args) {
        Pila pila = new Pila(5);

        pila.push(5);
        pila.push(10);
        pila.push(15);
        pila.push(20);
        pila.push(25);

        System.out.println("Elemento desapilado: " + pila.pop());
        System.out.println("Elemento desapilado: " + pila.pop());
        System.out.println("Elemento desapilado: " + pila.pop());
        System.out.println("Elemento desapilado: " + pila.pop());
        System.out.println("Elemento desapilado: " + pila.pop());
        System.out.println("Elemento desapilado: " + pila.pop());

        System.out.println("Tope actual: " + pila.peek());
        
    }
}