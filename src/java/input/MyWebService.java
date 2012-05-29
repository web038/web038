/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;



/**
 *
 * @author web038
 */

@WebService(name = "MyWebService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class MyWebService {
    
	static {
		System.setProperty("com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStackTrace", "true");
	}
        
        
	
        
}
