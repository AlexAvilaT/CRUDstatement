package com.crudstmnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * Servlet que sirve para dar de alta un producto
 * @author Avila
 * @since 1.0
 * @see javax.servlet.http.HttpSerlvlet
 * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html">link de ayuda</a>
 * 
 *  */
@WebServlet("/createServlet")
public class CreateServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 
	 * @param request es te parametro me sirve para recibir los datos del cliente
	 * @param response este parametro me sirve para enviar
	 * 
	 */
	//Metodo que cacha las solicitudes post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("application/json charset ='utf-8'");
		PrintWriter output = response.getWriter();		
	
		
		//1. Declaramos las variables
		String urlServidor = "jdbc:mysql://localhost:3306/tiendita?useSSL=false&serverTimezone=UTC";
		String nombreUsuario = "root";
		String password = "root";
		int rowsAffected = 0;
		
		String nameProduct = request.getParameter("txtNameProduct");
		double priceProduct = Double.parseDouble(request.getParameter("txtPriceProduct"));
		//2. Declaramos objetos
		/*
		Connection conn=null;
		Statement stmnt = null;
		ResultSet rs=null;
		*/
		//3. Instanciamos el driver
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(urlServidor, nombreUsuario, password);  /*esta clase se alimenta del driver*/
			Statement stmnt = conn.createStatement();
			rowsAffected = stmnt.executeUpdate("INSERT INTO productos (nombreProducto, precioProducto) VALUES('"+nameProduct+"', "+priceProduct+")");
				if(rowsAffected!=0)
				{
					output.append("Registro añadido con exito!");
				}
				else
				{
					output.append("Registro fallido");
				}
				stmnt.close();
				conn.close();
		}
		catch(Exception e)
		{
			
		}
		output.close();
		//4. Abrimos la conexion 
		//5. Ejecutamos la sequencia
		//6. Procesamos los datos
		//7. Cerramos la conexion
		
	}

}
