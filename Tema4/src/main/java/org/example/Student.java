package org.example;
import java.util.*;

public class Student implements Comparable<Student> {

    private String name;
    private List<Project> admissibleProjects;

    public Student(String name, List<Project> admissibleProjects) {
        this.name = name;
        this.admissibleProjects = admissibleProjects;
    }

    public String getName() {
        return this.name;
    }

    public List<Project> getAdmissibleProjects() {
        return this.admissibleProjects;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return this.name + " admissible projects: " + this.admissibleProjects;
    }
}
