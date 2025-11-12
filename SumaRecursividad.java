public class SumaRecursividad{
    static int suma(int n)
    {
        System.out.println(n); //acción antes de la llamada recursiva. imprime el valor actual de n. impreme 5,4,3,2,1
        if(n==1)
        {
            return 1; //caso base
        }
        else
        {
            return n + suma(n-1); //recursividad. Suma n con la suma de los números anteriores.
        }
    }

    public static void main(String[] args)
    {
        System.out.println("SUMA DE LOS PRIMEROS 5 NÚMEROS: " + suma(5)); // Output: 15 (5+4+3+2+1)
    }
}