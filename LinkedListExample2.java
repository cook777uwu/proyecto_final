
class Node {
    //int data;       // valor guardado
    int ID;   // ID del estudiante
    String nombre; // nombre del alumno
    String carrera; // carrera del curso
    int semestre;   // semestre del curso   
    //ejemplos para guardar más datos en nodos
    Node next;      // referencia al siguiente nodo

    Node(int ID, String nombre, String carrera, int semestre) {
        this.ID = ID;
        this.nombre = nombre;
        this.carrera = carrera;
        this.semestre = semestre;
        this.next = null;
    }
}   

// Clase que representa la lista ligada
class LinkedList {
    Node head; // primer nodo (inicio de la lista)

    // Agregar un elemento al final
    public void add(int ID, String nombre, String carrera, int semestre) {
        Node newNode = new Node(ID, nombre, carrera, semestre);

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
    public void remove(int ID) {
        if (head == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        // si el nodo a eliminar es el primero
        if (head.ID == ID) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.ID != ID) {
            current = current.next;
        }
        //si no existe el elemento
        if (current.next == null) {
            System.out.println("Elemento no encontrado: " + ID);
        } else {
            current.next = current.next.next;
        }
    }

    // Mostrar todos los elementos de la lista
    public void display() {
        Node current = head;
        System.out.print("Lista: ");
        while (current != null) {
            System.out.print(current.ID + "|" +
            current.nombre + "|" +
            current.carrera + "|" +
            current.semestre + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

// Programa principal para probar la lista
public class LinkedListExample2 {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.add( 1, "Juan", "Ingeniería", 2);
        list.add( 2, "María", "Medicina", 4);
        list.add( 3, "Luis", "Derecho", 6);
        list.display();

        list.remove(2);
        list.display();

        list.add( 4, "Ana", "Arquitectura", 1);
        list.display();
    }
}