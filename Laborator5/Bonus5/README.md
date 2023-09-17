1.) Am utilizat Apache Tika pentru a prelua metadatele din fișiere, implementare realizată în InfoCommand. În acest sent, am extras separat fișierele locale și cele regăsite la un link spre un URL extern.

2.) Am implementat BrownBacktrackingColoring folosindu-mă atât de API-ul Graph4J, precum și de JGraphT, urmând să compar eficiența acestora. Acestea sunt rezultatele obținute:

    * Pentru n = 20 de fișiere:
        - Colorarea cu Graph4J: 2 ms.
        - Colorarea cu JGraphT: 5 ms.

    * Pentru n = 100 de fișiere:
        - Colorarea cu Graph4J: 12 ms.
        - Colorarea cu JGraphT: 8 ms.

    * Pentru n = 200 de fișiere:
        - Colorarea cu Graph4J: 500 ms.
        - Colorarea cu JGraphT: 38 ms.