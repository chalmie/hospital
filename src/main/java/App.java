import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/doctors/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/doctor-form.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/doctors/:id/patients/new", (request, response) -> {
      //identify doctor on page with model.put for $doctor
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("doctor", Doctor.find(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/patient-form.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/doctors", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String doctorName = request.queryParams("doctorname");
      Doctor newDoctor = new Doctor(doctorName);
      newDoctor.save();
      model.put("template", "templates/doctors.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/doctors", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("doctors", Doctor.all());
      model.put("template", "templates/doctors.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/doctors/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("doctor", Doctor.find(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/doctor-info.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/doctors/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("patientname");
      String birthday = request.queryParams("birthday");
      Doctor doctor = Doctor.find(Integer.parseInt(request.queryParams("doctor_id")));
      Patient newPatient = new Patient(name, birthday, doctor.getId());
      newPatient.save();
      model.put("doctor", doctor);
      model.put("patient", newPatient);
      model.put("template", "templates/patient-form.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());
  }
}
