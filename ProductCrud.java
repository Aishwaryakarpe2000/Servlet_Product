package servlets_product1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ProductCrud {

	
	public int saveProduct(Product p) throws Exception 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prodservdb?user=root&password=root");
	
		PreparedStatement ps=con.prepareStatement("insert into product values(?,?,?,?,?)");
		ps.setInt(1, p.getId());
		ps.setString(2, p.getName());
		ps.setDouble(3, p.getPrice());
		ps.setString(4, p.getBrand());
		ps.setString(5, p.getState());
		
		int count=ps.executeUpdate();
		return count;
	}
}
