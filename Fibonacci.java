public class Fibonacci {
    static int fib(int n)
    {   
        System.out.println(n); //acción antes de la llamada recursiva. imprime el valor actual de n
        if (n<=1) return n;
        return fib(n-1)+fib(n-2); //recursividad
    }

    public static void main(String[] args)
    {
        System.out.println(fib(6)); // Output: 8
    }
    
}
