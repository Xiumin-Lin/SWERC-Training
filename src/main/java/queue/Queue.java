package queue;

import java.util.HashMap;
import java.util.Scanner;

public class Queue {
    private Queue() {}

    public static void runAlgo() throws Exception {
        // 12207 "That is Your Queue"
        HashMap<Integer, Personne> register = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int compteur = 0; // compteur de cas
        while(true) {
//            System.out.print("Enter p : "); // DEBUG
            int p = sc.nextInt();
//            System.out.print("Enter c : "); // DEBUG
            int c = sc.nextInt();

            if(p == 0 && c == 0) break;
            System.out.println("Case " + ++compteur + ":");
            // Remplir le registre des personnes
            Personne firstP = new Personne(1, null, null);
            register.put(firstP.getVal(), firstP);
            for(int i = 2; i <= p; i++) {
                Personne prevPerson = register.get(i - 1);
                Personne currentPerson = new Personne(i, prevPerson, null);
                register.put(currentPerson.getVal(), currentPerson);
                prevPerson.setNext(currentPerson);
            }
            Personne lastP = register.get(p);
            // Boucle la liste
            firstP.setPrev(lastP);
            lastP.setNext(firstP);

            for(int i = 0; i < c; i++) {
                switch(sc.next()) {
                    case "N":
                        System.out.println(firstP.getVal()); // annonce la prochaine personne
                        lastP = lastP.getNext();
                        firstP = firstP.getNext();
                        break;
                    case "E":
//                        System.out.print("Enter x : "); // DEBUG
                        int x = sc.nextInt();
                        assert x > 0 && x <= p;

                        System.out.println(x); // annonce que la personne urgente x est la suivantes
                        Personne urgentP = register.get(x);

                        // si la personne urgente est la personne en tête de liste, alors on fait comme avec 'N'
                        if(urgentP.equals(firstP)) {
                            lastP = lastP.getNext();
                            firstP = firstP.getNext();
                        }
                        // retire et insere la personne urgente à la fin de la liste
                        else if(!urgentP.equals(lastP)) {
                            // retire
                            urgentP.getPrev().setNext(urgentP.getNext());
                            urgentP.getNext().setPrev(urgentP.getPrev());

                            // insere
                            urgentP.setPrev(lastP);
                            urgentP.setNext(firstP);
                            firstP.setPrev(urgentP);
                            lastP.setNext(urgentP);
                            lastP = urgentP;
                        }

                        break;
                    default:
                        throw new Exception("Invalid Input !");
                }
            }
        }
        sc.close();
    }
}
