package it.geek.pht.util;

import it.geek.pht.pojo.Persona;

public class CustomUtils implements Constants{

	public static void setNullWhereNecessary(Object o, String custom){
		switch (custom) {
		case PERSONA:
			Persona p = (Persona)o;
			p.setIdPersona(0==p.getIdPersona()?null:p.getIdPersona());
			p.setNome("".equals(p.getNome())?null:p.getNome());
			p.setEmail("".equals(p.getEmail())?null:p.getEmail());
			break;

		default:
			break;
		}
	}

}
