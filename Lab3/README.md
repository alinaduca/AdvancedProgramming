1.) Am creat Main.java, Person.java și Company.java.
2.) Am înlocuit public class Person cu public class Person implements Comparable<Person>, după care am suprascris metoda compareTo astfel încât să returneze -1 dacă numele persoanei curente este mai mic alfanumeric decât al persoanei date ca parametru, 0 dacă sunt egale și 1 dacă este alfanumeric mai mare. Apoi, am făcut același lucru în Company.java.
3.) Am creat interfața Node în care am definit metoda getName. Am implementat metoda în Company.java și în Person.java.
4.) În Main.java am creat o listă de noduri în care am introdus trei persoane și trei companii, prin nodes.add(), iar apoi am afișat toate nodurile (numele lor).