package constrainteEtTransactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class VentesAuPif {
	public static void main(String[] args) {
		Connection conn = SqlQuery.getConnection();
		Random rng= new Random();
		
		int qty = rng.nextInt(10)+1;		
		int i = 0;
		while(i < 1) {
			try {
				conn.setAutoCommit(false);
				//VentesAuPif.Credit( 1, rng.nextInt(100)+1);
				VentesAuPif.sell( 1, 1, qty);
				conn.commit();
			} catch (SQLException e) {				
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
			} 						 	
			i++;
		}	
	}
	
	public static void Credit(int clientId,int cash) {
		
		System.out.println("Cr�dit allou� : "+cash);
		Connection conn = SqlQuery.getConnection();
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement("UPDATE customer SET credit = credit + ? WHERE pk_id = ?");
			statement.setInt(1, cash);
			statement.setInt(2, clientId);
			statement.executeUpdate();
			statement = conn.prepareStatement("UPDATE cash SET amount = amount + ? WHERE pk_id = 1");
			statement.setInt(1, cash);			
			statement.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
	
	
	public static void sell(int clientId,int productId,int qty) {
		System.out.println("Vente allou� de : "+qty+" articles");
		Connection conn = SqlQuery.getConnection();
		PreparedStatement statement;
		try {
			
			statement = conn.prepareStatement("SELECT * FROM PRODUCT WHERE pk_id = ?");
			statement.setInt(1, productId);
			ResultSet res = statement.executeQuery();
			
			while(res.next()) {
				double prix = res.getDouble("price");	
				
				statement = conn.prepareStatement("UPDATE cash SET amount = amount - (? * ?) WHERE pk_id = ?");
				statement.setInt(1, qty);
				statement.setDouble(2, prix);
				statement.setInt(3, productId);
				statement.executeUpdate();	
				System.out.println(statement);
				statement = conn.prepareStatement("UPDATE customer SET credit = credit - (? * ?) WHERE pk_id = ?");
				statement.setInt(1, qty);
				statement.setDouble(2, prix);				
				statement.setInt(3, clientId);
				statement.executeUpdate();	
				System.out.println(statement);
				statement = conn.prepareStatement("UPDATE product SET qty = qty - ? WHERE pk_id = ?");
				statement.setInt(1, qty);
				statement.setInt(2, productId);
				statement.executeUpdate();
				System.out.println(statement);
				statement = conn.prepareStatement("UPDATE sales_log SET qty = qty + ? WHERE fk_product_id = ?");
				statement.setInt(1, qty);
				statement.setInt(2, productId);
				statement.executeUpdate();
				System.out.println(statement);
				break;
			}		
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
}
