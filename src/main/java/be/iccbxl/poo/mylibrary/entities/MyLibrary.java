package be.iccbxl.poo.mylibrary.entities;

import java.util.ArrayList;
import java.util.Iterator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyLibrary {
    private String name;

    /**
	 * Liste des livres
	 */
	private ArrayList<Book> books = new ArrayList<>();
	
	/**
	 * Liste des membres
	 */
	private ArrayList<Person> people = new ArrayList<>();

    private final static byte BOOK_LIMIT = 3;

    public ArrayList<Book> findBooksByTitle(String title) {
        //TODO
        return null;
    }

    public void addBook(Book book) {
		this.books.add(book);
	}

	public void addPerson(Person person) {
		this.people.add(person);
	}
	
	public void printBooks() {
		Iterator<Book> it = this.getBooks().iterator();
		
		while(it.hasNext()) {
			Book b = it.next();						
			System.out.println(b.getTitle() + " - " + b.getAuthor());
		}
	}

	public void printMembers() {
		Iterator<Person> it = this.getPeople().iterator();
		
		while(it.hasNext()) {
			Person p = it.next();		
			System.out.println(p.getName() + ", inscrit le " + p.getRegistrationDate());
		}
	}

	public Person findMemberByName(String nomEmprunteur) {
		for (Person person : this.people) {
			if (person.getName().equalsIgnoreCase(nomEmprunteur)) {
				return person;
			}
		}
		return null; // Aucun membre trouvé
	}

}
