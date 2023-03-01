public class SquareMatrix {

    public int[][] elements;
    public final int order;

    public SquareMatrix(int order) {

        if (order <= 0)
            throw new RuntimeException("Enter a number equals or greater than 1");

        this.order = order;
        elements = new int[order][order];
    }

    // Set the element in the given position
    public void set(int element, int row, int column) {

        if ((row > order || column > order) || (row <= 0 || column <= 0))
            throw new RuntimeException("Enter a row or column that exists");

        elements[row - 1][column - 1] = element;
    }

    // Set elements in the given row
    public void setRow(int[] elements, int row) {

        if ((row > order || row <= 0) || elements.length > order)
            throw new RuntimeException("Enter a row or column that exists");

        this.elements[row - 1] = elements;
    }

    // Get the element in the given position
    public int get(int row, int column) {

        if ((row > order || column > order) || (row <= 0 || column <= 0))
            throw new RuntimeException("Enter a row or column that exists");

        return elements[row - 1][column - 1];
    }

    // Multiply all elements by scalar x
    public void scalarMultiplication(int x) {
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                elements[i][j] *= x;
            }
        }
    }

    // Return the transpose of this matrix
    public SquareMatrix transpose() {
        SquareMatrix transpose = new SquareMatrix(this.order);

        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {

                transpose.set(elements[j][i], i + 1, j + 1); // Rows turn into columns and columns turn into rows

            }
        }

        return transpose;
    }

    public boolean isInvertible() {
        return det() != 0;
    }

    public boolean isSymmetric() {
        return this.transpose().compare(this);
    }

    // public boolean isSkewSymmetric() {

    // }

    // Returns the minor of an element in this matrix
    private SquareMatrix minor(int row, int column) {

        SquareMatrix minor = new SquareMatrix(order - 1);

        for (int i = 1; i <= order; i++) {
            if (i == row)
                continue;
            for (int j = 1; j <= order; j++) {

                if (j == column)
                    continue;
                else if (i < row && j < column)
                    minor.set(this.get(i, j), i, j);
                else if (i < row && j > column)
                    minor.set(this.get(i, j), i, j - 1);
                else if (i > row && j < column)
                    minor.set(this.get(i, j), i - 1, j);
                else if (i > row && j > column)
                    minor.set(this.get(i, j), i - 1, j - 1);

            }
        }

        return minor;
    }

    // Calculates the cofactor of an element in this matrix
    private int cofactor(int row, int column) {

        return (row + column) % 2 == 0 ? this.minor(row, column).det() : (-1) * this.minor(row, column).det();

    }

    // Calculates the determinant of this matrix using Laplace's expansion in the
    // first row
    public int det() {

        if (this.order == 1)
            return get(1, 1);

        int sum = 0;
        for (int i = 1; i <= order; i++) {

            sum += this.get(1, i) * this.cofactor(1, i);
        }
        return sum;

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < order; i++) {
            sb.append("[");
            for (int j = 0; j < order; j++) {

                sb.append(get(i + 1, j + 1));
                if (j < order - 1)
                    sb.append("\t");

            }

            sb.append("]");
            if (i < order)
                sb.append("\n");

        }

        return sb.toString();

    }

    public boolean compare(SquareMatrix other) {

        if (this.order != other.order)
            return false;

        for (int i = 1; i <= order; i++) {
            for (int j = 1; j <= order; j++) {
                if (this.get(i, j) != other.get(i, j))
                    return false;
            }
        }

        return true;

    }
}