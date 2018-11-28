package com.bibliotheques.appliweb.webapp.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

/**
 * Convertisseur de type permettant de convertir un String en XMLGregorianCalendar et inversement.
 * @author André Monnier
 */
public class XMLGregorianCalendarConverter extends StrutsTypeConverter {
	

	// ======================== Attributs =======================
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(XMLGregorianCalendarConverter.class);

	@Override
	//Conversion String en Date puis en XMLGregorianCalendar.
	public Object convertFromString(Map context, String[] values, Class toClass) {
		LOGGER.info("XMLGregorianCalendarConverter : Appel de la méthode convertFromString ");
		Object vRetour=null;
	
		if(values!=null) {
			if(values.length==1) {
				String vValue=values[0];
				LOGGER.info("XMLGregorianCalendarConverter - String : "+vValue);
				Date date=null;
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					LOGGER.info("XMLGregorianCalendarConverter - Avant de parser la chaine de caractères");
					date=df.parse(vValue);
					LOGGER.info("XMLGregorianCalendarConverter - Date : "+date);
				} catch (ParseException e) {
					LOGGER.info("Parse Exception levée");
					LOGGER.info("Parse Exception Message : " +e.getMessage());
					throw new TypeConversionException("XMLGregorianCalendarConverter - Echec de la conversion String en Date.",e);
				}
				
				GregorianCalendar gCalendar = new GregorianCalendar();
		        gCalendar.setTime(date);
		    
		        try {
		        	vRetour = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
		            LOGGER.info("XMLGregorianCalendarConverter - XMLGregorianCalendar : "+vRetour);
		        } catch (DatatypeConfigurationException ex) {
		        	throw new TypeConversionException("XMLGregorianCalendarConverter - Echec de la conversion Date en XMLGregorianCalendar.",ex);
		        }	

			} else {
				vRetour=performFallbackConversion(context,values,toClass);
			}
		}
		return vRetour;
	}

	@Override
	 //Conversion XMLGregorianCalendar en Date puis en String. 
	public String convertToString(Map context, Object o) {
		LOGGER.info("XMLGregorianCalendarConverter : Appel de la méthode convertToString ");
		String vRetour=null;
		if(o instanceof XMLGregorianCalendar) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			XMLGregorianCalendar vXMLGregorianCalendar=(XMLGregorianCalendar)o;
			LOGGER.info("XMLGregorianCalendarConverter - XMLGregorianCalendar : "+vXMLGregorianCalendar);
			LOGGER.info("XMLGregorianCalendarConverter - Date : "+vXMLGregorianCalendar.toGregorianCalendar().getTime());
			vRetour=df.format(vXMLGregorianCalendar.toGregorianCalendar().getTime());	
			LOGGER.info("XMLGregorianCalendarConverter - String : "+vRetour);
		}else {
			vRetour="";
		}
		return vRetour;
	}
}