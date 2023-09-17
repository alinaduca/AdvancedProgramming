package org.example;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import java.util.ArrayList;
import java.util.List;
import org.example.models.Album;
import org.example.repos.AlbumRepository;

public class AlbumAssignmentProblem {
//    public static void main(String[] args) {
//        List<Album> albums = createAlbums(); // Your album repository logic here
//        int k = 3; // Number of albums to select
//        int p = 2; // Maximum difference in release years
//
//        // Create Choco model
//        Model model = new Model("AlbumAssignmentProblem");
//
//        // Variables
//        List<IntVar> selectedIndices = new ArrayList<>();
//        for (int i = 0; i < albums.size(); i++) {
//            selectedIndices.add(model.intVar("Selected_" + i, 0, 1));
//        }
//
//        // Constraints
//        IntVar[] selectedIndicesArray = selectedIndices.toArray(new IntVar[0]);
//        model.sum(selectedIndicesArray, "=", k).post(); // Select k albums
//
//        for (int i = 0; i < albums.size(); i++) {
//            Album album = albums.get(i);
//            for (int j = i + 1; j < albums.size(); j++) {
//                Album otherAlbum = albums.get(j);
//                model.ifThen(
//                        model.and(model.arithm(selectedIndicesArray[i], "=", 1),
//                                model.arithm(selectedIndicesArray[j], "=", 1)),
//                        model.and(model.arithm(album.))
////                        model.and(model.arithm(album.getReleaseYear(), "-", otherAlbum.getReleaseYear(), "<=", p),
////                                model.arithm(album.getReleaseYear(), "-", otherAlbum.getReleaseYear(), ">=", -p)
//                );
//            }
//        }
//
//        // Solve the model
//        Solution solution = model.getSolver().findSolution();
//        if (solution != null) {
//            List<Album> selectedAlbums = new ArrayList<>();
//            for (int i = 0; i < albums.size(); i++) {
//                if (solution.getIntVal(selectedIndicesArray[i]) == 1) {
//                    selectedAlbums.add(albums.get(i));
//                }
//            }
//
//            // Process the selected albums
//            System.out.println("Selected Albums:");
//            for (Album album : selectedAlbums) {
//                System.out.println(album.getTitle());
//            }
//        } else {
//            System.out.println("No solution found.");
//        }
//    }
//
//    private static List<Album> createAlbums() {
//        AlbumRepository albumRepository = new AlbumRepository();
//        List<Album> list = new ArrayList<>();
//        for(int i = 1; i < 1001; i++) {
//            list.add(albumRepository.findById((long) i));
//        }
//        return list;
//    }
//
//}



//
//
    public static void main(String[] args) {
////        int n = 8;
////        Model model = new Model("AlbumAssignmentProblem");
////        IntVar[] vars = new IntVar[n];
////        for(int q = 0; q < n; q++){
////            vars[q] = model.intVar("Q_"+q, 1, n);
////        }
////        for(int i  = 0; i < n-1; i++){
////            for(int j = i + 1; j < n; j++){
////                model.arithm(vars[i], "!=",vars[j]).post();
////                model.arithm(vars[i], "!=", vars[j], "-", j - i).post();
////                model.arithm(vars[i], "!=", vars[j], "+", j - i).post();
////            }
////        }
////        Solution solution = model.getSolver().findSolution();
////        if(solution != null){
////            System.out.println(solution.toString());
////        }

        // Sample data
        List<Album> albums = createAlbums();

        int k = 3;  // Minimum number of albums
        int p = 2;  // Maximum difference in release years

        // Create Choco solver model
        Model model = new Model("Albums");

        // Create variables
        IntVar[] releaseYears = model.intVarArray("releaseYears", albums.size(), 0, 3000);
        IntVar[] firstCharacter = model.intVarArray("firstCharacter", albums.size(), 65, 122);

        // Set constraints
        for (int i = 0; i < albums.size() - 1; i++) {
            for (int j = i + 1; j < albums.size(); j++) {
                System.out.println(i + " " + j);
                model.or(model.arithm(releaseYears[i], "-", releaseYears[j], "<=", p), model.arithm(releaseYears[j], "-", releaseYears[i], "<=", p)).post();
                model.and(model.arithm(firstCharacter[i], "=", firstCharacter[j]), model.arithm(firstCharacter[i], "=", albums.get(i).getTitle().charAt(0))).post();
            }
        }
//        Solution solution = model.getSolver().findSolution();
//        // Find a solution
//        if (solution != null) {
//            for (int i = 0; i < albums.size(); i++) {
//                System.out.println(albums.get(i).getTitle() + " (" + albums.get(i).getReleaseYear() + ")");
//            }
//        } else {
//            System.out.println("No solution found.");
//        }
//
////
////        // Select at least k albums
////        model.sum(selected, ">=", k).post();
////
////        // Albums with the same starting letter
////        for (int i = 0; i < albums.size() - 1; i++) {
////            for (int j = i + 1; j < albums.size(); j++) {
////                String firstLetterI = albums.get(i).getTitle().substring(0, 1);
////                String firstLetterJ = albums.get(j).getTitle().substring(0, 1);
////                BoolVar sameLetter = model.boolVar();
////                model.arithm(sameLetter, "=", firstLetterI.equals(firstLetterJ) ? 1 : 0).post();
////                model.ifThen(model.arithm(selected[i], "=", 1), model.arithm(selected[j], "=", 1), sameLetter);
////            }
////        }
////
////        // Albums released within a maximum difference of p years
////        for (int i = 0; i < albums.size() - 1; i++) {
////            for (int j = i + 1; j < albums.size(); j++) {
////                IntVar diff = model.intVar(Math.abs(albums.get(i).getReleaseYear() - albums.get(j).getReleaseYear()));
////                model.arithm(diff, "<=", p).post();
////                model.ifThen(model.arithm(selected[i], "=", 1), model.arithm(selected[j], "=", 1), model.arithm(diff, ">", 0));
////            }
////        }
////
////        // Find a solution
////        Solution solution = model.getSolver().findSolution();
////        if (solution != null) {
////            for (int i = 0; i < albums.size(); i++) {
////                if (solution.getIntVal(selected[i]) == 1) {
////                    System.out.println(albums.get(i).getTitle() + " (" + albums.get(i).getReleaseYear() + ")");
////                }
////            }
////        } else {
////            System.out.println("No solution found.");
////        }
//    }
//
////    public static void main(String[] args) {
////        List<Album> albums = createAlbums();
////        int k = 3; // Number of albums to select
////        int p = 2; // Maximum difference in release years
////
////        // Create Choco model
////        Model model = new Model("Albums");
////
////        BoolVar[] selected = model.boolVarArray(albums.size());
////
////        for (int i = 0; i < albums.size(); i++) {
////            for (int j = i + 1; j < albums.size(); j++) {
////                int releaseYearDiff = Math.abs(albums.get(i).getReleaseYear() - albums.get(j).getReleaseYear());
////                model.arithm(selected[i], "=", selected[j]).post(); // Albumele selectate în același timp
////                model.arithm(releaseYearDiff, "<=", 2).post(); // Diferența dintre anii de lansare este de maxim 2
////            }
////        }
////        IntVar = model.intVa
//
////        IntVar[] selected = model.intVarArray("selected", albums.size(), 0, 1);
////        IntVar[] releaseYears = model.intVarArray("releaseYears", albums.size(), 0, 3000);
////
////        for (int i = 0; i < albums.size(); i++) {
////            model.element(releaseYears[i], albums.get(i).getReleaseYear(), releaseYears);
////            model.arithm(selected[i], "=", 1).reifyWith(releaseYears[i]);
////        }
//
////        BoolVar[] selected = model.boolVarArray(albums.size());
////        BoolVar[][] conflicts = model.boolVarMatrix(albums.size(), albums.size());
////        BoolVar[] selectedConflicts = model.boolVarArray(albums.size());
////
////        for(Long i = albums.get(0).getId(); i < albums.get(albums.size()-1).getId(); i++) { //Albumele de la 1 la 1000
////            Album album = albums.get(Math.toIntExact(i));
////            //Albumul de la pozitia i
////            selected[Math.toIntExact(i)] = model.boolVar("Selected_" + i);
////            System.out.println(selected[Math.toIntExact(i)]);
////            Constraint constraint = model.and(getTitleStartsWithLetterConstraint(album.getTitle().charAt(0)), getReleaseYearConstraint(album, albums, p));
////            model.post(constraint);
//////                  selected[Math.toIntExact(i)],
//////                    model.and(getTitleStartsWithLetterConstraint(album.getTitle().charAt(0)), getReleaseYearConstraint(album, albums, p)),
//////                    selected[Math.toIntExact(i)].not().and(getTitleStartsWithLetterConstraint(album.getTitle().charAt(0)))
//////            );
////
////            for (Long j = i + 1; j < albums.size(); j++) {
////                conflicts[Math.toIntExact(i)][Math.toIntExact(j)] = selected[Math.toIntExact(i)].and(selected[Math.toIntExact(j)]);
////                conflicts[Math.toIntExact(j)][Math.toIntExact(i)] = conflicts[Math.toIntExact(i)][Math.toIntExact(j)];
////            }
////            selectedConflicts[Math.toIntExact(i)] = (BoolVar) model.or(conflicts[Math.toIntExact(i)]);
////        }
////
////
////        model.sum(selected, ">=", k).post(); // Select at least k albums
////        model.or(selectedConflicts).post(); // Ensure there are no conflicts in the selected albums
////
////        // Solve the model
////        Solver solver = model.getSolver();
////        if (solver.solve()) {
////            List<Album> selectedAlbums = new ArrayList<>();
////            for (int i = 0; i < albums.size(); i++) {
////                if (selected[i].getValue() == 1) {
////                    selectedAlbums.add(albums.get(i));
////                }
////            }
////
////            // Process the selected albums
////            System.out.println("Selected Albums:");
////            for (Album album : selectedAlbums) {
////                System.out.println(album.getTitle());
////            }
////        } else {
////            System.out.println("No solution found.");
////        }
////    }
//
////    public static void main(String[] args) {
////        List<Album> albums = createAlbums();
////        int k = 3; // Number of albums to select
////        int p = 2; // Maximum difference in release years
////
////        // Create Choco model
////        Model model = new Model("AlbumAssignmentProblem");
////
////        BoolVar var = model.boolVar("selectedAlbum");
////        model.not(var);
////    }
//
    }

