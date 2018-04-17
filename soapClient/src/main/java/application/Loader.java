package application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Loader {
	
	private static Controller controller = new Controller();
	
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
	
	/**
	  * Tente de lancer une m�thode dynamiquement via l'API r�flexivit�
	  * 
	  * @name lancerMethode
	  * @param String : nom de la m�thode
	  * @return boolean : indique si la m�thode a pu �tre lanc�e
	  * 
	  */
	
	public static  boolean lancerMethode(String methode)
	{
		
		
		Class<?> classeAInstancier = controller.getClass();
		Class<?>[] types = new Class[] {};				
		try {
			Method methodePresenter = classeAInstancier.getMethod(methode,types);
			methodePresenter.invoke(controller, null);
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return false;
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}
		
		
		return true;
		
		
		
		/*Class types[] = {  }; 
		
		Method method;
		
		try {
			
			//R�cup�ration de la m�thode
			method = Main.class.getMethod(methode, types);
			//Aucun param�tre n'est envoy�
			Object parametres[] = {  };
			
			try {
				method.invoke(null, parametres);
			} catch (IllegalAccessException e) {				
				e.printStackTrace();
				return false;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return false;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				return false;
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return false;
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;*/
	}
	
	
	/**
	  * M�thode permettant de factoriser la r�cup�ration d'un entier � la console via le scanner
	  *  
	  * @param String : question
	  * @return int : saisie
	  * @name askInt
	  */
	
	public static int askInt(String question) {
		
		System.out.println(question);
		int choixClavier = 0;
		boolean choixKo = true;
		
		while(choixKo) {
			try{
				choixClavier = Integer.parseInt(scanner.nextLine());
				if(choixClavier < 0) {
					System.err.println("Pas de valeur n�gative !!");
					continue;
				}
				choixKo = false;
			}
			catch(NumberFormatException e) {
				System.err.println("veuillez saisir une valeur num�rique");
			}
		}
		return choixClavier;
	}
	
	/**
	  * M�thode permettant de factoriser la r�cup�ration d'un entier � la console via le scanner
	  *  
	  * @param String : question
	  * @return int : saisie
	  * @name askString
	  */
	
	public static String askString(String question) {
		
		System.out.println(question);
		
		String choixClavier="";
		try{
			choixClavier = scanner.nextLine();			
		}
		catch(NumberFormatException e) {
			System.out.println("veuillez saisir une valeur num�rique");
		}
		return choixClavier;
		
	}
	
	/*
	 * Met en attente
	 *
	 * @access public static
	 * @name waitALittle
	 * @return void
	 * 
	 **/
	
	public static void waitALittle() {
		
		System.out.println("Go dans : ");
		
		for(int i = 3 ; i > 0 ; i--) {
			System.out.print(i+" ");				
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		System.out.println();
	}
}
