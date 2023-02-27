public class Bonus {
    /*Exercitiul 1 */
    public static int getDegree(int v, int[][] regularMatrix, int numberOfVertexes) {
        int deg = 0;
        for(int i = 0; i < numberOfVertexes; i++)
            deg += regularMatrix[v][i];
        return deg;
    }
    public static void main(String[] args) {
        int n = 5;
        int[][] matrix = new int[n][n];
        int[][] power = new int[n][n];
        for(int i = 0; i < n; i++) {
            matrix[i][(i + 1) % n] = 1;
            matrix[(i + 1) % n][i] = 1;
        }
        power = matrix;
        System.out.println("Matricea de adiacenta este: ");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        for(int k = 1; k <= n; k++) { //puterile
            int[][] aux = new int[n][n];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    for(int l = 0; l < n; l++) {
                        aux[i][j] = aux[i][j] + matrix[i][l] * power[l][j];
                    }
                }
            }
            power = aux;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(power[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        /*Exercitiul 2 */
        System.out.println("Exercitiul 2:");
        int numberOfVertexes;
        numberOfVertexes = Integer.parseInt(args[0]);
        int degree;
        degree = Integer.parseInt(args[1]);
        int[][] regularMatrix = new int[numberOfVertexes][numberOfVertexes];
        if((degree % 2 == 1 && numberOfVertexes % 2 == 1) || degree > numberOfVertexes) {
            System.out.println("Nu exista un astfel de graf.");
        }
        else
        {
            for(int j = 1; j <= degree; j++) {
                regularMatrix[0][j] = 1;
                regularMatrix[j][0] = 1;
            }
            int lastPos = 2;
            for(int i = 1; i < numberOfVertexes; i++) {
                for(int j = lastPos + 1, k = getDegree(i, regularMatrix, numberOfVertexes); k < degree && j < numberOfVertexes; k++, j++) {
                    regularMatrix[i][j] = 1;
                    regularMatrix[j][i] = 1;
                    lastPos = j;
                }
                if(lastPos + 1 >= numberOfVertexes)
                    lastPos = i + 1;
            }
            for(int i = 0; i < numberOfVertexes; i++) {
                for(int j = 0; j < numberOfVertexes; j++) {
                    System.out.print(regularMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
