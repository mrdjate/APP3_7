package com.APP4;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void ajouterEtudiant(ArrayList<Etudiant> etudiants) {
		
		String 	nom;
		String 	prenom;
		String 	identifiant;
		int 	serie;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nAJOUTER UN ETUDIANT :");
		System.out.print("Entrez le nom : ");				nom 		= sc.nextLine();
		System.out.print("Entrez le prenom : ");			prenom 		= sc.nextLine();
		System.out.print("Entrez l'identifiant : ");		identifiant = sc.nextLine();
		System.out.print("Entrez la serie : ");				serie 		= sc.nextInt();
		
		try {etudiants.add(new Etudiant(nom, prenom, identifiant, serie));}
		catch(IdentifantException e) {}
	}
	
	
	
	public static void supprimerEtudiant(ArrayList<Etudiant> etudiants) {
		
		String identifiant;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nSUPPRIMER UN ETUDIANT :");
		System.out.print("Entrez l'identifiant : ");	identifiant = sc.nextLine();
		
		for (int i = 0; i < etudiants.size(); i++) {
			if(etudiants.get(i).getIdentifant().equals(identifiant)) {
				etudiants.remove(i);
				i--;
			}
		}
	}
	
	
	
	public static void modifierEtudiant(ArrayList<Etudiant> etudiants) {
		
		String identifiant;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nMODIFIER UN ETUDIANT :");
		System.out.print("Entrez l'identifiant : ");	identifiant = sc.nextLine();
		
		for (Etudiant etudiant : etudiants) {if(etudiant.getIdentifant().equals(identifiant)) {etudiant.modifier();}}
	}
	
	
	
	public static void sauvegarderEtudiant(ArrayList<Etudiant> etudiants) {
		
		String identifiant;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nSAUVEGARDER UN ETUDIANT :");
		System.out.print("Entrez l'identifiant : ");	identifiant = sc.nextLine();
		
		for (Etudiant etudiant : etudiants) {if(etudiant.getIdentifant().equals(identifiant)) {etudiant.sauvegarder();}}
	}


	
	public static void sauvegarderSerie(ArrayList<Etudiant> etudiants) {
		try{
			int serie;
			Scanner sc = new Scanner(System.in);
			int nombreEtudiantSerie = 0;
			
			System.out.println("\nSAUVEGARDER UNE SERIE :");
			System.out.print("Entrez la serie : ");	serie = sc.nextInt();
			
			for (Etudiant etudiant : etudiants) {if(etudiant.getSerie() == serie) {nombreEtudiantSerie++;}}
			
			File fichier=new File("/Users/jean-loup/Desktop/Java/APP4/serie" + serie + ".txt"); 
			FileWriter fichierEcriture =new FileWriter(fichier);
			
			fichierEcriture.write("Serie " + serie);
			fichierEcriture.write("\n");
			fichierEcriture.write("\n");
			fichierEcriture.write("Nombre d'etudiants : " + nombreEtudiantSerie);
			fichierEcriture.write("\n");
			for (Etudiant etudiant : etudiants) {
				if(etudiant.getSerie() == serie) {
					fichierEcriture.write("\n");
					fichierEcriture.write(etudiant.getNom() + " " + etudiant.getPrenom() + " : " + etudiant.getIdentifant() + " : ");
					if(etudiant.getMoyenne() != -1) 	{fichierEcriture.write(Double.toString(etudiant.getMoyenne()));}
					else 								{fichierEcriture.write("X");}
				}
			}

			fichierEcriture.close(); 
		} 
		catch (Exception e) {}	
	}
	
	
	
	public static double moyenneGeneralSerie(ArrayList<Etudiant> etudiants, int serie) {
		double moyenne = 0;
		int nombreEtudiantAyantAuMoinsUneNote = 0;
		
		for (Etudiant etudiant : etudiants) {
			if(etudiant.getSerie() == serie) {
				if(etudiant.getMoyenne() != -1) {moyenne += etudiant.getMoyenne(); nombreEtudiantAyantAuMoinsUneNote++;}
			}
		}
		
		if(nombreEtudiantAyantAuMoinsUneNote > 0) 	{moyenne /= nombreEtudiantAyantAuMoinsUneNote;}
		else 										{moyenne = -1;}
		
		return moyenne;
	}
	
	
	
	public static int nombreEtudiantAyantValide(ArrayList<Etudiant> etudiants) {
		int nombreEtudiantValide = 0;
		
		for (Etudiant etudiant : etudiants) {if(etudiant.valideLeSemestre()) {nombreEtudiantValide++;}}
		
		return nombreEtudiantValide;
	}
	
	
	
	public static void informationEtudiantFichier(ArrayList<Etudiant> etudiants, String identifiant) {
		
		for (Etudiant etudiant : etudiants) {if(etudiant.getIdentifant().equals(identifiant)) {etudiant.affichageFichier();}}
	}
	
	

	public static void main(String[] args) {
		
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		
		try {etudiants.add(new Etudiant("PO", "Paul", "PLQXJ129283", 1));}
		catch(IdentifantException e) {}
		
		try {etudiants.add(new Etudiant("GABET", "Robert", "31I", 1));}
		catch(IdentifantException e) {}
		
		try {etudiants.add(new Etudiant("ROCHET", "Polo", "R9F2A028", 2));}
		catch(IdentifantException e) {}
	
		try {etudiants.add(new Etudiant("LA", "Lalala", "LZ122NS", 2));}
		catch(IdentifantException e) {}
		
		ajouterEtudiant(etudiants);
		
		supprimerEtudiant(etudiants);
		
		modifierEtudiant(etudiants);

		sauvegarderEtudiant(etudiants);
		
		sauvegarderSerie(etudiants);
		
		System.out.println("moyenne general serie 2 : " + moyenneGeneralSerie(etudiants, 2));
		
		System.out.println("nombre etudiants ayant valide leur semestre : " + nombreEtudiantAyantValide(etudiants));
		
		informationEtudiantFichier(etudiants, "LZ122NS");
	}
}
