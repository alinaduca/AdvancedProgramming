package org.example;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import java.util.ArrayList;
import java.util.List;
import org.example.models.*;

import org.chocosolver.solver.variables.SetVar;
import org.example.repos.AlbumRepository;
public class ChocoSolver {
    public static void main(String[] args) {
        Album[] albums = {
                new Album("Nirvana", 1990, null),
                new Album("Tenerife", 1980, null),
                new Album("Nefertiti", 1990, null)
        };
        int k = 3;
        int p = 2;
        // Create a Choco model
        Model model = new Model("Album Solver");

        // Create an array of set variables to represent the albums
        SetVar[] albumSets = model.setVarArray("Albums", albums.length, 0, 1);

        // Create a variable to represent the selected albums
        SetVar selectedAlbums = model.setVar("Selected Albums", 0, albums.length);

        // Add constraints for the selected albums
        model.count(selectedAlbums, albumSets, model.intVar(k)).post();

        // Add constraints for albums having titles that start with the same letter
        for (int i = 0; i < albums.length; i++) {
            for (int j = i + 1; j < albums.length; j++) {
                model.arithm(albumSets[i], "=", albumSets[j]).post();
                model.member(albums[i].getTitle().charAt(0), selectedAlbums).post();
                model.member(albums[j].getTitle().charAt(0), selectedAlbums).post();
            }
        }

        // Add constraints for albums released in the same period of time
        for (int i = 0; i < albums.length; i++) {
            for (int j = i + 1; j < albums.length; j++) {
                IntVar yearDiff = model.intVar(Math.abs(albums[i].getReleaseYear() - albums[j].getReleaseYear()));
                model.arithm(yearDiff, "<=", p).post();
                model.member(yearDiff, selectedAlbums).post();
            }
        }

        // Create a solver and solve the model
        Solver solver = model.getSolver();
        solver.showStatistics();
        solver.showSolutions();

        // Find all solutions
        while (solver.solve()) {
            System.out.println("Solution:");
            for (int i = 0; i < albums.length; i++) {
                if (albumSets[i].getValue() == 1) {
                    System.out.println(albums[i]);
                }
            }
            System.out.println();
        }



//        Model model = new Model("Album Solver");
//        IntVar[] vars = model.intVarArray("Q", albums.size(), 1000, 2000, false);
//        BoolVar[] selected = model.boolVarArray("selected", albums.size());
//        for (int i = 0; i < albums.size(); i++) {
//            for (int j = i + 1; j < albums.size(); j++) {
//                model.arithm(albums.get(i).getTitle()., "=", albums.get(j)).post();
//                model.member(albums[i].getTitle().charAt(0), selectedAlbums).post();
//                model.member(albums[j].getTitle().charAt(0), selectedAlbums).post();
//            }
//        }
//        model.allDifferent(vars).post();
//        for (int i = 0; i < albums.size() - 1; i++) {
//            for (int j = i + 1; j < albums.size(); j++) {
//                boolean cond = Math.abs(albums.get(i).getReleaseYear() - albums.get(i).getReleaseYear()) <= 3;
//                int conditie = 0;
//                if(cond) {
//                    conditie = 1;
//                }
//                model.arithm(selected[i], "=", conditie).post();
//                model.arithm(selected[j], "=", conditie).post();
//            }
//        }
//        Solution solution = model.getSolver().findSolution();
//        if(solution != null) {
//            System.out.println(solution.toString());
//        }
    }
}
