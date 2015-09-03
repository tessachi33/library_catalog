import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class PatronTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Patron.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Patron firstPatron = new Patron("Teresa", "555");
    Patron secondPatron = new Patron("Teresa", "555");
    assertTrue(firstPatron.equals(secondPatron));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Patron myPatron = new Patron("Teresa", "555");
    myPatron.save();

    Patron savedPatron = Patron.all().get(0);
    assertTrue(savedPatron.equals(myPatron));
  }

  @Test
  public void save_assignsIdToObject() {
    Patron myPatron = new Patron("Teresa", "555");
    myPatron.save();
    Patron savedPatron = Patron.all().get(0);
    assertEquals(myPatron.getId(), savedPatron.getId());
  }

  @Test
  public void find_findsPatronInDatabase_true() {
    Patron myPatron = new Patron("Rick", "777");
    myPatron.save();
    Patron savedPatron = Patron.find(myPatron.getId());
    assertTrue(myPatron.equals(savedPatron));
  }

  @Test
  public void updatePatronName() {
    Patron myPatron = new Patron("Rick", "777");
    myPatron.save();
    myPatron.update("John");
    assertTrue(Patron.all().get(0).getName().equals("John"));
  }

  @Test
  public void deletePatron() {
    Patron myFirstPatron = new Patron("Steven", "999");
    myFirstPatron.save();
    Patron mySecondPatron = new Patron("Teresa", "555");
    mySecondPatron.save();
    mySecondPatron.delete();
    assertTrue(Patron.all().get(0).equals(myFirstPatron));
  }

  @Test
public void getCopies_returnsCheckedOutCopies() {
  Patron instance = new Patron("Seymour Buttz");
  instance.save();
  Copy newCopy = new Copy(1);
  newCopy.save();
  Copy savedCopy = Copy.find(newCopy.getId());
  savedCopy.checkouts(instance);
  assertTrue(instance.getCopies().contains(savedCopy));
}


}
