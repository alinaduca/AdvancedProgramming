1.) Main.java, Project.java și Student.java se vor găsi în src->main->java->org->example.

2.) Pentru Student și Project am implementat Comparable.

3.) Am creat mai întâi o listă cu proiectele folosind stream, iar apoi am creat o listă cu studenți într-o manieră similară. Pentru a-mi furniza o listă cu proiectele de la 0 la 2-i, am implementat în clasa Main o metodă care returnează primele i proiecte (apelând pentru n-i, adică 2-i în cazul de față).

4.) Am creat un obiect de tip LinkedList pentru studenti pentru a-i sorta ulterior, printr-un apel al metodei Collections.sort.

5.) Am pus toate proiectele într-un obiect de tipul TreeSet. Astfel, proiectele nu mai trebuie sortate cu un apel explicit al metodei sort, ci ele sunt reținute gata sortate. Deoarece am suprascris metoda compareTo în ambele clase (Student și Project), nu mai trebuie să precizez explicit după ce criteriu se va face sortarea, aceasta realizându-se în ordinea alfabetică a numelor.
