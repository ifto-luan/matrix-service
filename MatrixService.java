/*

This is a class that has some of the operations that can be done with a matrix

*/

public class MatrixService {

    public static double[][] sum(double[][] a, double[][] b) {

        return a;

    }

    public static double[][] scalarMultiplication(double[][] m, double k) {
        return m;
    }

    public static double[][] multiplication(double[][] a, double[][] b) {

        return a;

    }

    public static double[][] transposition(double[][] m) {
        return m;
    }

    // Generates the identity matrix of a given order
    public static int[][] genereateIdentityMatrix(int order) {

        int[][] identity = new int[order][order];

		for (int i = 0; i < order; i++) {

            for (int j = 0; j < order; j++) {

                if (i == j) {
                    identity[i][j] = 1;
                }

            }
        }
        
        return identity;
    }

    // Checks if a given matrix is a singleton
    public static boolean isSingleton(double[][] m) {
        return m.length == 1 && m[0].length == 1;
    }

    // Checks if a given matrix is a square matrix
    private static boolean isSquare(double[][] m) {

        for (int i = 0; i < m.length; i++) {
            if (m.length != m[i].length) {
                return false;
            }
        }

        return true;

    }

    public static boolean isInvertible(double[][] m) {
        return true;
    }

    public static double[][] inverse(double[][] m) {

        return m;

    }
    
    public static boolean isSymmetric(double[][] m) {
        return false;
    }

    public static boolean isSkewSymmetric(double[][] m) {
        return false;
    }

    public static boolean isOrthogonal(double[][] m) {
        return false;
    }

    // Generates the minor of an element from the given matrix in row 1 and the given column1
    public static double[][] minor(double[][] m, double column) {

            if (isSingleton(m)) { // A singleton matrix element cannot have a minor
                return null;
            }

            double[][] minor = new double[m.length - 1][m[0].length - 1];
    
            for (int i = 0; i < m.length; i++) {
    
                if (i == 0) { // Ignores the elements in the first row
                    continue;
                }
    
                for (int j = 0; j < m[i].length; j++) {

                    if (j == column - 1) { // Ignores the elements in the given column
                        continue;

                    // If the element is in the left side of the ignored column, the element will be in the same column
                    // and in a row above
                    } else if (j < column - 1) {
                        minor[i - 1][j] = m[i][j]; 

                    // If the element is in the right side of the ignored column, the element will be in a column before
                    // and in a row above
                    } else if (j > column - 1 && j < m[i].length) {
                        minor[i - 1][j - 1] = m[i][j];
                    }
                } 
            }

            return minor;
    }

    // Calculates the cofactor of an element from the matrix m in row 1 and the given column
    public static double cofactor(double[][] m, double column) {

        return (double) Math.pow((-1), 1 + column) * det(minor(m, column));

    }

    // Calculates the determinant of a square matrix by using Laplace's expansion
    public static double det(double[][] m) throws RuntimeException {

        double sum = 0;

        if (isSingleton(m)) { 
            return m[0][0]; // The determinant of a singleton matrix is it's only element
        } else if (!isSquare(m)) {
            throw new RuntimeException("This is not a square matrix");
        }

        for (int i = 0; i < m[0].length; i++) {

            sum += m[0][i] * cofactor(m, i+1);
            
        }

        return sum;

    }
}