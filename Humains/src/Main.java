
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//On cr�e en m�moire un nouvel objet de type Humain
			//�a appelle automatiquement la m�thode Humain() : constructeur de la class Humain
		Humain monHumain = new Humain("Xavier",38,'H');		
			//On r�cup�re la sortie de la m�thode pr�senter de l'objet en cours et on affiche cette chaine de caract�res 
		System.out.println(monHumain.presenter());	
		
		//On cr�e un deuxi�me objet de type Humain en lui affectant d'autres valeurs � ses propri�t�s 
		Humain monHumain2 = new Humain("Waby",29,'F');
		System.out.println(monHumain2.presenter());
		
		monHumain2.setSexe('H');		
		System.out.println(monHumain2.presenter());
		
		System.out.println("fin !");		
		
	}

}