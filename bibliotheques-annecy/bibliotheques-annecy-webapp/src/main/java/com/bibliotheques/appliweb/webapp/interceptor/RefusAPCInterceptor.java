package com.bibliotheques.appliweb.webapp.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Intercepteur permettant de bloquer l'accès à la page de connexion et à la page de création de compte pour un utilisateur déjà connecté.
 * @author André Monnier
 */
public class RefusAPCInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1335959011697441679L;
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(RefusAPCInterceptor.class);


	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String vResult;
		if(invocation.getInvocationContext().getSession().get("user")==null) {
			vResult=invocation.invoke();	
		} else {
			vResult="refus-acces-page-connexion";
		}
		LOGGER.info("RefusAPCInterceptor - Intercepteur :"+vResult);
		return vResult;
	}
}