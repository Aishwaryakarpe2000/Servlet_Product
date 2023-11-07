package servlets_studend;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		
		String email=req.getParameter("email"); 
		String password=req.getParameter("password");
		
		ServletContext ct=getServletContext();
		String sname=ct.getInitParameter("sname");
		
		School s=new School();
		
		s.setEmail(email);
		s.setPassword(password);
		
		SchoolCrud crud=new SchoolCrud();
		try
		{
			School dbsname=crud.login(email);
			PrintWriter pw=res.getWriter();
			if(password.equals(dbsname.getPassword()) && sname.equals(dbsname.getSname()))
			{
				//pw.print("login success");
				
//				RequestDispatcher rd=req.getRequestDispatcher("success.html");
//				rd.forward(req, res);	//sends the request
				
				res.sendRedirect("Success.html");
			}
			else
			{
				//pw.print("login failed");
				
				RequestDispatcher rd=req.getRequestDispatcher("login.html");
				rd.include(req, res);	//sends back response
			}
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
}
