import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import sun.reflect.generics.tree.Tree;

public class Main {
	public static void main(String[] Args) {
		
		/*** Chap 1 : Les Tableaux ***/
		/*
		//1 - Tableau
		//0 => 1
		//1 => 3
		// ...
		//4 => 9
		
		//m�thode 1 pour d�clarer
		//int[] armoire = {1, 3, 5, 7, 9};
//		//m�thode 2 pour d�clarer
		int[] armoire = new int[5];
		//�quivalent � �a :
		//int[] armoire = {0, 0, 0, 0, 0};
		for(int i = 0 ; i < 5 ; i++ ) {
			armoire[i] = i;
		}
		armoire[0] = 0;
		armoire[1] = 1;
		armoire[2] = 2;
		armoire[3] = 3;
		armoire[4] = 4;
		System.out.println(armoire[0]);
		System.out.println(armoire[1]);
		System.out.println(armoire[2]);
		System.out.println(armoire[3]);
		System.out.println(armoire[4]);

		
		
		//Affichage de chaque casier d'indice i (i changeant � chaque it�ration) 
		for(int i = 0 ; i < armoire.length ; i++) {
			System.out.println(armoire[i]);
		}
		
		//Pour chaque "valeur" (d�clar� en int) cotenu dans "armoire"
		for(int valeur : armoire) {
			System.out.println(valeur);
		}
		*/
		
		/**** Chapitre 2 : Les collections ****/
		/*
		//ce sont comme des tableaux qui n'ont pas de taille pr�d�finies
		//On peut connaitre � tout moment la taille
		//On y empile des �l�ments		
		//On peut y retrouver les �l�ment via des indices		
		//On peut m�me effacer les �l�ments
		
		//J'instanice une collection de type ArrayList
		ArrayList armoireAlphabet = new ArrayList();
		//j'empile des �l�ments
		armoireAlphabet.add("A");
		armoireAlphabet.add("B");
		armoireAlphabet.add(9);
		
		int[] armoire = {1, 3, 5, 7, 9};
		armoireAlphabet.add(armoire);
		//J'enl�ve l'�l�ment d'indice "1" tous les autres indices sont recalcul�s
		//armoireAlphabet.remove(1);
		
		//J'efface tous les �l�ments
		//armoireAlphabet.clear();
		
		//Si ma collection "armoireAlphabet" n'est pas vide
		if( !armoireAlphabet.isEmpty() ) {
			System.out.println("je tente d'it�rer");
			//je boucle (j'it�re) sur mes �l�ments
			for(int i = 0 ; i < armoireAlphabet.size() ; i++) {
				System.out.println("indice = "+i+" element ="+armoireAlphabet.get(i));
			}
		}
		
		//J'affiche l'�l�ment 3
		int[] monTableau =  (int[]) armoireAlphabet.get(3);
		//En modifiant "monTableau", je modifie la m�me adresse m�moire que celle d�sign�e par "armoire" et "armoireAlphabet.get(3)" les 3 d�signent le m�me tableau en m�moire
		monTableau[1] = 45;
		
		*/
		
		//Je cr�er une nouvelle ArrayList en pr�cisant le type d'�l�ments quelle va recevoir
		
		/*ArrayList <Integer> collectionNbReps = new ArrayList<Integer>();
		collectionNbReps.add(8);
		collectionNbReps.add(30);
		collectionNbReps.add(3);
		
		for(int scoreRep : collectionNbReps) {
			System.out.println(scoreRep);
		}
		
		TreeSet<Integer> collectionXav = new TreeSet<Integer>();
		collectionXav.add(9);
		collectionXav.add(4);
		collectionXav.add(14);
		
		for(int scoreXav : collectionXav) {
			System.out.println(scoreXav);
		}
		
		TreeMap<String, String> collectionNom = new TreeMap<String,String>();
		collectionNom.put("Xavier", "Vitry");
		collectionNom.put("Waby","Paris");
		
		//System.out.println(collectionNom.get("Xavier"));
		Set entrySet = collectionNom.entrySet();
		Iterator iterator = entrySet.iterator();
		while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
	      }
		
		//int[] tab  = collectionNbReps.toArray();	
		*/
		//Figure carre = new Carre();
		//carre.dessiner();
		//Figure triangle = new Triangle();
		//triangle.dessiner();
		
		//dessinerUnFigure(new Carre());
		//dessinerUnFigure(new Triangle());
		
		/*if(Args.length > 0) {
			for(String arg : Args) {
				System.out.println(arg);
			}
		}*/
		
		/*** Les interfaces ***/
		
		//M�thode 1 : On instancie les objets et on appelle la m�thode dessiner de chacun 
		/*Carre carre = new Carre();
		carre.dessiner();
		
		Triangle triangle = new Triangle();
		triangle.dessiner();
		
		Rectangle rectangle = new Rectangle ();
		rectangle.dessiner();
		
		//M�thode 2 : On appelle une fonction qui prend en entr�e un param�tre (qui d�signe un objet) qui respecte d'interface (contrat) "Figure"
		dessinerUnFigure(carre);
		dessinerUnFigure(triangle);
		dessinerUnFigure(rectangle);
		
		//M�thode 3 : tr�s proche la m�thode 2 ou l'on envoie directement une nouvelle instance de "Carre","Rectangle","Triangle"
		dessinerUnFigure(new Carre());
		dessinerUnFigure(new Triangle());
		dessinerUnFigure(new Rectangle ());*/
	}
	
	/*public static void dessinerUnFigure(Figure f) {
		//Quelque soit la "Figure" elle poss�de forc�ment la m�thode dessiner() (impos� par l'interface "Figure")
		f.dessiner();
	}*/
}
