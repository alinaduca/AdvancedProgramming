package org.example;

import java.util.*;

public class Project implements Node, Comparable<Project> {
    private String name;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Project other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
