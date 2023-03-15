package org.example;
import java.util.*;
import com.github.javafaker.Faker;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        //Creez o lista cu proiecte folosind stream.
        List<Project> projects = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Project(faker.company().name().split(",", 2)[0].trim()))
                .toList();

        //Creez o lista cu studentii folosind stream.
        List<Student> students = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Student(faker.name().fullName(), getProjects(projects, 3-i)))
                .toList();

        //Creez un obiect de tip LinkedList pentru studenti pentru a-i sorta ulterior, printr-un apel al metodei Collections.sort.
        LinkedList<Student> sortedStudents = new LinkedList<>(students);
        Collections.sort(sortedStudents);
        sortedStudents.forEach(System.out::println);

        //Pun toate proiectele intr-un obiect de tipul TreeSet pentru a nu le sorta explicit.
        TreeSet<Project> sortedProjects = new TreeSet<>(projects);
        sortedProjects.forEach(System.out::println);

        //Calculez numarul mediu de preferinte ale studentilor folosind Java Stream API
        double avgPreferences = sortedStudents.stream()
                .mapToDouble(student -> student.getAdmissibleProjects().size())
                .average()
                .orElse(0);

        //Filtrez toti studentii pentru a-i afisa doar pe cei ai caror numar de preferinte este mai mic decat media calculata anterior.
        System.out.println("\nStudenti cu mai putine preferinte decat media (< " + avgPreferences + "):");
        sortedStudents.stream()
                .filter(student -> student.getAdmissibleProjects().size() < avgPreferences)
                .forEach(student -> System.out.print(student.getName() + " "));

        System.out.println("\n\nAtribuirile proiectelor:");
        Allocation allocation = new Allocation(sortedStudents, sortedProjects);
        allocation.allocateProjects();
        for(Student student : allocation.getAllocation().keySet()) {
            System.out.println(student.getName() + " cu proiectul " + allocation.getAllocation().get(student) + ".");
        }
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