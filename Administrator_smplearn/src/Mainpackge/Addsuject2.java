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
 * Servlet implementation class Addsuject2
 */
@WebServlet("/Addsuject2")
public class Addsuject2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addsuject2() {
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
        String subclass = request.getParameter("subclass");
        String sub = request.getParameter("sub");
        String subdesc= request.getParameter("subdesc");
        try
        {
     	   Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con =  
            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
                String s1 = "select * from classes where class=?";
                PreparedStatement ps = con.prepareStatement(s1);
               
                ps.setString(1,subclass); 
                ResultSet rs = ps.executeQuery();
    
                if(rs.next())
                {
                	String s2="insert into subject values(?,?,?)";
                    PreparedStatement ps2 = con.prepareStatement(s2);
                    ps2.setString(1,sub);
                    ps2.setString(2,subclass);
                    ps2.setString(3,subdesc);
                    ps2.executeUpdate();
                    RequestDispatcher rd = request.getRequestDispatcher("HomePage2.html");
                    rd.include(request, response);
                }
                else
                {
             	  RequestDispatcher rd = request.getRequestDispatcher("HomePage2.html");
            		  rd.include(request, response);
            		out.println("INVALID INPUT SUCH CLASS DOESNT EXISTS!!!");
                }
                con.close();
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
