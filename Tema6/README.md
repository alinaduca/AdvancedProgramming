1.) Am implementat clasele Dot, Graph și Line, pe lângă clasa PositionalGame, pentru a avea o reprezentare facilă a structurilor de date.

2.) În PositionalGame am introdus handler-ul pentru click canvas.setOnMouseClicked, în interiorul căruia calculez linia pe care am făcut click, urmând să o colorez în funcție de al cărei culori este rândul la momentul de față. În cazul în care a fost apăsată o linie validă (există și nu a fost colorată anterior), atunci ea va fi colorată, urmând să verific dacă player-ul care a făcut click a câștigat jocul sau dacă acesta continuă (câștigarea jocului presupunând ca un player din cei doi să formeze un triunghi).

3.) Am implementat butoanele de Save, Load, Reset și Exit.