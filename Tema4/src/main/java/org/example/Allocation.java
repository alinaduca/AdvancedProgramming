package org.example;
import java.util.*;

public class Allocation {
    private LinkedList<Student> students;
    private TreeSet<Project> projects;
    private Map<Student, Project> allocation;

    public Allocation(LinkedList<Student> students, TreeSet<Project> projects) {
        this.students = students;
        this.projects = projects;
        this.allocation = new HashMap<>();
    }

    public void allocateProjects() {
        //Sortez studentii in ordine crescatoare
        students.sort(Comparator.comparingInt(s -> s.getAdmissibleProjects().size()));

        //Atribui proiecte studentilor
        for(Student student : students) {
            for(Project project : student.getAdmissibleProjects()) {
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
