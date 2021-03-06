import java.util.Scanner;

public class SudokuFinal 
{
    static int cont=0;

/****************************
*                           *
*   Integrantes:            *
*                           *
*   Benjamín Díaz           *
*   Gastón Lara             *
*   Juan Muñoz              *
*   Francisca Palma         *
*   Camila Pinto            *
*                           *
****************************/

    public static void main(String[] args) 
    {
        int matriz[][];
        System.out.println("Ingrese Sudoku.\n");
        matriz = leerMatriz();
        //La posición de la celda es un arreglo tamaño 2, donde c[0] = x y c[1] = y.
        backtracking(matriz, new int[] { 0, -1 });
        System.out.println("\n\nNo hay solución.\n");
        imprimirMatriz(new int[9][9]); //Matriz de ceros.


        // write your code here
        int largo;
        LinkedList<Integer> numeros = new LinkedList<>();
        Scanner lala = new Scanner(System.in);

        System.out.println("Ingrese el largo: ");
        largo = lala.nextInt();
        //genera el arreglo que paja ingresarlo
        for (int i = 0; i < largo; i++) {
            numeros.add((int) (Math.random() * largo * 2) + 1);
        }
        //lo ordena
        Collections.sort(numeros);

        System.out.println("Ingrese el numero: ");
        int n= lala.nextInt();
        //solo para ver el arreglo creado
        for (int i : numeros) {
            System.out.print(i+" ");
        }
        System.out.println();
        //borrando los mayores
        LinkedList<Integer> num2= new LinkedList<>();
        for (int i=0;i<numeros.size();i++) {
            if(numeros.get(i)<=n){
                num2.add(numeros.get(i));
            }
            else{
                break;
            }
        }

        //para ver el arreglo nuevo c:
        for (int i : num2) {
            System.out.print(i+" ");
        }
    }

    public static void backtracking(int[][] m, int[] celda)
    {
        cont++;
        if (Reject(m, celda)) return;
        if (Accept(m, celda)) output(m);
        int[] sC = siguienteCelda(m, celda);
        if (First(m, celda))
            do backtracking(m, sC);
        while (Next(m , sC));
    }

    static boolean Reject(int[][] m, int[] c) 
    {
        int x=c[0], y=c[1];
        if (y>=0)
        {
            if (m[x][y] > 0) {
            for (int i = 0; i < 9; i++)  //Comparando con la fila y columna de c.
            {
                if (i != x && (m[i][y] == m[x][y]))
                {
                    return true;
                }

                if (i != y && (m[x][i] == m[x][y])) 
                {
                    return true;
                }
            }

            int primerX = x / 3 * 3, primerY = y / 3 * 3;

            for (int i = primerX; i < primerX + 3; i++) //Comparando en el subconjunto de 3x3.
                for (int j = primerY; j < primerY + 3; j++) 
                {
                    if (x != i && y != j && m[x][y] == m[i][j]) 
                    {
                        return true;
                    }

                }
            }
        }
        return false;
    }

    static boolean Accept(int[][] m, int[] c) 
    {
        if (siguienteCelda(m, c) == null) //Si no quedan celdas vacías, el sudoku está completo.
        {
            int[] arr = new int[10];
            int aux=0;
            for (int i=0; i<9; i++)
            for (int j=0; j<9; j++)
            {
                aux = m[i][j];
                arr[aux]++;
                if (arr[aux]>9)
                return false;
            }
            return true;
        }
        return false;
    }

    static void imprimirMatriz(int[][] matriz) 
    {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
                System.out.print(matriz[i][j] + " ");
            System.out.println();
        }
    }


    static void output(int[][] matriz) 
    {
        System.out.println("\n\nSudoku terminado:\n");
        imprimirMatriz(matriz);
        System.out.println("Cont: " + cont);
        System.exit(0);
    }

    static boolean First(int[][] m, int[] c) 
    {
        int[] sC = siguienteCelda(m, c);
        if (sC != null)
        {
            m[sC[0]][sC[1]] = 1;
            return true;
        }
        return false;
    }

    static boolean Next(int m[][], int celda[]) 
    {
        if (m[celda[0]][celda[1]] < 9) 
        {
            m[celda[0]][celda[1]]++;
            return true;
        }
        m[celda[0]][celda[1]] = 0;
        return false;
    }

    static int[][] leerMatriz()
    {
        Scanner sc = new Scanner(System.in);
        int[][] matriz = new int[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                matriz[i][j] = sc.nextInt();
        sc.close();
        return matriz;
    }

    static int[] siguienteCelda(int[][] m, int[] c) //Busca la siguiente celda vacía.
    {
        int x = c[0], y = c[1];
        while (x != 8 || y != 8) 
        {
            if (y < 8)
            {
                y++;
            } else if (x < 8) 
            {
                y = 0;
                x++;
            }
            if (m[x][y] == 0) 
            {
                return (new int[] { x, y });
            }
        }
        return null; //No quedan celdas vacías.
    }

}