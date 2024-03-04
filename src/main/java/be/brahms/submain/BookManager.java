package be.brahms.submain;

import be.brahms.Main;

import java.util.Scanner;

public class BookManager {

    public static void books() {

        // Declare variables
        Scanner scan = new Scanner(System.in);
        int choice;
        boolean stopMethode = false;

        do {

            // This is the main for my application
            System.out.println("""
                     Entrez le numéro que vous souhaitez y aller\s
                    1- Ajouter un livre\s
                    2- Modifier un livre\s
                    3- Supprimer un livre\s
                    4- Chercher un livre via son titre\s
                    5- Chercher un livre via son auteur\s
                    6- Chercher un livre via son ISBN\s
                    0- Pour revenir en arrière""");
            choice = Integer.parseInt( scan.nextLine() );

            switch(choice) {
                case 1 -> {}
                case 2 -> {}
                case 3 -> {}
                case 4 -> {}
                case 5 -> {}
                case 6 -> {}
                case 0 -> {
                    System.out.println( "Vous serez redirigé vers le menu principal" );
                    stopMethode = true;
                    Main.appLibrary();
                }
                default -> System.out.println(" Veuillez choisir le numéro entre 1 à 6 ou le 0 pour quitter");
            }

        } while( !stopMethode );

    }

}
