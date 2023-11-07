package servlets_studend;

import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException {

		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String fname = req.getParameter("fname");
		String mname = req.getParameter("mname");
		int age = Integer.parseInt(req.getParameter("age"));
		long phone = Long.parseLong(req.getParameter("phone"));
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		School s = new School();
		s.setId(id);
		s.setName(name);
		s.setFname(fname);
		s.setMname(mname);
		s.setAge(age);
		s.setPhone(phone);
		s.setAddress(address);
		s.setEmail(email);
		s.setPassword(password);

//		SchoolCrud crud = new SchoolCrud();
		ServletContext ct=getServletContext();
		String sname=ct.getInitParameter("sname");
		s.setSname(sname);
	
		String fee=req.getParameter("fees");
		if(fee.equals("onetime"))
		{
			ServletConfig cf=getServletConfig();
			double fees=Double.parseDouble(cf.getInitParameter("onetime"));
			s.setFees(fees);
		}
		else
		{
			ServletConfig cf=getServletConfig();
			double fees=Double.parseDouble(cf.getInitParameter("installment"));
			s.setFees(fees);
		}
		
		
		SchoolCrud crud=new SchoolCrud();
		try
		{
			int result=crud.signUp(s);
			PrintWriter pw=res.getWriter();
			if(result!=0)
			{
//				RequestDispatcher rd=req.getRequestDispatcher("Successfull...!");
//				rd.forward(req, res);
				
				pw.print("Data inserted..!");
			}
			else
			{
//				RequestDispatcher rd=req.getRequestDispatcher("Failed..!");
//				rd.include(req, res);
				
				pw.print("Data Not inserted..!");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
					

	}

}

