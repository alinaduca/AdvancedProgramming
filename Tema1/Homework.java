public class Homework {
    public static void main(String args[]) {
        long startNano = System.nanoTime();
        long startMil = System.currentTimeMillis();
        if(args.length < 1) {
            System.out.println("Nu a fost dat niciun numar la linia de comanda.");
            System.exit(1);
        }
        else {
            String digits = "0123456789";
            char c;
            int index;
            for(int i = 0; i < args[0].length(); i++) {
                c = args[0].charAt(i);
                if(c == '-' && i == 0)
                    continue;
                index = digits.indexOf(c);
                if(index < 0){
                    System.out.println("Argumentul nu este un numar intreg.");
                    System.exit(1);
                }
            }
            int n;
            n = Integer.parseInt(args[0]);
            System.out.println("Argumentul a fost validat cu succes!");
            System.out.println(n);

            int[][] matrix = new int[n][n];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    matrix[i][j] = (i + j) % (n) + 1;
                    if(n <= 30_000)
                        System.out.print(matrix[i][j] + " ");
                }
                if(n <= 30_000)
                    System.out.println();
            }
            if(n <= 30_000)
                System.out.println("Obiectele string");
            for(int i = 0; i < n; i++) {
                String row = "", column = "";
                for(int j = 0; j < n; j++) {
                    row = row.concat(Integer.toString(matrix[i][j]));
                    column = column.concat(Integer.toString(matrix[j][i]));
                }
                System.out.println("Linia " + i);
                if(n <= 30_000) {
                    System.out.println("Pentru linia " + i + ": " + row);
                    System.out.println("Pentru coloana " + i + ": " + column);
                }
            }
            if(n > 30_000) {
                long endNano = System.nanoTime();
                long endMil = System.currentTimeMillis();
                System.out.println(endNano - startNano);
                System.out.println(endMil - startMil);
            }
        }
    }
}
