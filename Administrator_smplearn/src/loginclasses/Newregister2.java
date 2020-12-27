package loginclasses;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Newregister2
 */
@WebServlet("/Newregister2")
public class Newregister2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Newregister2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
PrintWriter out = response.getWriter();
        
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");

        //Connection establishment with DB
       try{
    	   Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con =  
           DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
               String s1="select * from adminLogin where uname=?";
               PreparedStatement ps1=con.prepareStatement(s1);
               ps1.setString(1,uname);
               ResultSet rs=ps1.executeQuery();
               if(rs.next())
               {
            	   RequestDispatcher rd = request.getRequestDispatcher("register22.html");
           		   rd.include(request, response);
            	   out.println("UserName already Exists");
            	   
               }
               else
               {
               String s2="insert into adminLogin values(?,?,?,?,?)";
              
               PreparedStatement ps2=con.prepareStatement(s2);
               ps2.setString(1, uname);
               ps2.setString(2, pwd);
               ps2.setString(3, name);
               ps2.setString(4, email);
               ps2.setString(5, mobile);
               ps2.executeUpdate();
               RequestDispatcher rd2 = request.getRequestDispatcher("index2.html");
       		   rd2.include(request, response);
               out.println("Registered Sucessfully!!!");
               }
	}
       catch(Exception e)
       {
    	   
       }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
