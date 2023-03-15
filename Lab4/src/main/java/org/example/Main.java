package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //Creez o lista cu proiecte folosind stream.
        List<Project> projects = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Project("P" + i) )
                .toList();

        //Creez o lista cu studentii folosind stream.
        List<Student> students = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Student("S" + i, getProjects(projects, 3-i)))
                .toList();

        //Creez un obiect de tip LinkedList pentru studenti pentru a-i sorta ulterior, printr-un apel al metodei Collections.sort.
        List<Student> sortedStudents = new LinkedList<>(students);
        Collections.sort(sortedStudents);
        sortedStudents.forEach(System.out::println);

        //Pun toate proiectele intr-un obiect de tipul TreeSet pentru a nu le sorta explicit.
        Set<Project> sortedProjects = new TreeSet<>(projects);
        sortedProjects.forEach(System.out::println);
    }

    //Implementez o metoda care returneaza primele i proiecte (pentru a-mi furniza o lista cu proiectele de la 0 la 2-i).
    public static List<Project> getProjects(List<Project> projects, int index) {
        List<Project> newList = new ArrayList<>();
        int i = 0;
        for(Project p : projects) {
            newList.add(p);
            i++;
            if(i == index) {
                break;
            }
        }
        return newList;
    }
}
