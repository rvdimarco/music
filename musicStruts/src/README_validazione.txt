Per attivare la validazione base di struts seguire i seguenti passi.

Creare il file ApplicationResources.properties all'interno del package base (ad esempio it.geek)

#queste saranno applicate globalmente
errors.prefix=<li>
errors.suffix=</li>
errors.footer=</ul><hr>
errors.header=<h3><font color="red">Validation Error</font></h3>Devi correggere i seguenti errori prima di continuare:<ul>

#queste sono le etichette relative allo specifico messaggio di errore
errors.fiels.blank=hai inserito una parola vuota
errors.fiels.lunghezza_insufficiente=hai inserito meno di due caratteri per la parola
errors.fiels.not_pera=hai inserito una parola non valida (pera)



Quindi all'interno del nostro struts-config.xml inseriamo il seguente tag alla fine, prima del tag </struts-config>


<message-resources parameter="it.geek.ApplicationResources"></message-resources>

Nel nostro oggetto ActionForm (ad esempio AutoForm) facciamo l'override del metodo 
validate nel seguente modo:

public ActionErrors validate(ActionMapping map, HttpServletRequest req) {
	
		ActionErrors errors = new ActionErrors();
		
		if ("".equals(parola)) {
			errors.add("parola", new ActionMessage("errors.fiels.blank"));
		}
		if (parola.length()<3) {
			errors.add("parola", new ActionMessage("errors.fiels.lunghezza_insufficiente"));
		}
		if ("pera".equalsIgnoreCase(parola)) {
			errors.add("parola", new ActionMessage("errors.fiels.not_pera"));
		}

		
		return errors;
		
	}

altre operazioni da fare nello struts-config.xml:
- impostare l'attributo validate="true" del tag action di riferimento
- impostare l'attributo input="/paginaInCuiTornareInCasoDiErrore.jsp" per indicare in quale pagina saremo reidirizzati in caso di errori di validazione

Infine nella jsp dove si intende mostrare il messaggio all'utente (nell'esempio qui sopra paginaInCuiTornareInCasoDiErrore.jsp):
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>

e nel body
<html:errors/>



