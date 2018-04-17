package application;

import client.FoodAttribute;
import client.FoodDBService;
import client.FoodDBService_Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;



/**
  * Classe principale du programme
  * 
  * @author Xavier Tagliarino
  * @name Main
  * @access public
  *
  */

public class Main {
	
	/**
	  * Object scanner pour entr�e utilisateur
	  * 
	  * @author Xavier Tagliarino <xavier.tagliarino@gmail.com>
	  * @access public static
	  * @name scanner
	  * @var Scnanner
	  * 
	  */
	
	private static Scanner scanner = new Scanner(System.in);

	
	
	public static void main(String[] args) {
		
		
		try {
			
			//R�cup�ration des infos sur l'application lanc�e
			ResourceBundle rs 	=	ResourceBundle.getBundle("config");
			String name 		=	rs.getString("appli.name");
			String version 		=	rs.getString("appli.version");
			String contactName 		=	rs.getString("contact.name");
			String contactMail 		=	rs.getString("contact.email");
			//Message de bienvenu	
			System.out.println("***************************************");
			System.out.println("*** "+name+" "+version+" *** ");
			System.out.println("***************************************");
			System.out.println("--------------------------------------------------------------------------------------------------------");
			System.out.println("Pour toute r�clamation adressez vous � "+contactName+" en lui �crivant � : "+contactMail);
			System.out.println("--------------------------------------------------------------------------------------------------------");
		}catch(MissingResourceException e) {
			  System.out.println("Hoho ! il faut v�rifier le chemin du fichier properties");		 
			}			
		
		//3, 2, 1 : et on d�marre
		System.out.println("Vous allez y entrer dans :");	
		Loader.waitALittle();
		
		//D�marrage
		//Affichage du menu
		System.out.println("**************************");
		System.out.println("*********Go !!!!!!!*******");
		System.out.println("**************************");
		
								
		//Indication pour savoir si on doit quitter l'application
		boolean quit = false;		
		
		while(!quit) {
					
			/* Fabrication du menu */
			MenuAppli menu = new MenuAppli();
			
			//On quitte le programme
			menu.add(new ItemMenu("Quitter cette superbe application de la mort qui tue !!", "quit"));
			//On affiche l'int�gralit� de la base de donn�es
			menu.add(new ItemMenu("Tester le WS", "ping"));
			menu.add(new ItemMenu("Tester le WS", "ping2"));
			menu.add(new ItemMenu("Chercher des aliments par le nom", "getFoodAttributeListByName"));
			menu.add(new ItemMenu("Lister les cat�gories", "listFoodCategories"));
											
			//D�clenchement de la m�thode dynamiquement
			try{
				
				int choixClavier = Loader.askInt("Que souhaitez vous faire ?\n"+menu);
				String methodeALancer = menu.getItemMenus().get(choixClavier).getAction();
							
				//On d�clenche la m�thode
				if(!Loader.lancerMethode(methodeALancer)) {
					System.err.println("Il semble que l'action que vous tentez de lancer soit indisponible");
				}
				//On patiente un peu entre chaque action
				Loader.waitALittle();				
				
			}catch(NumberFormatException e) {
				System.err.println("** veuillez saisir une valeur num�rique !!! **");
				Loader.waitALittle();
			}catch(IndexOutOfBoundsException e) {
				System.err.println("** Ce choix est IMPOSSIBLE !!! ** ");
				Loader.waitALittle();
			}
			
		}	
	}	
	

}