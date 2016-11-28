package rmiClient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rmiServer.Hello;

/**
 * Servlet implementation class RmiClient
 */
@WebServlet("/RmiClient")
public class RmiClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RmiClient() {
        super();
        // TODO Auto-generated constructor stub
    }
    Hello service;
    public void init() throws ServletException {
    	try {
			service = (Hello) Naming.lookup("serverObj");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("Service " + service);
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int days = 7;
		if (request.getParameter("days").trim().length() > 0)
			days = Integer.parseInt(request.getParameter("days"));
		request.setAttribute("output",service.getContent(request.getParameter("cityName"),days ));
		
		doGet(request, response);
	}

}
