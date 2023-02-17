/*

This is a class that has some of the operations that can be done with a matrix

*/

public class MatrixService {

    // Generates the product of the scalar multiplication between m and a scalar k
    public static double[][] scalarMultiplication(double[][] m, double k) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = m[i][j] * k; // The scalar multiplication is the multplication of all elements in a given matrix and a given scalar K
            }
        }
        
        return m;
    }

    // Generates the tranpose of a given matrix m
    public static double[][] transposition(double[][] m) {
        
        double[][] transpose = new double[m[0].length][m.length]; // The transpose of a matrix M ixj is a matrix M jxi

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                transpose[j][i] = m[i][j]; // i = j and j = i
            }
        }

        return transpose;
    }

    // Generates the identity matrix of a given order
    public static double[][] genereateIdentityMatrix(int order) {

        double[][] identity = new double[order][order];

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

        return scalarMultiplication(adjoint(m), (1 / det(m)));

     }

    public static double[][] adjoint(double[][] m) {

        double[][] adjoint = new double[m.length][m[0].length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                adjoint[i][j] = cofactor(m, i+1, j+1);
            }
        }

        return transposition(adjoint);        

    }

    // Generates the minor of an element i h 1 and j = column from the given matrix
    public static double[][] minor(double[][] m, int row,  int column) {

            if (isSingleton(m)) { // A singleton matrix element cannot have a minor
                return null;
            }

            double[][] minor = new double[m.length - 1][m[0].length - 1];
    
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {

                    if (i == row - 1) {
                        continue;

                    } else if (i < row - 1) {

                        if (j == column - 1) {
                            continue;

                        } else if (j < column) {

                            minor[i][j] = m[i][j];

                        } else {

                            minor[i][j - 1] = m[i][j];

                        }

                    } else {

                        if (j == column - 1) {
                            continue;

                        } else if (j < column) {

                            minor[i - 1][j] = m[i][j];

                        } else {

                            minor[i - 1][j - 1] = m[i][j];

                        }

                    }

                }
            }
            return minor;
    }

    // Calculates the cofactor of an element i = 1 and j = column from the given matrix
    public static double cofactor(double[][] m, int row, int column) {

        return (double) Math.pow((-1), row + column) * det(minor(m, row, column));

    }

    // Calculates the determinant of a square matrix by using Laplace's expansion whith the elements in the first row
    public static double det(double[][] m) throws RuntimeException {

        double sum = 0;

        if (isSingleton(m)) { 
            return m[0][0]; // The determinant of a singleton matrix is it's only element
        } else if (!isSquare(m)) {
            throw new RuntimeException("This is not a square matrix");
        }

        for (int i = 0; i < m[0].length; i++) {

            sum += m[0][i] * cofactor(m, 1, i+1);
            
        }

        return sum;

    }
    
}