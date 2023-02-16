/*

This is a class that has some of the operations that can be done with a matrix

*/

public class MatrixService {

    // Generates the product of the scalar multiplication between m and a scalar k
    public static double[][] scalarMultiplication(double[][] m, double k) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; i++) {
                m[i][j] = m[i][j] * k; // The scalar multiplication is the multplication of all elements in a given matrix and a given scalar K
            }
        }

        return m;
    }

    // Generates the tranpose of a given matrix m
    public static double[][] transposition(double[][] m) {
        
        double[][] transpose = new double[m[0].length][m.length]; // The transpose of a matrix M ixj is a matrix M jxi

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; i < m[0].length; j++) {
                transpose[j][i] = m[i][j]; // i = j and j = i
            }
        }

        return transpose;
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
        return m.length == 1 && m[0].length == 1; // A singleton matrix is a matrix with only one element
    }

    // Checks if a given matrix is a square matrix
    private static boolean isSquare(double[][] m) {

        for (int i = 0; i < m.length; i++) {
            if (m.length != m[i].length) { // A square matrix is a matrix where i = j
                return false;
            }
        }

        return true;

    }

    // Checks if a given matrix is invertible
    public static boolean isInvertible(double[][] m) {
        return det(m) != 0; // A matrix M is invertible if it's determinant is different from 0
    }

    // Generates the inverse of a given matrix
    public static double[][] inverse(double[][] m) {

        if(!isSquare(m)) {
            throw new RuntimeException("this is not a square matrix");
        } else if (!isInvertible(m)) {
            throw new RuntimeException("this matrix is not invertible because it's determinant is equal to 0");
        }

        double[][] inverse = new double[m.length][m[0].length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; i++) {
                inverse[i][j] = m[i][j] * det(m); // The inverse of a matrix M is the scalar multiplication between M and det(M);
            }
        }

        return inverse;

    }
    
    // Checks is a given matrix is symmetrix
    public static boolean isSymmetric(double[][] m) {
        return m.equals(transposition(m)); // A symmetric matrix is the matrix that is equals to it's transpose matrix
    }

    // Checks if a given matrix is skew-symmetric
    public static boolean isSkewSymmetric(double[][] m) {
        return m.equals(scalarMultiplication(transposition(m), (-1))); // A skew-symmetric matrix is the transpose matrix that is equivalent to it's matrix
    }

    // Checks if a given matrix is orthogonal
    public static boolean isOrthogonal(double[][] m) {
        return inverse(m).equals(transposition(m)); // A orthogonal matrix is the matrix where it's inverse is equivalent to the it's transpose matrix
    }

    // Generates the minor of an element i h 1 and j = column from the given matrix
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

    // Calculates the cofactor of an element i = 1 and j = column from the given matrix
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

    // Returns the string format of a matrix 
    public static String toString(double[][] m) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m.length; i++) {

            sb.append("[");
            for (int j = 0; j < m[i].length; j++) {
                sb.append(m[i][j]);
                if (j < m[i].length - 1) {
                    sb.append("\t");
                }
            }

            sb.append("]");
            if (i < m.length - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();

    }

    // Checks if two matrices are equivalent
    public static boolean isEquivalent(double[][] a, double[][] b) {
        return true;
    }
}