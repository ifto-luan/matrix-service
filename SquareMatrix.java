public class SquareMatrix {

    private int[][] elements;
    public final int order;

    public SquareMatrix(int order) {

        if (order <= 0) throw new RuntimeException("Enter a number equals or greater than 1");
        
        this.order = order;
        elements = new int[order][order];
    }

    //Set the element in a given position
    public void set(int element, int row, int column) {
        
        if ((row > order || column > order) || (row <= 0 || column <= 0)) throw new RuntimeException("Enter a row or column that exists");
        
        elements[row - 1][column - 1] = element;
    }
    
    //Get the element in a given position
    public int get(int row, int column) {
        
        if ((row > order || column > order) || (row <= 0 || column <= 0)) throw new RuntimeException("Enter a row or column that exists");

        return elements[row - 1][column - 1];
    }

    //Do the scalar multiplication by scalar x
    public void scalarMultiplication(int x) {
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                elements[i][j] *= x;
            }
        }
    }

    //Return the transpose of this matrix
    public SquareMatrix transpose() {
        SquareMatrix transpose = new SquareMatrix(this.order);

        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {

                transpose.set(elements[j][i], i+1, j+1); //Rows turn into columns and columns turn into rows

            }
        }

        return transpose;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < order; i++) {
            sb.append("[");
            for (int j = 0; j < order; j++) {

                sb.append(get(i+1, j+1));
                if (j < order - 1) sb.append("\t");

            }

            sb.append("]");
            if (i < order) sb.append("\n");

        }

        return sb.toString();

    }

}