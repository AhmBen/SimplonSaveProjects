package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe de gestion des aliments
 * 
 * @author Xavier Tagliarino <xavier.tagliarino@gmail.com>
 * @name Food
 * @access public
 *
 */

public class Food {
	
	/**
	  * Unit� de mesure des calories
	  * 
	  * @access private final static
	  * @name kCalUnit
	  * @var String
	  * 
	  */
	
	private final static String kCalUnit = "KCal";
	
	/**
	  * Unit� de r�f�rence de mesure des masses
	  * 
	  * @access private final static
	  * @name weightUnit
	  * @var String
	  * 
	  */
	
	private final static String weightUnit = " g";
	
	/**
	  * Poids de r�f�rence des alimenyts dans les indication energ�tique
	  * 
	  * @access private final static
	  * @name weightUnit
	  * @var String
	  * 
	  */
	
	private final static String refWeightKCal = "100 g";
	
	/**
	  * Id de l'aliment
	  *
	  * @access private
	  * @name id 
	  * @var int 
	  *
	  */
	
	private int id;
	
	/**
	  * Nom de l'aliment
	  *
	  * @access private
	  * @name name 
	  * @var String 
	  *
	  */
	
	private String name;
	
	/**
	  * Proteines de l'aliment
	  *
	  * @access private
	  * @name protein 
	  * @var int 
	  *
	  */
	
	private int protein;
	
	/**
	  * Glucides de l'aliment
	  *
	  * @access private
	  * @name glucid 
	  * @var int 
	  *
	  */
	
	private int glucid;
	
	/**
	  * Lipides de l'aliment
	  *
	  * @access private
	  * @name lipid 
	  * @var int 
	  *
	  */
	
	private int lipid;
	
	/**
	  * id de la cat�gorie de l'aliment
	  *
	  * @access private
	  * @name categoryId 
	  * @var int 
	  *
	  */
	
	private int categoryId; 
	
	/**
	  * Cat�gory de l'aliment
	  *
	  * @access private
	  * @name category 
	  * @var Category 
	  *
	  */
	
	private Category category; 
	
	/**
	  * Constructeur hyper light 
	  * 
	  * @access public
	  * @name Food
	  */
	
	public Food() {
		
	}
	
	/**
	  * Constructeur light 
	  * 
	  * @access public
	  * @name Food
	  * @param String name
	  * @param int protein
	  * @param int glucid
	  * @param int lipid
	  * 
	  */
	
	public Food(String name, int proteine, int glucid, int lipid) {
		this.setName(name);
		this.setProteine(proteine);
		this.setGlucid(glucid);
		this.setLipid(lipid);		
		
	}
	
	/**
	  * Constructeur complet 
	  * 
	  * @access public
	  * @name Food
	  * @param int id
	  * @param String name
	  * @param int protein
	  * @param int glucid
	  * @param int lipid
	  * 
	  */
	
	public Food(int id, String name, int proteine, int glucid, int lipid) {
		this.setId(id);
		this.setName(name);
		this.setProteine(proteine);
		this.setGlucid(glucid);
		this.setLipid(lipid);		
		
	}
	
	/**
	  * Tente de trouver un aliment via un id
	  * 
	  * @access public
	  * @name find
	  * @param id de l'aliment
	  * @return boolean : indique si l'aliment a �t� trouv�
	  * 
	  */
	
	public boolean find(int foodId) {
		Connection conn = SqlQuery.getConnection();
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(
					"SELECT id	,"
					+ " name	,"
					+ "	protein	,"
					+ "	glucid	,"
					+ "	lipid	,"
					+ "	id_category"
					+ " FROM foods WHERE id = ? "
					);
			
			statement.setInt(1, foodId);
			
			ResultSet result = statement.executeQuery();		
			
			while(result.next()) {
				
				this.setId(result.getInt("id"));
				this.setName(result.getString("name"));
				this.setProteine(result.getInt("protein"));
				this.setGlucid(result.getInt("glucid"));
				this.setLipid(result.getInt("lipid"));
				this.setCategoryId(result.getInt("id_category"));
				
				if(this.getCategory() == null) {
					Category category = new Category();
					category.find(this.getCategoryId());
					this.setCategory(category);
				}
				
				return true;
			}
			
			conn.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
		}
		return false;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProtein() {
		return protein;
	}

	public void setProteine(int proteine) {
		this.protein = proteine;
	}

	public int getGlucid() {
		return glucid;
	}

	public void setGlucid(int glucid) {
		this.glucid = glucid;
	}

	public int getLipid() {
		return lipid;
	}

	public void setLipid(int lipid) {
		this.lipid = lipid;
	}
	
	
	/**
	 * Tente de cr�er l'aliment
	 * 
	 * @access public
	 * @name create
	 * @return boolean : indique si tout s'est bien d�roul�
	 *
	 */
	
	public boolean create() {
		Connection conn = SqlQuery.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement("INSERT INTO foods(name, protein, glucid, lipid, id_category) VALUES ( ?, ?, ?, ?, ?);");
			statement.setString(1,this.getName());
			statement.setInt(2,this.getProtein());
			statement.setInt(3,this.getGlucid());
			statement.setInt(4,this.getLipid());
			statement.setLong(5,this.getCategory().getId());
			statement.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {				
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			return false;
		}
		return true;
		
		
	}
	
	/**
	 * Tente de mettre � jour l'aliment
	 * 
	 * @access public
	 * @name update
	 * @return boolean : indique si tout s'est bien d�roul�
	 *
	 */
	
	public boolean update() {
		Connection conn = SqlQuery.getConnection();
		try {
			
			conn.setAutoCommit(false);
			
			PreparedStatement statement = conn.prepareStatement("UPDATE foods SET name = ?, protein = ?, glucid = ?, lipid = ? WHERE id = ?;");
			statement.setString(1,this.getName());
			statement.setInt(2,this.getProtein());
			statement.setInt(3,this.getGlucid());
			statement.setInt(4,this.getLipid());
			statement.setLong(5,this.getId());
			statement.executeUpdate();	
			conn.commit();
		} catch (SQLException e) {			
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
	/**
	 * Tente de supprimer l'aliment
	 * 
	 * @access public
	 * @name delete
	 * @return boolean : indique si tout s'est bien d�roul�
	 *
	 */
	
	public boolean delete() {
		Connection conn = SqlQuery.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement("DELETE FROM foods WHERE id = ?");
			statement.setLong(1,this.getId());
			statement.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			return false;
		}
		
		return true;
	}
	
	
	
	public String toString() {
		
		String str = "";
		
		String[] tabString = {
				String.valueOf(this.getId()),
				this.getName(),
				this.getProtein()+" "+weightUnit,
				this.getGlucid()+" "+weightUnit,
				this.getLipid()+" "+weightUnit,
				String.valueOf(this.getEnergy()),
				kCalUnit+" / "+refWeightKCal
		};			
		
		int lengthCol = 14;
		
		for(int i = 0 ; i < (lengthCol + 2) * tabString.length ; i++) {
			str += "-";
		}
		
		str += "\n";		
		
		for(String string : tabString) {			
			
			str += "|"+String.format("%-14s", string)+"|";
		}
		
		str += "\n";
				
		for(int i = 0 ; i < (lengthCol + 2) * tabString.length ; i++) {
			str += "-";
		}
		
		str += "\n";
		
		return str;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public double getEnergy() {
		return 4 * this.getProtein() + 4 * this.getGlucid() + 9 * this.getLipid();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
}
