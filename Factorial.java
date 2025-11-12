public class Factorial {
    static int factorial(int n)
    {   
        System.out.println(n); //acción antes de la llamada recursiva. imprime el valor actual de n
        if (n==0) //caso base
            return 1;
        else
            return n*factorial(n-1); //recursividad
    }

    public static void main(String[] args)
    {
        System.out.println(factorial(5)); // Output: 120
    }
}
