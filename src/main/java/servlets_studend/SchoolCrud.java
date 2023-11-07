package servlets_studend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SchoolCrud {

	
	public Connection getConnection() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studservdb","root","root");
		return con;
	}
	
	public int signUp(School s) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("insert into school values(?,?,?,?,?,?,?,?,?,?,?)");
		ps.setInt(1, s.getId());
		ps.setString(2, s.getName());
		ps.setString(3, s.getFname());
		ps.setString(4, s.getMname());
		ps.setInt(5, s.getAge());
		ps.setLong(6, s.getPhone());
		ps.setString(7, s.getAddress());
		ps.setString(8, s.getEmail());
		ps.setString(9, s.getPassword());
		ps.setDouble(10, s.getFees());
		ps.setString(11, s.getSname());
		
		int count=ps.executeUpdate();
		con.close();
		return count;
	}

	public School login(String email) throws Exception {
		
		Connection con = getConnection();

		PreparedStatement ps = con.prepareStatement("select * from school where email=?");
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		School s=new School();
		
		while(rs.next())
		{
			String password=rs.getString("password");
			 s.setPassword(password);
			String sname=rs.getString("sname");
			s.setSname(sname);
		}
		con.close();
		return s;
	}
}
