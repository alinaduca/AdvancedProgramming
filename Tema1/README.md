1.) Pentru a valida argumentul voi verifica, în primul rand, dacă la linia de comandă a fost dată vreo valoare, adică dacă args.length este mai mare decât 0.
Apoi, voi parcurge șirul de caractere args[0] și voi verifica dacă toate valorile sunt cifre (și eventual dacă prima valoare este egală cu -). Pentru a face acest lucru, voi avea un nou șir de caractere conținând fiecare cifră și voi verifica cu charAt() dacă caracterul se află în șirul respectiv.
Dacă toate caracterele din șirul citit de la linia de comandă se regăsesc în șirul care conține toate cifrele, atunci numărul dat este un integer.
2.) Declar o matrice de n x n, iar fiecarei pozitii ii voi da valoarea (i + j) % n + 1 (prima linie va contine elementele 1, ..., n, iar urmatoarele linii vor reprezenta permutari la stanga a liniei anterioare).
3.) Voi avea un for in care voi lua liniile si coloanele in acelasi timp, iar in urmatorul for voi construi string-urile corespunzatoare fiecarei linii si coloane din matrice.
4.) La începutul programului voi avea o variabila startNano pentru timpul în ns și un startMil pentru ms, iar la sfarsitul programului voi scadea din endNano și endMil pe startNano și startMil pentru a afla timpul atât în milisecunde, cât și în nanosecunde.