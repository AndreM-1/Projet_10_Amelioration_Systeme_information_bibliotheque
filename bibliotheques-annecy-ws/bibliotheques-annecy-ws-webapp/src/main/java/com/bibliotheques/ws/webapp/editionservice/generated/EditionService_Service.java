package com.bibliotheques.ws.webapp.editionservice.generated;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-12-04T10:23:48.430+01:00
 * Generated source version: 3.2.5
 *
 */
@WebServiceClient(name = "EditionService",
                  wsdlLocation = "file:/C:/Users/Jean%20et%20Maria/Parcours_Developpeur_Application_Java/Projet_10_Amelioration_SIB/bibliotheques-annecy-ws/bibliotheques-annecy-ws-webapp/src/main/resources/wsdl/EditionService.wsdl",
                  targetNamespace = "http://www.example.org/EditionService/")
public class EditionService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.example.org/EditionService/", "EditionService");
    public final static QName EditionServiceSOAP = new QName("http://www.example.org/EditionService/", "EditionServiceSOAP");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Jean%20et%20Maria/Parcours_Developpeur_Application_Java/Projet_10_Amelioration_SIB/bibliotheques-annecy-ws/bibliotheques-annecy-ws-webapp/src/main/resources/wsdl/EditionService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(EditionService_Service.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/Jean%20et%20Maria/Parcours_Developpeur_Application_Java/Projet_10_Amelioration_SIB/bibliotheques-annecy-ws/bibliotheques-annecy-ws-webapp/src/main/resources/wsdl/EditionService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public EditionService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EditionService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EditionService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    public EditionService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public EditionService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public EditionService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns EditionService
     */
    @WebEndpoint(name = "EditionServiceSOAP")
    public EditionService getEditionServiceSOAP() {
        return super.getPort(EditionServiceSOAP, EditionService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EditionService
     */
    @WebEndpoint(name = "EditionServiceSOAP")
    public EditionService getEditionServiceSOAP(WebServiceFeature... features) {
        return super.getPort(EditionServiceSOAP, EditionService.class, features);
    }

}
