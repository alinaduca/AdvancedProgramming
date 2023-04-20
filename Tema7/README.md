1.) Am implementat comenzi pentru start și pause, astfel încât:
    - prin "pause all", utilizatorul poate pune toți roboții pe pauză;
    - prin "pause all n", utilizatorul poate pune toți roboții pe pauză pentru n secunde;
    - prin "pause i", utilizatorul poate pune pe pauză al i-lea robot;
    - prin "pause i n", utilizatorul poate pune pe pauză al i-lea robot pentru n secunde;
    - prin "start all", utilizatorul poate porni execuția tuturor roboților;
    - prin "start i", utilizatorul poate porni execuția celui de-al i-lea robot;
2.) Am implementat un timekeeper care rulează concurent și care monitorizează timpul, afișând la final timpul de rulare și oprind execuția roboților în cazul în care se depășește o anumită limită de timp.
3.) Am implementat metoda getRobotsInfo în clasa Exploration care afișează pentru fiecare robot câte token-uri au fost plasate în matrice.