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
    Doctor firstDoctor = new Doctor("Frankenstein");
    Doctor secondDoctor = new Doctor("Frankenstein");
    assertTrue(firstDoctor.equals(secondDoctor));
  }

  @Test
  public void save_savesIntoDatabase_true(){
    Doctor newDoctor = new Doctor("Frankenstein");
    newDoctor.save();
    assertTrue(Doctor.all().get(0).equals(newDoctor));
  }

  @Test
  public void find_findDoctorInDatabase_true() {
    Doctor newDoctor = new Doctor("Frankenstein");
    newDoctor.save();
    Doctor savedDoctor = Doctor.find(newDoctor.getId());
    assertTrue(newDoctor.equals(savedDoctor));
  }
}
