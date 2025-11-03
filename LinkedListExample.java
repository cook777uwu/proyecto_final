// Clase que representa un nodo de la lista
class Node {
    int data;       // valor guardado
    //int semestre;   // semestre del curso
    //String nombre; // nombre del curso
    //String carrera; // carrera del curso
    //ejemplos para guardar más datos en nodos
    Node next;      // referencia al siguiente nodo

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Clase que representa la lista ligada
class LinkedList {
    Node head; // primer nodo (inicio de la lista)

    // Agregar un elemento al final
    public void add(int data) {
        Node newNode = new Node(data);

        if (head == null) {   // si la lista está vacía
            head = newNode;
            return;
        }
        // hacia donde va a apuntar el nuevo nodo
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Eliminar el primer elemento que coincida con el valor
    public void remove(int data) {
        if (head == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        // si el nodo a eliminar es el primero
        if (head.data == data) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }
        //si no existe el elemento
        if (current.next == null) {
            System.out.println("Elemento no encontrado: " + data);
        } else {
            current.next = current.next.next;
        }
    }

    // Mostrar todos los elementos de la lista
    public void display() {
        Node current = head;
        System.out.print("Lista: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

// Programa principal para probar la lista
public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.add(10);
        list.add(20);
        list.add(30 );
        list.display();

        list.remove(20);
        list.display();

        list.add(40);
        list.display();
    }
}