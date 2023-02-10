
public class MatrixService {

    // public static Integer det(int[][] m) {

    // }

    public static int[][] minor(int[][] m, int column) {

        int[][] minor = new int[m.length - 1][m[0].length - 1];

        for (int i = 0; i < m.length; i++) {

            if (i == 0) {
                continue;
            }

            for (int j = 0; j < m[i].length; j++) {

                if (j == column + 1) {
                    continue;
                }


            }
        }

        return minor;

    }

    public static void print(int[][] m) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m.length; i++) {

            sb.append("[");

            for (int j = 0; j < m[0].length; j++) {

                if (j == m[i].length - 1) {

                    sb.append(m[i][j]);
                    continue;
                }
                sb.append(m[i][j] + "  ");

            }
            
            sb.append("]\n");

        }

        System.out.println(sb.toString());

    }

}