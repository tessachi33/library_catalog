import org.junit.*;
import static org.junit.Assert.*;

public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Book.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Book firstBook = new Book("Math", "Teresa", 45);
    Book secondBook = new Book("Math", "Teresa", 45);
    assertTrue(firstBook.equals(secondBook));
  }
  //
  // @Test
  // public void save_savesObjectIntoDatabase() {
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   Task savedTask = Task.all().get(0);
  //   assertTrue(savedTask.equals(myTask));
  // }
  //
  // @Test
  // public void save_assignsIdToObject() {
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   Task savedTask = Task.all().get(0);
  //   assertEquals(myTask.getId(), savedTask.getId());
  // }
  //
  // @Test
  // public void find_findsTaskInDatabase_true() {
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   Task savedTask = Task.find(myTask.getId());
  //   assertTrue(myTask.equals(savedTask));
  // }
}
