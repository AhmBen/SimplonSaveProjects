package designPattern.decorateur;

public class Decorateur implements Traitement{

	protected Traitement traitement;
	
	public Decorateur() {
		
	}
	
	public Decorateur(Traitement traitement) {
		this.traitement = traitement;
	}
	
	@Override
	public void methode() {
		// TODO Auto-generated method stub
		System.out.println("M�thode ex�cut�e de la classe "+this.getClass().getSimpleName()+" avec quelques fonctionnalit�s");
		if(this.traitement != null) {
			this.traitement.methode();
		}
	}
	
}
