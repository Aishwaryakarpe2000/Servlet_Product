package servlets_product1;

import java.io.PrintWriter;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductController extends HttpServlet{
protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		double price=Double.parseDouble(req.getParameter("price"));	 
		String brand=req.getParameter("brand");
		String state=req.getParameter("state");
		
		Product product=new Product();
		
		product.setId(id);
		product.setName(name);
		product.setBrand(brand);
		product.setState(state);
		
		ServletContext context=getServletContext();
		double CGST=Double.parseDouble(context.getInitParameter("CGST"));
		if(state.equals("MH")) {
			ServletConfig config=getServletConfig();
			double SGST=Double.parseDouble((config.getInitParameter("MH")));
			price+=(CGST+SGST)*price;
		}
		
		else {
			ServletConfig config=getServletConfig();
			double SGST=Double.parseDouble((config.getInitParameter("KAR")));
			price+=(CGST+SGST)*price;
			
		}
		product.setPrice(price);
		
		ProductCrud crud=new ProductCrud();
		
		try {
			int result=crud.saveProduct(product);
			PrintWriter pw=res.getWriter();
			if(result!=0)
			{
//				RequestDispatcher  rd =	req.getRequestDispatcher("Successful!");
//      			rd.forward(req, res);
				
				pw.print("data inserted");
			}
			else {
//               RequestDispatcher rd=req.getRequestDispatcher("Failed!");			
//				rd.include(req, res);
				
				pw.print("data not inserted");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
