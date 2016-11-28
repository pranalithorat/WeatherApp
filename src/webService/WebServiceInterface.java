package webService;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.XmlRootElement;

@WebService
@SOAPBinding(style=Style.RPC)
@XmlRootElement
public interface WebServiceInterface {
	
	@WebMethod double findAvgTemperature(String cityName);
	@WebMethod double findMinSevenDays(String cityName);
	@WebMethod double findMaxSevenDays(String cityName);
	@WebMethod String getJSONString(String cityName);

}
