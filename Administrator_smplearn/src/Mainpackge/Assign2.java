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
 * Servlet implementation class Assign2
 */
@WebServlet("/Assign2")
public class Assign2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Assign2() {
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
        String facname = request.getParameter("facname");
        String classw = request.getParameter("class");
        String sub = request.getParameter("sub");
        try
        {
     	   Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con =  
            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
            String s1 = "select * from classes where class=?";
            PreparedStatement ps = con.prepareStatement(s1);
           
            ps.setString(1,classw); 
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
            	String s2 = "select * from teacher where facname=?";
                PreparedStatement ps2 = con.prepareStatement(s2);
               
                ps2.setString(1,facname); 
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next())
                {
            	
                	String s3="select * from subject where subjname=?";
                	PreparedStatement ps3 = con.prepareStatement(s3);
                	ps3.setString(1,sub);
                	ResultSet rs3 = ps3.executeQuery();
                	if(rs3.next())
                    {
                		String s4="insert into assign values(?,?,?)";
                        PreparedStatement ps4 = con.prepareStatement(s4);
                        ps4.setString(1,facname);
                        ps4.setString(2,classw);
                        ps4.setString(3,sub);
                        ps4.executeUpdate();
                        RequestDispatcher rd = request.getRequestDispatcher("HomePage2.html");
       
                	rd.include(request, response);
                	out.println("SUCCESS!!!");
                    }
                	else
                	{
                		RequestDispatcher rd = request.getRequestDispatcher("HomePage2.html");
                		  rd.include(request, response);
                		out.println("INVALID INPUT SUCH SUBJECT DOENT EXISTS!!!");
                	}
                }
                else
                {
                	RequestDispatcher rd = request.getRequestDispatcher("HomePage2.html");
          		  rd.include(request, response);
          		out.println("INVALID INPUT SUCH TEACHER DOENT EXISTS!!!");
                }
            }
            else
            {
         	  RequestDispatcher rd = request.getRequestDispatcher("HomePage2.html");
        		  rd.include(request, response);
        		out.println("INVALID INPUT SUCH CLASS DOENT EXISTS!!!");
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
