import org.junit.Rule;
import org.junit.*;
import static org.junit.Assert.*;

public class PatientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst(){
    assertEquals(Patient.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame(){
    Patient firstPatient = new Patient("Quasimodo", 1, "12-26-91");
    Patient secondPatient = new Patient("Quasimodo", 1, "12-26-91");
    assertTrue(firstPatient.equals(secondPatient));
  }

  @Test
  public void save_returnsListOfPatients() {
    Patient newPatient = new Patient("Quasimodo", 1, "12-26-91");
    newPatient.save();
    assertTrue(Patient.all().get(0).equals(newPatient));
  }

  @Test
  public void save_assignsIdToObject() {
    Patient newPatient = new Patient("Quasimodo", 1, "12-26-91");
    newPatient.save();
    Patient savedPatient = Patient.all().get(0);
    assertEquals(newPatient.getId(), savedPatient.getId());
  }

  @Test
  public void find_findsPatientinDatabase_true() {
    Patient newPatient = new Patient("Quasimodo", 1, "12-26-91");
    newPatient.save();
    Patient savedPatient = Patient.find(newPatient.getId());
    assertTrue(newPatient.equals(savedPatient));
  }

  @Test
  public void save_savesDoctorIdIntoDB_true() {
    Doctor newDoctor = new Doctor("Frankenstein");
    newDoctor.save();
    Patient newPatient = new Patient("Quasimodo", newDoctor.getId(), "12-26-91");
    newPatient.save();
    Patient savedPatient = Patient.find(newPatient.getId());
    assertEquals(savedPatient.getDoctor_id(), newDoctor.getId());
  }
}
