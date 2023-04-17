package org.example;

import java.util.*;
import java.util.stream.IntStream;
import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexCoverAlgorithm;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.matching.EdmondsMaximumCardinalityMatching;

import org.graph4j.alg.matching.HopcroftKarpMaximumMatching;
import org.graph4j.GraphBuilder;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        int n = 2000;
        //Creez o lista cu proiecte folosind stream.
        List<Project> projects = IntStream.rangeClosed(0, n - 1)
                .mapToObj(i -> new Project(faker.company().name().split(",", 2)[0].trim()))
                .toList();

        //Creez o lista cu studentii folosind stream.
        List<Student> students = IntStream.rangeClosed(0, n - 1)
                .mapToObj(i -> new Student(faker.name().fullName()))
                .toList();

        List<Node> nodes = new ArrayList<>(students);
        nodes.addAll(projects);

        //Creez in acelasi timp ambele grafuri
        Graph<Node, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        org.graph4j.Graph graph2 = GraphBuilder.empty().buildGraph();
        Allocation graph3 = new Allocation(students, projects);

        for (Node node : nodes) {
            graph.addVertex(node);
            graph2.addVertex(node);
        }

        Random randNumber = new Random();
        for (int i = 0; i < n; i++) {
            Node student = nodes.get(i);
            if (student instanceof Student) {
                //pentru fiecare student selectez un numar random de proiecte, pe care le voi prelua tot random
                int nrProjects;
                nrProjects = randNumber.nextInt(1, 45);
                while (nrProjects > 0) {
                    int index = randNumber.nextInt(n, 2 * n);
                    graph.addEdge(student, nodes.get(index));
                    graph2.addEdge(student, nodes.get(index));
                    graph.addEdge(student, nodes.get(index));
                    nrProjects--;
                }
            }
        }

        //Calculez maximum cardinality matching cu JGraphT
        long startTime = System.currentTimeMillis();
        EdmondsMaximumCardinalityMatching<Node, DefaultEdge> matching = new EdmondsMaximumCardinalityMatching<>(graph);
        long endTime = System.currentTimeMillis();
        long elapsedTime1 = endTime - startTime;

        //Afisez maximum cardinality matching
        matching.getMatching().getEdges().forEach(System.out::println);


        //Calculez maximum cardinality matching cu Graph4J
        startTime = System.currentTimeMillis();
        HopcroftKarpMaximumMatching matching2 = new HopcroftKarpMaximumMatching(graph2);
        endTime = System.currentTimeMillis();
        long elapsedTime2 = endTime - startTime;

        //Afisez maximum cardinality matching
        System.out.println(matching2.getMatching().toString());

        startTime = System.currentTimeMillis();
        graph3.allocateProjects();
        endTime = System.currentTimeMillis();
        long elapsedTime3 = endTime - startTime;

        System.out.println("JGraphT: " + elapsedTime1 + " ms, Graph4J: " + elapsedTime2 + " ms, Greedy: " + elapsedTime3 + " ms.");

        //Calculez Vertex Cover
        VertexCoverAlgorithm<Node> vc = new GreedyVCImpl<>(graph);
        Set<Node> vertexCover = vc.getVertexCover();

        System.out.println("Vertex Cover");
        vertexCover.forEach(System.out::println);

        //Calculez complementul primului graf, caruia ii voi afla vertex cover-ul pentru a-i afla maximum independent set.
        //Am in vedere faptul ca trebuie sa neg doar muchiile dintre studenti si proiecte.

        Graph<Node, DefaultEdge> complement = new SimpleGraph<>(DefaultEdge.class);
        for (Node node : nodes) {
            complement.addVertex(node);
        }

        //Determin complementul grafului
        for(int i = 0; i < n; i++) {
            Node student = nodes.get(i);
            for(int j = n; j < 2 * n; j++) {
                Node project = nodes.get(j);
                if(!graph.containsEdge(student, project)) {
                    complement.addEdge(student, project);
                }
            }
        }

        //Calculez vertex cover-ul
        VertexCoverAlgorithm<Node> mis = new GreedyVCImpl<>(complement);
        Set<Node> maximumIndependentSet = mis.getVertexCover();

        //Afisez ceea ce am obtinut, reprezentand maximum independent set-ul grafului initial.
        System.out.println("Maximum Independent Set");
        maximumIndependentSet.forEach(System.out::println);
    }
}