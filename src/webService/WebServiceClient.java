package webService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.google.gson.Gson;


/**
 * Servlet implementation class WebServiceClient
 */
@WebServlet("/WebServiceClient")
public class WebServiceClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebServiceClient() {
        super();
        // TODO Auto-generated constructor stub
    }
    WebServiceInterface ws;
    public void init() throws ServletException {
    	URL url = null;
		try {
			url = new URL("http://localhost:9999/ws/weather?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        //1st argument service URI, refer to wsdl document above
	//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://webService/", "WebServiceImplService");

        Service service = Service.create(url, qname);

         ws = service.getPort(WebServiceInterface.class);
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("webServiceIndex.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cityName = request.getParameter("cityName");
		try {
			System.out.println("Average temperature : " + ws.findAvgTemperature(cityName));
		request.setAttribute("average", ws.findAvgTemperature(cityName));
		request.setAttribute("min", ws.findMinSevenDays(cityName));
		request.setAttribute("max", ws.findMaxSevenDays(cityName));
		String jsonString = ws.getJSONString(cityName);
		if (jsonString != null) {
			Gson gson = new Gson();
			request.setAttribute("output" ,gson.fromJson(jsonString, Object.class));
			System.out.println("output request attribute is set");
		}
		
		} catch(Exception e) {
			System.out.println("Exception :" +e);
			
		}
		doGet(request, response);
	}

}
