public class MatrixService {

    public static int det(int[][] m) {

        if (m.length == 1 && m[0].length == 1) {
            return m[0][0];
        }

        int sum = 0;

        for (int i = 0; i < m[0].length; i++) {

            sum += m[0][i] * Math.pow((-1), (1 + (i+1))) * det(minor(m, i+1));

        }

        return sum;

    }

    private static int[][] minor(int[][] m, int column) {

        try {

            if (m.length != m[0].length) {
                throw new Exception("This is not a square matrix");
            } else if (m.length == 1 && m[0].length == 1) {
                return m;
            }

            int[][] minor = new int[m.length - 1][m[0].length - 1];
    
            for (int i = 0; i < m.length; i++) {
    
                if (i == 0) {
                    continue;
                }
    
                for (int j = 0; j < m[i].length; j++) {

                    if (j == column - 1) {
                        continue;

                    } else if (j < column - 1) {
                        minor[i - 1][j] = m[i][j];

                    } else if (j > column - 1 && j < m[i].length) {
                        minor[i - 1][j - 1] = m[i][j];
                    }
                } 
            }

            return minor;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}