// Clase que representa un nodo doblemente ligado
class Node {
    int data; // valor guardado
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
        // inicializar referencias
    }
}

// Clase principal de la lista doblemente ligada
class DoublyLinkedList {
    Node head; // inicio de la lista
    Node tail; // fin de la lista

    // Agregar un elemento al final
    public void add(int data) {
        Node newNode = new Node(data);

        if (head == null) { // lista vacía
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode; // conectar al final
            newNode.prev = tail;
            tail = newNode; // mover el tail
        }
    }

    // Eliminar un elemento por su valor
    public void remove(int data) {
        if (head == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Node current = head;
        while (current != null && current.data != data) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Elemento no encontrado: " + data);
            return;
        }

        // Caso 1: eliminar el primero
        if (current == head) {
            head = current.next;
            if (head != null) head.prev = null;
        }
        // Caso 2: eliminar el último
        else if (current == tail) {
            tail = current.prev;
            if (tail != null) tail.next = null;
        }
        // Caso 3: eliminar en medio
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
    }

    // Mostrar lista hacia adelante
    public void displayForward() {
        System.out.print("Lista adelante: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Mostrar lista hacia atrás
    public void displayBackward() {
        System.out.print("Lista atrás: ");
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }
}

// Programa principal
public class DoublyLinkedListExample {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);
        list.displayForward();

        list.remove(20);
        list.displayForward();

        list.add(40);
        list.displayForward();

        list.displayBackward();
    }
}