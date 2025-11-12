// Clase que representa un nodo doblemente ligado
class Node {
    //int data; // valor guardado
    int ID;
    String De; // quien lo envía
    String Para; // quien lo recibe
    String Asunto; // asunto del mail
    String Cuerpo; // cuerpo del mail
    String Recibido; // fecha de recibido
    Node next;
    Node prev;

    Node(int ID, String De, String Para, String Asunto, String Cuerpo, String Recibido) {
        this.ID = ID;
        this.De = De;
        this.Para = Para;
        this.Asunto = Asunto;
        this.Cuerpo = Cuerpo;
        this.Recibido = Recibido;
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
    public void add(int ID, String De, String Para, String Asunto, String Cuerpo, String Recibido) {
        Node newNode = new Node(ID, De, Para, Asunto, Cuerpo, Recibido);

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
    public void remove(int ID) {
        if (head == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Node current = head;
        while (current != null && current.ID != ID) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Elemento no encontrado: " + ID);
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
        System.out.println();
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.ID + " , " + current.De + ", " + current.Para + " , " + current.Asunto + " , " + current.Cuerpo + " , " + current.Recibido + "] <-> ");
            //se deben imprimir en esta zon apara que pueda correr el código, NO OLVIDAR
            current = current.next;
            System.out.println();
        }
        System.out.println("null");
    }

    // Mostrar lista hacia atrás
    public void displayBackward() {
        System.out.print("Lista atrás: ");
        System.out.println();
        Node current = tail;
        while (current != null) {
            System.out.print("[" + current.ID + ", " + current.De + ", " + current.Para + " , " + current.Asunto + " , " + current.Cuerpo + " , " + current.Recibido + "] <-> ");
            current = current.prev;
            System.out.println();
        }
        System.out.println("null");
    }
}

// Programa principal
public class MyMailExample {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.add(10, "lexmor040@example.com", "juanD@example.com", "Asunto: dudas", "Cuerpo 1", "Recibido: 2X/06/2024");
        list.add(20, "lexalmb@example.com", "GuadalupeV@example.com", "Asunto: Tarea", "Cuerpo 2", "Recibido: 1X/08/2025");
        list.add(30, "cook777uwu@example.com", "botcat117@example.com", "Asunto: papeles", "Cuerpo 3", "Recibido: 5/12/2023");
        list.displayForward();

        list.remove(20);
        list.displayForward();

        list.add(40, "wudis7890@example.com", "GuironMar@example.com", "Asunto: Proyecto", "Cuerpo 4", "Recibido: 4/09/2025");
        list.displayForward();

        list.displayBackward();
    }
}