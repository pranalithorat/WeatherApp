package rmiServer;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RMIServer {
	
	
	
	public static void main(String[] args) throws Exception {
		try {
			   Registry r = LocateRegistry.getRegistry();
			   r.bind("serverObj", new RMIServant());

	
			   System.out.println("Addition Server is ready.");
			   }catch (Exception e) {
				   System.out.println("Addition Server failed: " + e);
				}
	}
	 

}