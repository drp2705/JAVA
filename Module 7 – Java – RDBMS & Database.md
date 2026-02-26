# Module 7 – Java – RDBMS \& Database Programming with JDBC



##### Introduction to JDBC :



What is JDBC (Java Database Connectivity)?



JDBC is a Java API that allows Java programs to connect and interact with databases (like MySQL, Oracle, etc.).





Importance of JDBC in Java Programming :



✔ Connects Java application to database



✔ Perform CRUD operations (Create, Read, Update, Delete)



✔ Execute SQL queries



✔ Retrieve and process data



✔ Used in web apps, desktop apps, enterprise systems



Without JDBC, Java cannot directly communicate with databases.





DBC Architecture (Main Components)

1️⃣ Driver Manager



Manages database drivers



Creates connection between Java app and database.





2️⃣ Driver



Software that allows Java to talk to specific database



Example: MySQL Driver



It converts Java commands into database-understandable format.





3️⃣ Connection



Represents connection between Java and database



Used to create statements





4️⃣ Statement



Used to execute SQL queries



Types:



Statement



PreparedStatement



CallableStatement





5️⃣ ResultSet



Stores data returned from SELECT query



Used to read database records

###### 



###### Lab Exercise :



import java.sql.Connection;

import java.sql.DriverManager;



public class JdbcConnectionExample {



&nbsp;   public static void main(String\[] args) {



&nbsp;       // Database URL

&nbsp;       String url = "jdbc:mysql://localhost:3306/testdb";

&nbsp;       String username = "root";

&nbsp;       String password = "root";



&nbsp;       try {

&nbsp;           // Load JDBC Driver

&nbsp;           Class.forName("com.mysql.cj.jdbc.Driver");

&nbsp;           System.out.println("Driver Loaded Successfully!");



&nbsp;           // Establish Connection

&nbsp;           Connection con = DriverManager.getConnection(url, username, password);

&nbsp;           System.out.println("Connection Established Successfully!");



&nbsp;           // Close Connection

&nbsp;           con.close();

&nbsp;           System.out.println("Connection Closed!");



&nbsp;       } catch (Exception e) {

&nbsp;           e.printStackTrace();

&nbsp;       }

&nbsp;   }

}







##### JDBC Driver Types :



###### &nbsp;Overview of JDBC Driver Types:



1️⃣ Type 1: JDBC-ODBC Bridge Driver



🔹 Connects Java → JDBC → ODBC → Database

🔹 Uses ODBC driver installed on system



✔ Advantages



Easy to use



Good for small projects



❌ Disadvantages



Slow performance



Requires ODBC setup



Not platform independent



Removed from Java 8+





2️⃣ Type 2: Native-API Driver



🔹 Uses database’s native library (DLL files)



Java → JDBC → Native API → Database



✔ Advantages



Faster than Type 1



❌ Disadvantages



Requires native database libraries



Not fully platform independent





3️⃣ Type 3: Network Protocol Driver



🔹 Uses middleware server



Java → JDBC → Middleware Server → Database



✔ Advantages



Fully platform independent



No native library needed on client side



❌ Disadvantages



Requires middleware server



Network dependency



4️⃣ Type 4: Thin Driver (Most Important ⭐)



🔹 Directly converts Java calls to database protocol



Java → JDBC → Database



✔ Advantages



Fastest performance



Platform independent



No middleware or native library required



Most commonly used



❌ Disadvantages



Database specific driver required





###### Steps for Creating JDBC Connections



Step-by-Step Process to Establish a JDBC Connection:

1\. Import the JDBC packages

2\. Register the JDBC driver

3\. Open a connection to the database

4\. Create a statement

5\. Execute SQL queries

6\. Process the resultset



###### Write a Java program to establish a connection to a database and print a

###### confirmation message upon successful connection.





package JDBC\_connection;



import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;



public class Jdbc\_conn {



&nbsp;	Connection con;

&nbsp;	public Connection gConnection() {

&nbsp;		//load driver for sql

&nbsp;		try {

&nbsp;			Class.forName("com.mysql.cj.jdbc.Driver");

&nbsp;			con=	DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");

&nbsp;		} catch (ClassNotFoundException e) {

&nbsp;			// TODO Auto-generated catch block

&nbsp;			e.printStackTrace();

&nbsp;		} catch (SQLException e) {

&nbsp;			// TODO Auto-generated catch block

&nbsp;			e.printStackTrace();

&nbsp;		}

&nbsp;		return con;

&nbsp;	}

}









###### Create a program that inserts, updates, selects, and deletes data using Statement,

###### Modify the program to use PreparedStatement for parameterized queries,

###### JDBC CRUD Operations (Insert, Update, Select, Delete).



package Servlet;



import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;



import JDBC\_connection.Jdbc\_conn;



/\*\*

&nbsp;\* Servlet implementation class insertServlet

&nbsp;\*/

@WebServlet("/insertServlet")

