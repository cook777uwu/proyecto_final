import java.util.Stack;

// Clase Estudiante
class Estudiante {
    private String nombre;
    private int edad;
    private String matricula;

    public Estudiante(String nombre, int edad, String matricula) {
        this.nombre = nombre;
        this.edad = edad;
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años, matrícula: " + matricula + ")";
    }
}

public class PilaEstudiantes {
    public static void main(String[] args) {
        // Crear una pila de estudiantes
        Stack<Estudiante> pila = new Stack<>();

        // Apilar estudiantes
        pila.push(new Estudiante("Ana López", 20, "A001"));
        pila.push(new Estudiante("Carlos Pérez", 22, "A002"));
        pila.push(new Estudiante("Lucía Gómez", 19, "A003"));

        System.out.println("Pila actual:");
        for (Estudiante e : pila) {
            System.out.println(" - " + e);
        }

        // Ver el último estudiante (peek)
        System.out.println("\nEstudiante en la cima: " + pila.peek());

        // Desapilar (pop)
        Estudiante removido = pila.pop();
        System.out.println("\nSe desapiló: " + removido);

        System.out.println("\nPila después del pop:");
        for (Estudiante e : pila) {
            System.out.println(" - " + e);
        }

        // Verificar si está vacía
        System.out.println("\n¿La pila está vacía? " + pila.empty());
    }
}