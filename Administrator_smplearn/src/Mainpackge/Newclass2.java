package Mainpackge;

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
 * Servlet implementation class Newclass2
 */
@WebServlet("/Newclass2")
public class Newclass2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Newclass2() {
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
        String newclass= request.getParameter("class");
        int no= Integer.parseInt(request.getParameter("no"));
        try
        {
     	   Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con =  
            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
            String s1 = "select * from classes where class=?";
            PreparedStatement ps = con.prepareStatement(s1);
           
            ps.setString(1,newclass); 
            ResultSet rs = ps.executeQuery();
            
            
            if(rs.next())
            {
            	RequestDispatcher rd = request.getRequestDispatcher("HomePage2.html");
      		  rd.include(request, response);
      		out.println("INVALID INPUT SUCH CLASS ALREADY EXISTS!!!");
            	
            	
            	
            }
            else
            {

            	String s2="insert into classes values(?,?)";
                PreparedStatement ps2 = con.prepareStatement(s2);
                ps2.setString(1,newclass);
                ps2.setInt(2,no);
                ps2.executeUpdate();
                RequestDispatcher rd = request.getRequestDispatcher("HomePage2.html");
                rd.include(request, response);
                out.println("SUCCESS!!!");
         	  
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
