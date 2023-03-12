package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Project> projects = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Project("P" + i) )
                .toList();
        List<Student> students = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Student("S" + i, getProjects(projects, 3-i)))
                .toList();
        List<Student> sortedStudents = new LinkedList<>(students);
        Collections.sort(sortedStudents);
        sortedStudents.forEach(System.out::println);
        Set<Project> sortedProjects = new TreeSet<>(projects);
        sortedProjects.forEach(System.out::println);
    }

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