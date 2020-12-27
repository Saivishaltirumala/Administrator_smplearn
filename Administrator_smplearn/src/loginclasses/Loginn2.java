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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Loginn2
 */
@WebServlet("/Loginn2")
public class Loginn2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loginn2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       
       //Capture the form data into servlet
       String uname = request.getParameter("uname");
       String pwd = request.getParameter("pwd");
       HttpSession session = request.getSession();
       session.setAttribute("uname", uname);
       //Connection establishment with DB
      try
      {
   	   Class.forName("oracle.jdbc.driver.OracleDriver");
          Connection con =  
          DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
              String s1 = "select * from adminlogin where uname=? and pwd=?";
              PreparedStatement ps = con.prepareStatement(s1);
             
              ps.setString(1,uname);             
              ps.setString(2,pwd);
              ResultSet rs = ps.executeQuery();
  
              if(rs.next())
              {
           	   response.sendRedirect("HomePage2.html");
              }
              else
              {
           	  RequestDispatcher rd = request.getRequestDispatcher("index2.html");
          		  rd.include(request, response);
          		out.println("INVALID CREDENTIALS RECHECK YOUR USERNAME AND PASSWORD");
              }
              con.close( );
          } 
         catch(Exception e)
         {
                out.println(e);
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
