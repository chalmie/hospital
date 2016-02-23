import java.util.List;
import org.sql2o.*;

public class Patient {
  private String name;
  private String birthday;
  private int doctor_id;
  private int id;

  public Patient (String name, String birthday, int doctor_id) {
    this.name = name;
    this.doctor_id = doctor_id;
    this.birthday = birthday;
  }

  public String getBirthday(){
    return birthday;
  }

  public int getDoctor_id(){
    return doctor_id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object otherPatient){
    if(!(otherPatient instanceof Patient)){
      return false;
    } else {
      Patient newPatient = (Patient) otherPatient;
      return this.getName().equals(newPatient.getName()) &&
        this.getId() == newPatient.getId() &&
        this.getDoctor_id() == newPatient.getDoctor_id();
    }
  }

  public static List<Patient> all() {
    String sql = "SELECT id, name, birthday, doctor_id FROM patients";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Patient.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients(name, birthday, doctor_id) VALUES (:name, :birthday, :doctor_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("doctor_id", this.doctor_id)
        .addParameter("birthday", this.birthday)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patient find(int id){
    try(Connection con = DB.sql2o.open()){
    String sql = "SELECT * FROM Patients where id=:id";
    Patient patient = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Patient.class);
    return patient;
    }
  }
}
