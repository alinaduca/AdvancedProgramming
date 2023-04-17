1.) Pentru a determina un cuplaj de cardinal maxim, am folosit atât cele două biblioteci (JGraphT, Graph4J), cât și algoritmul greedy implementat în clasa Allocation.

2.) Am testat cele trei metode de determinare a cuplajului de cardinal maxim, în felul următor:

    * Pentru 1000 de studenți și 1000 de proiecte, am obținut următoarele rezultate:
        - 2 ms pentru JGraphT;
        - 2 ms pentru algoritmul greedy;
        - 13 ms pentru Graph4J.

    * Pentru 2000 de studenți și 2000 de proiecte, am obținut următoarele rezultate:
        - 1 ms pentru JGraphT;
        - 2 ms pentru algoritmul greedy;
        - 16 ms pentru Graph4J.
        
    * Pentru 3000 de studenți și 3000 de proiecte, am obținut următoarele rezultate:
        - 2 ms pentru JGraphT;
        - 2 ms pentru algoritmul greedy;
        - 20 ms pentru Graph4J.

    * Pentru 4000 de studenți și 4000 de proiecte, am obținut următoarele rezultate:
        - 2 ms pentru JGraphT;
        - 2 ms pentru algoritmul greedy;
        - 22 ms pentru Graph4J.

3.) Am determinat acoperirea cu noduri de cardinal minim utilizând JGraphT.

4.) Am determinat mulțimea independentă de cardinal maxim negând muchiile dintre studenți și proiecte (am creat un graf complementar, astfel încât, dacă în graful inițial nu aveam muchie între nodul student și nodul project, în graful curent o voi fi adăugat, iar dacă în graful inițial aveam o muchie, nu o mai adăugam și în graful de față). Grafului obținut i-am determinat o acoperire cu noduri de cardinal minim, exact ca la punctul precedent, iar astfel am obținut un Maximum Independent Set.