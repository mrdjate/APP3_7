package com.APP4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Etudiant {

	// Champ(s) :
	private String nom;
	private String prenom;
	
	private String identifiant;
	
	private int serie;
	
	private Matiere math;
	private Matiere physique;
	private Matiere geo;
	private Matiere francais;
	private Matiere anglais;

	
	
	// Constructeur(s) :
	public Etudiant(String nom,
					String prenom,
					String identifiant,
					int serie) 
	
					throws IdentifantException {
		
		// On determine le nombre de chiffre dans l'identifiant :
		int nombreChiffreIdentifiant = 0;
		for (int i=0; i < identifiant.length(); i++) {
			if (	identifiant.substring(i, i+1).equals("0") || 
					identifiant.substring(i, i+1).equals("1") || 
					identifiant.substring(i, i+1).equals("2") || 
					identifiant.substring(i, i+1).equals("3") || 
					identifiant.substring(i, i+1).equals("4") || 
					identifiant.substring(i, i+1).equals("5") || 
					identifiant.substring(i, i+1).equals("6") || 
					identifiant.substring(i, i+1).equals("7") || 
					identifiant.substring(i, i+1).equals("8") || 
					identifiant.substring(i, i+1).equals("9")) {
				
				nombreChiffreIdentifiant++;
			}
		}

		// 0n renvoie une exception si l'identifiant n'est pas valide :
		if(		identifiant.length() < 4 ||
				!identifiant.substring(0, 1).equals(nom.substring(0, 1)) ||
				nombreChiffreIdentifiant > 7) {
			
			throw new IdentifantException(nom, prenom);
		} 
		// Si l'identifiant est valide on initialise les champs de l'etudiant :
		else {
			this.nom 			= nom;
			this.prenom 		= prenom;
			
			this.identifiant 	= identifiant;

			this.serie = serie;
			
			this.math 		= new Matiere();
			this.physique 	= new Matiere();
			this.geo 		= new Matiere();
			this.francais 	= new Matiere();
			this.anglais 	= new Matiere();
		}
	}
	
	
	
	// Getter(s) :
	public String getNom() 			{return this.nom;}
	public String getPrenom() 		{return this.prenom;}
	public String getIdentifant() 	{return this.identifiant;}
	public int getSerie() 			{return this.serie;}

	public double getMoyenne() {
		
		double moyenne = 0;
		int nombreMatiereAyantAuMoinsUneNote = 0;
		
		if(this.math.getMoyenne() 		!= -1) 	{moyenne += this.math.getMoyenne(); 	nombreMatiereAyantAuMoinsUneNote++;}
		if(this.physique.getMoyenne() 	!= -1) 	{moyenne += this.physique.getMoyenne(); nombreMatiereAyantAuMoinsUneNote++;}
		if(this.geo.getMoyenne() 		!= -1) 	{moyenne += this.geo.getMoyenne(); 		nombreMatiereAyantAuMoinsUneNote++;}
		if(this.francais.getMoyenne() 	!= -1) 	{moyenne += this.francais.getMoyenne(); nombreMatiereAyantAuMoinsUneNote++;}
		if(this.anglais.getMoyenne() 	!= -1) 	{moyenne += this.anglais.getMoyenne(); 	nombreMatiereAyantAuMoinsUneNote++;}

		if(nombreMatiereAyantAuMoinsUneNote != 0) 	{moyenne /= nombreMatiereAyantAuMoinsUneNote; return moyenne;}
		else 										{return -1;}
	}
	
	public boolean valideLeSemestre() {
		if(getMoyenne() >= 10) 	{return true;}
		else 					{return false;}
	}
	
	
	
	// Methode(s) :
	public void modifier() {
		
		int choix;
		Scanner sc = new Scanner(System.in);

		System.out.println("Entrez le champ :");
		System.out.println("1 : nom");
		System.out.println("2 : prenom");
		System.out.println("3 : identifiant");
		System.out.println("4 : serie");
		System.out.println("5 : math");
		System.out.println("6 : physique");
		System.out.println("7 : geo");
		System.out.println("8 : francais");
		System.out.println("9 : anglais");
		System.out.print("Votre choix : ");	choix = sc.nextInt();
		

		switch (choix) {
		
		case 1:
			System.out.print("Entrez le nom : ");
			this.nom = sc.nextLine();
			break;
			
		case 2:
			System.out.print("Entrez le prenom : ");
			this.prenom = sc.nextLine();
			break;
			
		case 3:
			System.out.print("Entrez l'identifiant : ");
			this.identifiant = sc.nextLine();
			break;
			
		case 4:
			System.out.print("Entrez la serie : ");
			this.serie = sc.nextInt();
			break;
			
		case 5:
			this.math.modifier();
			break;
			
		case 6:
			this.physique.modifier();
			break;
			
		case 7:
			this.geo.modifier();
			break;
			
		case 8:
			this.francais.modifier();
			break;
			
		case 9:
			this.anglais.modifier();
			break;
			
		default:
			System.out.println("choix non valide");
			break;
		}
	}
	
	public void sauvegarder() {
		
		try{
			File fichier=new File("/Users/jean-loup/Desktop/Java/APP4/bulletin_" + this.nom + "_" + this.prenom + ".txt"); 
			FileWriter fichierEcriture = new FileWriter(fichier);
			
			fichierEcriture.write(this.nom + " " + this.prenom);  
			fichierEcriture.write("\n");  
			fichierEcriture.write("\n");  
			fichierEcriture.write(this.identifiant);  
			fichierEcriture.write("\n");  
			fichierEcriture.write("\n");  
			
			fichierEcriture.write("Math		|	1	|	");
			if(this.math.getOral() != -1) 		{fichierEcriture.write(Double.toString(this.math.getOral()));}
			else 								{fichierEcriture.write("X");}
			fichierEcriture.write("	|	");
			if(this.math.getEcrit() != -1) 		{fichierEcriture.write(Double.toString(this.math.getEcrit()));}
			else 								{fichierEcriture.write("X");}
			fichierEcriture.write("	|	");
			if(this.math.getMoyenne() != -1) 	{fichierEcriture.write(Double.toString(this.math.getMoyenne()));}
			else 								{fichierEcriture.write("X");}
			fichierEcriture.write("\n");

			fichierEcriture.write("Physique	|	1	|	");
			if(this.physique.getOral() != -1) 		{fichierEcriture.write(Double.toString(this.physique.getOral()));}
			else 									{fichierEcriture.write("X");}
			fichierEcriture.write("	|	");
			if(this.physique.getEcrit() != -1) 		{fichierEcriture.write(Double.toString(this.physique.getEcrit()));}
			else 									{fichierEcriture.write("X");}
			fichierEcriture.write("	|	");
			if(this.physique.getMoyenne() != -1) 	{fichierEcriture.write(Double.toString(this.physique.getMoyenne()));}
			else 									{fichierEcriture.write("X");}
			fichierEcriture.write("\n");
			
			fichierEcriture.write("Geo		|	1	|	");
			if(this.geo.getOral() != -1) 		{fichierEcriture.write(Double.toString(this.geo.getOral()));}
			else 								{fichierEcriture.write("X");}
			fichierEcriture.write("	|	");
			if(this.geo.getEcrit() != -1) 		{fichierEcriture.write(Double.toString(this.geo.getEcrit()));}
			else 								{fichierEcriture.write("X");}
			fichierEcriture.write("	|	");
			if(this.geo.getMoyenne() != -1) 	{fichierEcriture.write(Double.toString(this.geo.getMoyenne()));}
			else 								{fichierEcriture.write("X");}
			fichierEcriture.write("\n");
			
			fichierEcriture.write("Francais	|	1	|	");
			if(this.francais.getOral() != -1) 		{fichierEcriture.write(Double.toString(this.francais.getOral()));}
			else 									{fichierEcriture.write("X");}
			fichierEcriture.write("	|	");
			if(this.francais.getEcrit() != -1) 		{fichierEcriture.write(Double.toString(this.francais.getEcrit()));}
			else 									{fichierEcriture.write("X");}
			fichierEcriture.write("	|	");
			if(this.francais.getMoyenne() != -1) 	{fichierEcriture.write(Double.toString(this.francais.getMoyenne()));}
			else 									{fichierEcriture.write("X");}
			fichierEcriture.write("\n");
			
			fichierEcriture.write("Anglais		|	1	|	");
			if(this.anglais.getOral() != -1) 		{fichierEcriture.write(Double.toString(this.anglais.getOral()));}
			else 								{fichierEcriture.write("X");}
			fichierEcriture.write("	|	");
			if(this.anglais.getEcrit() != -1) 		{fichierEcriture.write(Double.toString(this.anglais.getEcrit()));}
			else 								{fichierEcriture.write("X");}
			fichierEcriture.write("	|	");
			if(this.anglais.getMoyenne() != -1) 	{fichierEcriture.write(Double.toString(this.anglais.getMoyenne()));}
			else 								{fichierEcriture.write("X");}

			fichierEcriture.close(); 
		} 
		catch (Exception e) {}	
	}

	public void affichageFichier() {
		
		try{
			File fichier = new File("/Users/jean-loup/Desktop/Java/APP4/bulletin_" + this.nom + "_" + this.prenom + ".txt"); 
			FileReader fichierLecture = new FileReader(fichier);
			BufferedReader br = new BufferedReader(fichierLecture);
			
			for	(String line; (line = br.readLine()) != null;) {
				System.out.println(line);
			}
			
			fichierLecture.close(); 
		} 
		catch (Exception e) {}	
	}
}
