package org.example;
import java.util.*;
import java.util.stream.Collectors;

public class Allocation {
    private List<Student> students;
    private List<Project> projects;
    private Map<Student, List<Project>> adj;
    private Map<Student, Project> allocation;

    public Allocation(List<Student> students, List<Project> projects) {
        this.students = students;
        this.projects = projects;
        this.allocation = new HashMap<>();
        this.adj = new HashMap<>();
        List<Project> p = new ArrayList<>();
        for (Student s : this.students) {
            adj.put(s, p);
        }
    }

    public void addEdge(Node n1, Node n2) {
        Student student = (Student) n1;
        Project project = (Project) n2;
        List<Project> newProjects = adj.get(student);
        newProjects.add(project);
        adj.put(student, newProjects);
    }

    public void allocateProjects() {
        //Sortez studentii in ordine crescatoare
        List<Student> sortedStudents = students.stream()
                .sorted((s1, s2) -> Integer.compare(adj.get(s1).size(), adj.get(s2).size()))
                .toList();

        //Atribui proiecte studentilor
        for(Student student : sortedStudents) {
            for(Project project : adj.get(student)) {
                if(projects.contains(project) && !allocation.containsValue(project)) {
                    allocation.put(student, project);
                    projects.remove(project);
                    break;
                }
            }
        }
    }

    public Map<Student, Project> getAllocation() {
        return this.allocation;
    }
}
