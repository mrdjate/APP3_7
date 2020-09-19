package com.APP4;

import java.util.Scanner;

public class Matiere {

	// Champ(s) :
	private double ecrit;
	private double oral;

	
	
	// Constructeur(s) :
	public Matiere() {
		this.ecrit = -1;
		this.oral = -1;
	}
	
	
	
	// Getter(s) :
	public double getEcrit()	{return this.ecrit;}
	
	public double getOral()		{return this.oral;}
	
	public double getMoyenne() {
		if		(this.ecrit == -1 && this.oral == -1)	{return -1;}
		else if	(this.ecrit != -1 && this.oral == -1)	{return this.ecrit;}
		else if	(this.ecrit == -1 && this.oral != -1)	{return this.oral;}
		else											{return (0.7*this.ecrit + 0.3*this.oral) / (0.7 + 0.3);}
	}
	
	
	
	// Methode(s) :
	public void modifier() {
		int choix;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1 : ecrit");
		System.out.println("2 : oral");
		System.out.print("Votre choix : ");	choix = sc.nextInt();

		switch (choix) {
		
		case 1:
			System.out.print("Entrez la note : ");
			this.ecrit = sc.nextDouble();
			break;

		case 2:
			System.out.print("Entrez la note : ");
			this.oral = sc.nextDouble();
			break;
			
		default:
			System.out.println("choix non valide");
			break;
		}
	}
}
