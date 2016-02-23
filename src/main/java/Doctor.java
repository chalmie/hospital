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



}
