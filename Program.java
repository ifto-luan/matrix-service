import java.util.Scanner;

public class Program {

    public static void main(final String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the matrix rows number: ");
        int n = sc.nextInt();

        System.out.print("Enter the matrix columns number: ");
        int m = sc.nextInt();

        System.out.println("\nEnter your matrix elemnts: \n");

        int[][] a = new int[n][m];

        for (int i = 0; i < a.length; i++) {

            for (int j = 0; j < a[i].length; j++) {
                
                a[i][j] = sc.nextInt();

            }

        }

        sc.close();

    }
}