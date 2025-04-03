package be.iccbxl.poo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import be.iccbxl.poo.mylibrary.entities.Book;
import be.iccbxl.poo.mylibrary.entities.Person;
import be.iccbxl.poo.mylibrary.entities.NotAvailableException;

public class PersonTest {

   @Test
   public void borrows() {
      Map<LocalDate, ArrayList<Book>> loans = new TreeMap();
      Person bob = new Person(UUID.randomUUID(), "Bob", LocalDate.of(2020, 10, 25), loans);
      Book book = new Book("Une vie", "Guy de Maupassant", (short)210);
      bob.borrows(book);
      ArrayList<Book> emprunts = (ArrayList)bob.getLoans().get(LocalDate.now());
      Assertions.assertEquals(1, emprunts.size());
   }

   @Test
   public void emprunterWithPlusDisponibleShouldFail() {
      Map<LocalDate, ArrayList<Book>> loans = new TreeMap();
      Person bob = new Person(UUID.randomUUID(), "Bob", LocalDate.of(2020, 10, 25), loans);
      Person lydia = new Person(UUID.randomUUID(), "Bob", LocalDate.of(2020, 10, 25), loans);
      Book book = new Book("Une vie", "Guy de Maupassant", (short)210);
      bob.borrows(book);
      Assertions.assertThrows(NotAvailableException.class, () -> {
         lydia.borrows(book);
      });
      ArrayList<Book> emprunts = (ArrayList)lydia.getLoans().get(LocalDate.now());
      Assertions.assertEquals(0, emprunts.size());
   }
   
}
