package Grille;

import java.util.ArrayList;

import org.w3c.dom.views.AbstractView;

/**
 * Classe des cases d'un morpion
 * 
 * @author <xavier.tagliarino@gmail.com>
 * @name Case 
 *  
 */
public class Case extends AbstractGrilleElement{
	
	/**
	  * Valeur de la case
	  * 
	  * @access private
	  * @name position
	  * @var String
	  * 
	  */
	
	private String value = "";
	
	/**
	  * Status
	  * 
	  * @access private
	  * @name modified
	  * @var boolean
	  * 
	  **/
	
	private boolean modified = false;
	
		
	/**
	 * Constructeur qui initialise la valeur mais en indiquant que la case n'est pas modifi�e car �tat initial
	 * 
	 * @access public
	 * @name Case
	 * @param int Value
	 */
	
	public Case(String value) {		
		this.setValue(value);
		this.setModified(false);		
	}
	
	/**
	  * renvoie la valeur et indique � la case qu'elle vient d'�tre modifi�e
	  *
	  * @access public
	  * @name getValue
	  * @return String
	  * 
	  */
	
	
	public String getValue() {
		return value;
	}
	
	/**
	  * renseigne la valeur et indique � la case qu'elle vient d'�tre modifi�e
	  *
	  * @access public
	  * @name setValue
	  * @param String
	  * 
	  */

	public void setValue(String value) {
		this.value = value;
		this.setModified(true); 
	}
	
	/**
	  * Indique si la case est modifi�e
	  *
	  * @access public
	  * @name setModified
	  * @return boolean
	  * 
	  */

	public boolean isModified() {
		return this.modified;
	}
	
	/**
	  * Indique � la case qu'elle est modifi�e
	  *
	  * @access private
	  * @name setModified
	  * @param boolean
	  * 
	  */

	private void setModified(boolean modified) {
		this.modified = modified;
	}
	
	/**
	  * La on ne fait rien expr�s car une case ne peut pas contenir de case
	  *
	  * @access public
	  * @name addCase
	  * @param String
	  * 
	  */
	
	public void addCase(String value) {
		//Nothing
	}
	
	/**
	  * La on ne fait rien expr�s car une case ne peut pas contenir de case
	  *
	  * @access public
	  * @name addCase
	  * @param Case
	  * 
	  */
	
	public void addCase(Case maCase) {
		
	}
	
	/**
	  * R�cup�re une case � l'index i : ici null
	  * 
	  * @access public
	  * @name getCase
	  * @param int
	  * @return Case 
	  * 
	  */ 
	
	public Case getCase(int i) {
		return null;
	}
	
	/**
	  * R�cup�re les cases : ici c'est null 
	  * 
	  * @access public
	  * @name getCases
	  * @return ArrayList<Case> ou null
	  * 
	  */ 
	
	public ArrayList<Case> getCases() {
		return null;
	}

}
