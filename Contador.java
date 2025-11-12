public class Contador {
    static void contarAtras(int n)
    {
        if(n==0) //caso base; el valor n llega a 0
        {
            System.out.println("Despegue!");
            return;
        }
        System.out.println(n); //acción antes de la llamada recursiva
        contarAtras(n-1);
    }

    public static void main(String[] args)
    {
        contarAtras(10); // Output: 10 9 8 7 6 5 4 3 2 1 Despegue!
    }
}

