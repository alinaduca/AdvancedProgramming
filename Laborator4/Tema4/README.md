1.) Am creat clasa Allocation.java, pentru a descrie problema.

2.) În Main.java, am calculat numărul mediu de preferințe ale studenților folosind Java Stream API, iar apoi am filtrat toți studenții pentru a-i afișa doar pe cei ai căror număr de preferințe este mai mic decât media calculată anterior.

3.) Am utilizat librăria JavaFaker pentru a genera nume random pentru studenți și proiecte. În acest sens, am adăugat o nouă dependență în pom.xml.

4.) Pentru a atribui fiecărui student câte un proiect, am implementat un algoritm Greedy. Concret, în Allocation.java, am implementat metoda allocateProject, care inițial va sorta studenții în ordine crescătoare în funcție de numărul de proiecte care le sunt admisibile, iar apoi voi lua fiecare student și îi voi atribui primul proiect din lista lui de preferințe care nu a fost atribuit anterior.