public class insertServlet extends HttpServlet {

&nbsp;	private static final long serialVersionUID = 1L;

&nbsp;      

&nbsp;   /\*\*

&nbsp;    \* @see HttpServlet#HttpServlet()

&nbsp;    \*/

&nbsp;   public insertServlet() {

&nbsp;       super();

&nbsp;       // TODO Auto-generated constructor stub

&nbsp;   }



&nbsp;	/\*\*

&nbsp;	 \* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)

&nbsp;	 \*/

&nbsp;	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

&nbsp;		// TODO Auto-generated method stub

&nbsp;		

&nbsp;		Jdbc\_conn conn=new Jdbc\_conn();

&nbsp;		Connection con;

&nbsp;		String action=request.getParameter("action");

&nbsp;		String id=request.getParameter("id");

&nbsp;		if(action.equals("edit")) {

&nbsp;			

&nbsp;			con=conn.gConnection();

&nbsp;			try {

&nbsp;				PreparedStatement psmt=con.prepareStatement("select \* from product where id = ?");

&nbsp;				psmt.setInt(1, Integer.parseInt(id));

&nbsp;				ResultSet rs= psmt.executeQuery();

&nbsp;				request.setAttribute("edit", rs);

&nbsp;				RequestDispatcher rd=request.getRequestDispatcher("update.jsp");

&nbsp;				rd.forward(request, response);

&nbsp;			} catch (SQLException e) {

&nbsp;				// TODO Auto-generated catch block

&nbsp;				e.printStackTrace();

&nbsp;			}

&nbsp;			

&nbsp;		}

&nbsp;		

&nbsp;		else if(action.equals("update"))

&nbsp;		{

&nbsp;			con=conn.gConnection();

&nbsp;			String username=request.getParameter("username");

&nbsp;			String password=request.getParameter("password");

&nbsp;			String email=request.getParameter("email");

&nbsp;			int edit\_id=Integer.parseInt(request.getParameter("id"));

&nbsp;			try {

&nbsp;				PreparedStatement psmt=con.prepareStatement("update product set username=?, password=?, email=? where id=?");

&nbsp;				psmt.setString(1, username);

&nbsp;				psmt.setString(2, password);

&nbsp;				psmt.setString(3, email);

&nbsp;				psmt.setInt(4, edit\_id);

&nbsp;				

&nbsp;				int i=psmt.executeUpdate();

&nbsp;				if(i>0) {

&nbsp;					RequestDispatcher rd=request.getRequestDispatcher("showdata");

&nbsp;					rd.forward(request, response);

&nbsp;				}

&nbsp;			} catch (SQLException e) {

&nbsp;				// TODO Auto-generated catch block

&nbsp;				e.printStackTrace();

&nbsp;			}

&nbsp;			

&nbsp;		}

&nbsp;		

&nbsp;		else

&nbsp;		{

&nbsp;			con=conn.gConnection();

&nbsp;			String d\_id=request.getParameter("id");

&nbsp;			try {

&nbsp;				PreparedStatement psmt=con.prepareStatement("delete from product where id=?");

&nbsp;				psmt.setInt(1, Integer.parseInt(id));

&nbsp;				

&nbsp;				int i=psmt.executeUpdate();

&nbsp;				if(i>0) {

&nbsp;					RequestDispatcher rd=request.getRequestDispatcher("showdata");

&nbsp;					rd.forward(request, response);

&nbsp;				}

&nbsp;				

&nbsp;			} catch (SQLException e) {

&nbsp;				// TODO Auto-generated catch block

&nbsp;				e.printStackTrace();

&nbsp;			}

&nbsp;		}

&nbsp;		response.getWriter().append("Served at: ").append(request.getContextPath());

&nbsp;	}



&nbsp;	/\*\*

&nbsp;	 \* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)

&nbsp;	 \*/

&nbsp;	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

&nbsp;		// TODO Auto-generated method stub

&nbsp;		

&nbsp;		String username=request.getParameter("username");

&nbsp;		String password=request.getParameter("password");

&nbsp;		String email=request.getParameter("email");

&nbsp;		

&nbsp;		System.out.println(username+" "+password+" "+email);

&nbsp;		Connection con;

&nbsp;		Jdbc\_conn conn=new Jdbc\_conn();

&nbsp;		con=conn.gConnection();

&nbsp;		try {

&nbsp;			PreparedStatement psmt=con.prepareStatement("insert into product(username,password,email) values(?,?,?)");

&nbsp;			psmt.setString(1, username);

&nbsp;			psmt.setString(2, password);

&nbsp;			psmt.setString(3, email);

&nbsp;			

&nbsp;			int i=psmt.executeUpdate();

&nbsp;			if(i>0) {

&nbsp;				request.setAttribute("msg", "Data inserted !!!!!");

&nbsp;				RequestDispatcher rd=request.getRequestDispatcher("home.jsp");

&nbsp;				rd.forward(request, response);

&nbsp;			}

&nbsp;		} catch (SQLException e) {

&nbsp;			// TODO Auto-generated catch block

&nbsp;			e.printStackTrace();

&nbsp;		}

&nbsp;		

&nbsp;		doGet(request, response);

&nbsp;	}



}





###### ResultSet Interface :



Why ResultSet is Used?



To read data returned from database



To move through rows one by one



To fetch column values







