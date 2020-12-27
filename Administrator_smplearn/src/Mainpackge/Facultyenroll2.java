package Mainpackge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Facultyenroll2
 */
@WebServlet("/Facultyenroll2")
public class Facultyenroll2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Facultyenroll2() {
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
        String workexp = request.getParameter("workexp");
        String facaddress = request.getParameter("facaddress");
        String gender= request.getParameter("gender");
        String pno= request.getParameter("pno");
        try
        {
     	   Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con =  
            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
            String s1="insert into teacher values(?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(s1);
               
                ps.setString(1,facname);             
                ps.setString(2,workexp);
                ps.setString(3,pno);
                ps.setString(4,facaddress);
                ps.setString(5,gender);
                ps.executeUpdate();
                RequestDispatcher rd = request.getRequestDispatcher("HomePage2.html");
                
             	out.println("SUCCESFULLY REGISTERED");
             	rd.include(request, response);
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
