package com.APP4;

public class IdentifantException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public IdentifantException(String nom, String prenom) {
		System.out.println("Identifiant non valide : " + nom + " " + prenom);
	}
}
