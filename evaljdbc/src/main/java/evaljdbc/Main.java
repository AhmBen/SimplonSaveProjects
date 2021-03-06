package evaljdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

import Model.Category;
import Model.Food;

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
		Class types[] = {  }; 
		
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
		
		return true;
	}
	
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
		waitALittle();
		
		//D�marrage
		System.out.println("Go !!!!!!!");
								
		//Indication pour savoir si on doit quitter l'application
		boolean quit = false;		
		
		while(!quit) {
			
			//Affichage du menu
			System.out.println("**************************");
			System.out.println("Que souhaitez vous faire ?");
			System.out.println("**************************");
			
			/* Fabrication du menu */
			MenuAppli menu = new MenuAppli();
			
			//On quitte le programme
			menu.add(new ItemMenu("Quitter cette superbe application de la mort qui tue !!", "quit"));
			//On affiche l'int�gralit� de la base de donn�es
			menu.add(new ItemMenu("Afficher tout en d�tail d�tail", "displayAll"));
			//Cat�gories
			menu.add(new ItemMenu("Afficher les cat�gories", "displayCats"));
			menu.add(new ItemMenu("Cr�er une cat�gorie", "createCat"));
			menu.add(new ItemMenu("Modifier une cat�gorie", "updateCat"));
					
			
			//On autorise ces actions uniquement si au moins une cat�gorie existe
			if(Category.list().size() > 0) {
				menu.add(new ItemMenu("Effacer une cat�gorie", "deleteCat"));
				//Aliments
				menu.add(new ItemMenu("Cr�er un aliment", "createFood"));
				
				//Rien ne sert d'y aller si aucun aliment n'existe dans la bdd
				int nbFoods = 0;
				for(Category category : Category.list()) {
					nbFoods += category.getFoods().size();
				}
				
				if(nbFoods > 0) {
					menu.add(new ItemMenu("Modifier un aliment", "updateFood"));
					menu.add(new ItemMenu("Effacer un aliment", "deleteFood"));
				}
				
				
			}
			
			//On affiche le menu
			System.out.println(menu);
			
			if(Category.list().size() == 0) {
				System.out.println("Vous devez cr�er au moins une cat�gorie pour afficher les actions sur les aliments");
			}
			
			//D�clenchement de la m�thode dynamiquement
			try{
				
				int choixClavier = Integer.parseInt(scanner.nextLine());
				String methodeALancer = menu.getItemMenus().get(choixClavier).getAction();
				
				
				//Choix Z�ro : on quitte
				if(choixClavier == 0) { quit = true; }
				
				//On d�clenche la m�thode
				if(!lancerMethode(methodeALancer)) {
					System.err.println("Il semble que l'action que vous tentez de lancer soit indisponible");
				}
				//On patiente un peu entre chaque action
				waitALittle();				
				
			}catch(NumberFormatException e) {
				System.err.println("** veuillez saisir une valeur num�rique !!! **");
				waitALittle();
			}catch(IndexOutOfBoundsException e) {
				System.err.println("** Ce choix est IMPOSSIBLE !!! ** ");
				waitALittle();
			}
			
		}
		
		//Fin du programme
		System.out.println("Vous partez d�j� ?? :(");
	}
	
	/*
	 * Afiche les cat�gories sans leur aliments
	 * 
	 * @access public static
	 * @name displayCats
	 * @return void
	 * 
	 */
	
	public static void displayCats() {
		
		ArrayList<Category> listCategories = Category.list();
		
		if(listCategories.size() == 0) { 
			System.err.println("Aucune cat�gorie n'est disponible dans la bdd");
			return; 
		}
		
		for(Category category : listCategories) {
			System.out.println(category);
		}
		
	}
	
	/*
	 * Efface une cat�gorie via un id s�lection�
	 * 
	 * @access public static
	 * @name deleteCat
	 * @return void
	 * 
	 */
	
	public static void deleteCat() {
		
		//Rien ne sert d'y aller si aucune cat�gorie n'existe dans la bdd
		if(Category.list().size() <= 0) {
			System.err.println("Op�ration impossible car aucune cat�gorie n'est pr�sente dans la base de donn�es");
			return; 
		}
		
		//On affiche les cat�gories
		displayCats();
				
		//On r�cup�re la saisie de l'id de la cat�gorie � supprimer
		int catId = 0;
		
		while(catId == 0) {
			
			catId = recupererChoixInt("Quel est l'id de la cat�gorie � supprimer ?");
						
			Category category = new Category();
			
			//Si on on ne trouve pas la cat�gorie
			if(!category.find(catId)) {
				catId = 0;
				System.out.println("Il n'y pas de cat�gorie avec cet Id");
			}
			//Si on a trouv� la cat�gorie
			else
			{
				//On test s'il y a des aliments dans la bdd
				if(category.getFoods().size() > 0) {
					System.err.println("Vous ne pouvez pas supprimer la cat�gorie : "+category.getName()+ " car elle contient encore des aliments !!");
					displayCats();
					return;
				}
				
				//On tente la suppression
				if(category.delete()) {
					System.out.println("La cat�gorie : "+category.getName()+" vient d'�tre supprim�e avec succ�s");
				}else {
					System.err.println("Il y a eu un probl�me technique lors de la suppression de la cat�gorie :"+category.getName());
				}				
			}
			
			//On affiche � nouveau la liste � jour des cat�gories
			displayCats();
		}	
		
	}
	
	/**
	  * Cr�e un cat�gorie via une saisie des infos
	  *  - name
	  * 
	  * @access public static
	  * @name createCat
	  * @return void
	  *  
	  */
	
	public static void createCat() {
		
		//On affiche la liste des cat�gories � jour
		displayCats();
		//On r�cup�re l'info name pour cr�er la cat�gorie
		String catName = recupererChoixString("Quelle est le nom de la cat�gorie � cr�er ?");			
		
		Category category = new Category(catName);
		//On tente une cr�ation
		if(category.create()) {
			System.out.println("La cat�gorie : "+category.getName()+" a �t� cr��e avec succ�ss");
		}
		//Cr�ation �chou�e
		else {
			System.err.println("Il y a eu un probl�me lors de la cr�ation de la cat�gorie : "+category.getName());
		}
		
		//On r�affiche la liste de cat�gories mises � jour 
		displayCats();
		
	}
	
	/**
	  * Met � jour une cat�gorie en s�lectionnant un id
	  * Puis on saisie les infos :
	  * -name
	  *
	  * @access public static
	  * @name updateCat
	  * @return void
	  * 
	  */
	
	public static void updateCat() {
		
		//On affiche la liste des cat�gories � jour
		displayCats();
		//On demande l'id de la cat�gorie � modifier
		int catIt = recupererChoixInt("Quelle est l'id de la cat�gorie � mettre � jour ?");
			
		Category category = new Category();		
		//Si on trouve la cat�gorie
		if(category.find(catIt)) {
			String catName = recupererChoixString("Quelle est le nom de la cat�gorie � mettre � jour ?");
			category.setName(catName);
			//On tente une mise � jour
			if(category.update()) {
				System.out.println("Cat�gorie "+category.getName()+" mise � jour avec succ�s");
			}
			//Mise � jour �chou�e
			else {
				System.err.println("Il y a eu un probml�me de mise � jour de la cat�gorie "+category.getName());
				
			}
			
			//On affiche la liste de cat�gories mise � jour
			displayCats();
			return;
		}
		else {
			System.out.println("Cat�gorie introuvable !!");
		}
	}
	
	/**
	  * Affiche toute la base de donn�es :
	  * 2 niveaux :
	  * - Cat�gorie
	  * -- Aliments
	  *
	  * @access public static
	  * @name displayAll
	  * @return void
	  * 
	  */	
	
	public static void displayAll() {
		
		ArrayList<Category> listCategories = Category.list();
		
		if(listCategories.size() == 0) { 
			System.err.println("Aucune cat�gorie n'est disponible dans la bdd");
			return; 
		}		
		
		System.out.println("******");	
		
		
		
		for(Category category : listCategories) {
			
			
			
			String[] tabLegendCategories = {"N�=","Nom"};
			String legendCategories = "";
			
			int lengthColsCatgeories = 14;
			
			for(int i = 0 ; i < (lengthColsCatgeories + 2) * tabLegendCategories.length ; i++) {
				legendCategories += "-";
			}
			
			legendCategories += "\n";
			
			for(String str : tabLegendCategories) {
				legendCategories += "|"+String.format("%-"+lengthColsCatgeories+"s", str)+"|";
			}
			
			legendCategories += "\n";
			
			
			
			for(int i = 0 ; i < (lengthColsCatgeories + 2) * tabLegendCategories.length ; i++) {
				legendCategories += "-";
			}
			
			System.out.println(legendCategories);
			
			System.out.print(category);		
			
			
			
			ArrayList<Food> foods = category.getFoods();
			if(foods.size() > 0) {				
				System.out.println("** Les aliments sont les suivants **");
								
				String[] tabString = {
						"N� = "			,
						"Nom : "		,
						"Protein :"		,
						"Glucide :"		,
						"Lipide :"		,
						"Calories : "	,
						"Unite : "
				};				
				
				String legend = "";
				int lengthCols = 14;				
				
				for(int i = 0 ; i < (lengthCols + 2) * tabString.length ; i++) {
					legend += "-";
				}
				legend += "\n";
				
				for(String str : tabString) {
					legend += "|"+String.format("%-"+lengthCols+"s", str)+"|";
				}
				
				legend += "\n";
				
				for(int i = 0 ; i < (lengthCols + 2) * tabString.length ; i++) {
					legend += "-";
				}
				
				System.out.println(legend);
				
				
				for(Food food : foods) {
					System.out.print(food);				
				}
				
			}
			else {
				System.out.println("- pas d'aliment -");
			}
		}
	}
	
	/*
	 * Supprime un aliment
	 * 
	 * @access public static
	 * @name deleteFood
	 * @return void
	 * 
	 */
	
	public static void deleteFood() {
		
		//Rien ne sert d'y aller si aucune cat�gorie n'existe dans la bdd
		if(Category.list().size() <= 0) {
			System.err.println("Op�ration impossible car aucune cat�gorie n'est pr�sente dans la base de donn�es");
			return; 
		}
		
		//Rien ne sert d'y aller si aucun aliment n'existe dans la bdd
		ArrayList<Category> Categories = Category.list();
		int nbFoods = 0;
		for(Category category : Categories) {
			nbFoods += category.getFoods().size();
		}
		
		if(nbFoods == 0) {
			System.err.println("Op�ration impossible car aucun aliment n'est pr�sent dans la base de donn�es");
			return;
		}
		
		//On affiche toute la bdd
		displayAll();
		
		//On demande l'id de l'aliment � supprimer
		int foodId = 0;
		
		while(foodId == 0) {
			foodId = recupererChoixInt("Quel est l'id de l'aliment � supprimer ?");
						
			Food food = new Food();
			//Si l'aliment n'est pas trouv�
			if(!food.find(foodId)) {
				foodId = 0;
				System.out.println("Il n'y pas d'aliment avec cet Id");
			}
			//On a trouv� l'aliment
			else
			{
				//On supprime l'aliment
				if(food.delete()) {
					System.out.println("La suppression de l'aliment : "+food.getName());
				}
				//On n'y est pas arriv� 
				else {
					System.err.println("Probl�me lors de la suppression de l'aliment : "+food.getName());
				}
			}
		}
		
		//On affiche la bdd � jour
		displayAll();
	}
	
	/**
	  * On Cr�er un aliment en l'affectant � une cat�gorie et on demande de infos :
	  * - name
	  * - proteine
	  * - glucide
	  * - lipide
	  * 
	  * @access public static
	  * @name createFood
	  * @return void
	  * 
	  */
	
	public static void createFood() {
		
		//Rien ne sert d'y aller si aucune cat�gorie n'existe dans la bdd
		if(Category.list().size() <= 0) {
			System.err.println("Op�ration impossible car aucune cat�gorie n'est pr�sente dans la base de donn�es");
			return; 
		}
		//On affiche toute la bdd
		displayAll();
		
		//On demande � quelle cat�gorie relier l'aliment
		int catId = 0;
		Category category = new Category();
		Food food;
		while(catId == 0) {		
			catId = recupererChoixInt("Quel est l'id de la cat�gorie o� ajouter l'aliment ?");
			
			if(!category.find(catId)) {
				System.err.println("Veuillez choisir une cat�gorie existante !!");
				catId = 0;
			}			
		}
		
		//On demande les infos de l'aliment
		//name
		String name = recupererChoixString("Nom de l'aliment ?");
		//proteine
		int protein = recupererChoixInt("Prot�ine ?");
		//glucide
		int glucid = recupererChoixInt("Glucide ?");
		//lipide
		int lipid = recupererChoixInt("Lipide ?");
		
		//On tente de cr�er l'aliment
		food = new Food(name,protein,glucid,lipid);
		food.setCategory(category);
		//Si on y arrive
		if(food.create()) {
			System.out.println("L'aliment : "+food.getName()+" a �t� cr�� avec succ�s ");
		}
		//Si la cr�ation n'a pas fonctionn�
		else {
			System.err.println("probl�me de cr�ation de l'aliment : "+food.getName());
		}
			
		//On affiche la bdd � jour
		displayAll();
		
	
		
	}
	
	/**
	  * Met � jour un aliment � partir de son id plus d'autres infos :
	  * - name
	  * - proteine
	  * - glucide
	  * - lipide
	  *  On ne modifie pas sa cat�gorie
	  * 
	  * @access public static
	  * @name updateFood
	  * @return void
	  * 
	  */
	
	public static void updateFood() {
		
		//Rien ne sert d'y aller si aucune cat�gorie n'existe dans la bdd
		if(Category.list().size() <= 0) {
			System.err.println("Op�ration impossible car aucune cat�gorie n'est pr�sente dans la base de donn�es");
			return; 
		}
		
		//Rien ne sert d'y aller si aucun aliment n'existe dans la bdd
		ArrayList<Category> Categories = Category.list();
		int nbFoods = 0;
		for(Category category : Categories) {
			nbFoods += category.getFoods().size();
		}
		
		if(nbFoods == 0) {
			System.err.println("Op�ration impossible car aucun aliment n'est pr�sent dans la base de donn�es");
			return;
		}
		
		//On affiche la bdd � jour
		displayAll();
		int foodId = 0;
		//Category category = new Category();
		Food food = new Food();
		while(foodId == 0) {		
			foodId = recupererChoixInt("Quel est l'id de l'aliment � modifier ?");
			
			if(!food.find(foodId)) {
				System.err.println("Veuillez choisir un aliment existant !!");
				foodId = 0;
			}			
		}
		
		//On demande les infos de l'aliment
		//name
		String name = recupererChoixString("Nom de l'aliment ?");
		//proteine
		int protein = recupererChoixInt("Prot�ine ?");
		//glucide
		int glucid = recupererChoixInt("Glucide ?");
		//lipide
		int lipid = recupererChoixInt("Lipide ?");
		
		//On tente la mise � jour
		food = new Food(foodId, name,protein,glucid,lipid);
		
		if(food.update()) {
			System.out.println("La mise � jour de l'aliment : "+food.getName()+" s'est d�roul�e avec succ�s");
		}
		//Mise � jour rat�e
		else {
			System.err.println("Probl�me lors de la mise � jour de l'aliment : "+food.getName());
		}
		//On affiche la bdd � jour
		displayAll();
	}
	
	/**
	  * M�thode permettant de factoriser la r�cup�ration d'un entier � la console via le scanner
	  *  
	  * @param String : question
	  * @return int : saisie
	  * @name recupererChoixInt
	  */
	
	
	public static int recupererChoixInt(String question) {
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
	  * @name recupererChoixString
	  */
	
	public static String recupererChoixString(String question) {
		
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
	 * Wait
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
	
	/*
	 * Say "GoodBye !!"
	 *
	 * @access public static
	 * @name quit
	 * @return void
	 * 
	 **/
	
	public static void quit() {
		System.out.println("GoodBye !!!");
		System.exit(0);
	}

}
