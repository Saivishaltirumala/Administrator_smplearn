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
 * Servlet implementation class Studentenroll2
 */
@WebServlet("/Studentenroll2")
public class Studentenroll2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Studentenroll2() {
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
        String stuname = request.getParameter("stuname");
        String stuclass = request.getParameter("stuclass");
        String address=request.getParameter("stuadress");
        String gender=request.getParameter("gender");
        String pname=request.getParameter("pname");
        String pno=request.getParameter("pno");
        try
        {
     	   Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con =  
            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
            String s1="insert into student values(?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(s1);
                          
                ps.setString(1,stuname);
                ps.setString(2,stuclass);
                ps.setString(3,address);
                ps.setString(4,gender);
                ps.setString(5,pname);
                ps.setString(6,pno);
                ps.executeUpdate();
                RequestDispatcher rd = request.getRequestDispatcher("HomePage2.html");
                rd.include(request, response);
                out.println("STUDENT ENROLLED SUCCESSFULLY");
                
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
