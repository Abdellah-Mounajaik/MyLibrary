package be.iccbxl.poo.mylibrary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.UUID;

import be.iccbxl.poo.mylibrary.entities.Book;
import be.iccbxl.poo.mylibrary.entities.MyLibrary;
import be.iccbxl.poo.mylibrary.entities.Person;
import be.iccbxl.poo.mylibrary.entities.RangeException;

public class Main {
    public static void main(String[] args) {
        /*
         * 76.	//Affichage du menu
            77.	écrire "Menu principal
                1 - Ajouter un membre
	            2 - Ajouter un livre
                3 - Emprunter un livre
                4 - Afficher les statistiques
                0 – Quitter
         */
        MyLibrary library = new MyLibrary();
        library.setName("Nid des lecteurs");
        
        Scanner scanner = new Scanner(System.in);
        int choix = -1;

        String menuPrincipal = "Menu principal\n"
            +"\t1 - Ajouter un membre\n"
	        +"\t2 - Ajouter un livre\n"
            +"\t3 - Emprunter un livre\n"
            +"\t4 - Afficher les statistiques\n"
            +"\t0 - Quitter";

        //Affichage du menu
        do {
            try {
                System.out.println(menuPrincipal);

                //Lecture du choix
                choix = scanner.nextInt();
                scanner.nextLine(); //Vider le tampon

                //Valider l'entrée utilisateur (entier compris entre 0 et 4)
                if(choix<0 || choix>4) {
                    throw new RangeException("Choix non valide :"+choix+" ([0,1,2,3,4])");
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre parmi ceux proposés.");
                scanner.nextLine(); //Vider le tampon
            } catch(RangeException e) {
                System.out.println("Veuillez entrer un nombre parmi ceux proposés.");
                scanner.nextLine(); //Vider le tampon
                choix = -1;
            }
        } while(choix==-1);

        //Traitement du choix
        switch (choix) {
            case 1:
                //Demander le nom
                System.out.println("Quel est le nom du nouveau membre ?");
                String nom = scanner.nextLine();

                //Créer une Person
                Map<LocalDate,ArrayList<Book>> loans = new TreeMap<>();
                Person p = new Person(UUID.randomUUID(),nom,LocalDate.now(),loans);
                
                //Ajouter à la library
                library.addPerson(p);
                break;
            
            case 2 :
                // Ajouter un livre
                System.out.println("Quel est le titre du livre ?");
                String title = scanner.nextLine();
                System.out.println("Quel est l'auteur du livre ?");
                String author = scanner.nextLine();
                System.out.println("Combien de pages contient le livre ?");
                short totalPages = scanner.nextShort(); 
                Book book1 = new Book(title,author,totalPages);
                library.addBook(book1);
                //TODO
            case 3 :
                // Demander le nom de l'emprunteur
                System.out.println("Veuillez entrer le nom du membre :");
                String nomEmprunteur = scanner.nextLine();

                // Rechercher le membre
                Person selectiondMembre = library.findMemberByName(nomEmprunteur);
                if (selectiondMembre == null) {
                    System.out.println("Aucun membre trouvé avec ce nom.");
                    break;
                }
                // Vérifier si le membre peut encore emprunter des livres
                if (selectiondMembre.getLoans().size() >= 3) {
                    System.out.println("Ce membre ne peut plus emprunter de livres (limite atteinte).");
                    break;
                }   
                // Demander le titre du livre
                System.out.println("Veuillez entrer le titre ou une partie du titre du livre :");
                String livreTitre = scanner.nextLine();
                // Rechercher les livres correspondants
                ArrayList<Book> livresTrouves = library.findBooksByTitle(livreTitre);
                if (livresTrouves.isEmpty()) {
                    System.out.println("Aucun livre trouvé avec ce titre.");
                    break;
                }
                // Afficher les livres correspondants
                System.out.println("Livres disponibles :");
                for (int i = 0; i < livresTrouves.size(); i++) {
                    Book book = livresTrouves.get(i);
                    // String availability = book.isAvailable() ? "Disponible" : "Non disponible";
                    // System.out.println(i + " - " + book.getTitle() + " par " + book.getAuthor() + " (" + availability + ")");
                }
                // Demander à l'utilisateur de sélectgit push -f origin masterionner un livre
                System.out.println("Veuillez sélectionner le numéro du livre à emprunter :");
                int selectionBookIndex = scanner.nextInt();
                scanner.nextLine(); // Vider le tampon

                if (selectionBookIndex < 0 || selectionBookIndex >= livresTrouves.size()) {
                    System.out.println("Sélection invalide.");
                    break;
                }
                Book selectBook = livresTrouves.get(selectionBookIndex);

                // Emprunter le livre
                // boolean success = library.borrowBook(selectiondMembre, selectBook);
                // if (success) {
                //     System.out.println("Le livre \"" + selectBook.getTitle() + "\" a été emprunté par " + selectedMember.getName() + ".");
                // } else {
                //     System.out.println("Impossible d'emprunter ce livre.");
                // }
                break;
        
            default:
                System.out.println("Fin du programme.");
                System.exit(0);
        }

        System.out.println(library);
    }
}