    private static List<Album> createAlbums() {
        AlbumRepository albumRepository = new AlbumRepository();
        List<Album> list = new ArrayList<>();
        for(int i = 1; i < 1001; i++) {
            list.add(albumRepository.findById((long) i));
        }
        return list;
    }
////
////    private static BoolVar getTitleStartsWithLetterConstraint(char letter) {
////        Model model = new Model();
////        BoolVar[] titleVars = model.boolVarArray("TitleVars", 26);
////        int letterIndex = letter - 'A';
////        titleVars[letterIndex] = model.boolVar(true);
////        model.arithm(titleVars[letterIndex], "=", 1).post();
////        return titleVars[letterIndex];
////    }
////
////    private static BoolVar getReleaseYearConstraint(Album album, List<Album> albums, int p) {
////        Model model = new Model();
////        BoolVar[] releaseYearVars = model.boolVarArray("ReleaseYearVars", albums.size());
////        for (int i = 0; i < albums.size(); i++) {
////            Album otherAlbum = albums.get(i);
////            if (otherAlbum != album) {
////                model.arithm(releaseYearVars[i], "=", Math.abs(album.getReleaseYear() - otherAlbum.getReleaseYear()) <= p ? 1 : 0).post();
////            }
////        }
////        return releaseYearVars[albums.indexOf(album)];
////    }
}
