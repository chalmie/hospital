import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class DoctorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Doctor.all().size(), 0);
  }

  @Test
  public void getName_returnsNameOfDoctor() {
    Doctor newDoctor = new Doctor("Frankenstein");
    assertEquals(newDoctor.getName(), "Frankenstein");
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Doctor firstDoctor = new Doctor("Household chores");
    Doctor secondDoctor = new Doctor("Household chores");
    assertTrue(firstDoctor.equals(secondDoctor));
  }

}
