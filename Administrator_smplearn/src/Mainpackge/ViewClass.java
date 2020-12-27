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
 * Servlet implementation class ViewClass
 */
@WebServlet("/ViewClass")
public class ViewClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewClass() {
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
        String classview = request.getParameter("class");
        try
        {
     	   Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con =  
            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
            String s1 = "select a.stuname,a.stuclass,b.teacher,b.subject from student a inner join assign b on a.stuclass=b.class and b.class=?";
            
            PreparedStatement ps = con.prepareStatement(s1);
           
            ps.setString(1,classview); 
            ResultSet rs = ps.executeQuery();
                	if(1==1)
                    {
                		RequestDispatcher rd = request.getRequestDispatcher("result.html");
                		
                		while(rs.next())
                		{
                			String stuname=rs.getString(1);
                			String stuclass=rs.getString(2);
                			String teacher=rs.getString(3);
                			String subject=rs.getString(4);
                			out.println("<h1>"+"STUDENT NAME:"+ stuname+" STUDENT CLASS:"+stuclass+" TEACHER :"+teacher+" SUBJECT: "+subject+"</h1>");
                		}
                		  rd.include(request, response);
                	
                    }
                	else
                	{
                		RequestDispatcher rd = request.getRequestDispatcher("HomePage2.html");
                		  rd.include(request, response);
                		out.println("INVALID INPUT SUCH SUBJECT DOENT EXISTS!!!");
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
