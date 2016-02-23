import java.util.List;
import org.sql2o.*;

public class Doctor {
  private int id;
  private String name;

public static List<Doctor> all(){
  String sql = "SELECT * FROM doctors";
  try(Connection con = DB.sql2o.open()){
    return con.createQuery(sql).executeAndFetch(Doctor.class);
  }
}

public Doctor(String name) {
  this.name = name;
}

public String getName() {
  return name;
}

@Override
public boolean equals(Object otherDoctor) {
  if (!(otherDoctor instanceof Doctor)) {
    return false;
  } else {
    Doctor newDoctor = (Doctor) otherDoctor;
    return this.getName().equals(newDoctor.getName());
  }
}



}
