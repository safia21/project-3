package prdt;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connectage {
	Connection con;
	public Connectage(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/dbgesprod","root","");
			System.out.println("bien connect�e!");
		}
		catch(Exception ex){
			
			System.out.println("probleme de connection!");	
		}
	}
	public Connection laconnexion(){
		return con;
	}

}
