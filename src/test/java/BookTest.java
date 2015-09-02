import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Book.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Book firstBook = new Book("Math");
    Book secondBook = new Book("Math");
    assertTrue(firstBook.equals(secondBook));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Book myBook = new Book("Math");
    myBook.save();

    Book savedBook = Book.all().get(0);
    assertTrue(savedBook.equals(myBook));
  }

  @Test
  public void save_assignsIdToObject() {
    Book myBook = new Book("Math");
    myBook.save();
    Book savedBook = Book.all().get(0);
    assertEquals(myBook.getId(), savedBook.getId());
  }

  @Test
  public void find_findsBookInDatabase_true() {
    Book myBook = new Book("Mow the lawn");
    myBook.save();
    Book savedBook = Book.find(myBook.getId());
    assertTrue(myBook.equals(savedBook));
  }

  @Test
  public void updateBookTitle() {
    Book myBook = new Book("Mow the lawn");
    myBook.save();
    myBook.update("science");
    assertTrue(Book.all().get(0).getTitle().equals("science"));
  }

  @Test
  public void deleteBook() {
    Book myFirstBook = new Book("Biology");
    myFirstBook.save();
    Book mySecondBook = new Book("Maths");
    mySecondBook.save();
    mySecondBook.delete();
    assertTrue(Book.all().get(0).equals(myFirstBook));
  }

  @Test
  public void getAuthor_returnsAllAuthors_List() {
    Book myBook = new Book("math");
    myBook.save();

    Author myAuthor = new Author("You");
    myAuthor.save();

    myBook.addAuthor(myAuthor);
    List savedAuthors = myBook.getAuthors();
    assertEquals(savedAuthors.size(), 1);

  }


}
