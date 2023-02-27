1.) Interpretarea rezultatului: 
    În matricea A^2, numărul de pe poziția i și j denotă numărul de drumuri de la i la j.
    Concret, A^2[i][j] += A[i][k]*A[k][j], deci va fi egală cu numărul de valori k intermediare astfel încât să existe drum de la i la j prin k.
    De exemplu, pentru (0, 0) avem valoarea 2 deoarece avem drumurile (0, 1) și (1, 0), respectiv (0, 4) și (4, 0).
    În matricea A^k, numărul de pe poziția i și j denotă numărul de drumuri de la i la j prin k.
    Concret, la pasul anterior am calculat numărul de mersuri de la i la j. Acum iau fiecare mers și încerc să ajung la noul j, adăugând o singură muchie nouă în plus.
    Deci, voi lua elementele (i, l) din matricea A^(k - 1) și voi verifica dacă există muchia (l, j) pentru a o adăuga la mersul actual.
    Pentru că pot fi mai multe mersuri calculate la pasul anterior pe care le-aș putea mări pentru a ajunge la un mers cu o unitate mai mare, voi însuma rezultatele obținute.

2.) Condițiile ca un astfel de graf să existe sunt:
        * suma gradelor tuturor vârfurilor să fie egală cu 2m, deci degree*numberOfVertexes trebuie să fie nr. par, deci una dintre cele două trebuie să fie nr. par
        * m <= numberOfVertexes*(numberOfVertexes - 1) / 2, deci degree trebuie să fie mai mic decât numberOfVertexes
Pentru început, pentru linia 0, voi pune 1 în matricea de adiacență începând cu poziția imediat după diagonala principală.
De fiecare dată când inserez o valoare de 1, mă voi asigura că pun aceeași valoare și în poziția simetrică de sub diagonala principală.
În continuare, voi începe de la poziția imediat după ultima poziție la care am pus 1 pe linia de mai sus.
Dacă acea poziție este mai mare sau egală cu numberOfVertexes, atunci voi începe cu poziția de după diagonala principală.
Dacă, încă de la început, aș fi pus valorile de 1 pe pozițiile de după diagonala principală, atunci ar fi existat posibilitatea de a crea un graf cu mai multe componente conexe.