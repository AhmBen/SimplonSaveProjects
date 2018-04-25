package co.projetWeb.Application.Scanner;

import java.util.Scanner;

/**
 * Scanner of the application
 * 
 * @author Xavier TAGLIARINO <xavier.tagliarino@gmail.com>
 * @name ApplicationScanner
 *
 */

public class ApplicationScanner {
	
	/**
	  *  Scanner
	  *
	  * @access private
	  * @name scanner
	  * 
	  */
	
	
	private Scanner scanner = new Scanner(System.in);
	
	
	/**
	  * M�thode permettant de factoriser la r�cup�ration d'un entier � la console via le scanner
	  *  
	  * @param String : question
	  * @return int : saisie
	  * @name askInt
	  */
	
	
	public int askInt(String question) {
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
	
	public String askString(String question) {
		
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
	
	/**
	  * Close the Scanner
	  *
	  * @access public
	  * @name close
	  * 
	  * 
	  */
	
	public void close() {
		this.scanner.close();
	}
}
