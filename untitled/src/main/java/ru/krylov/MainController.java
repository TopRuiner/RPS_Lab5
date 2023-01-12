package ru.krylov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.krylov.beans.Patient;
import ru.krylov.beans.Polis;
import ru.krylov.dao.DAO;
import ru.krylov.service.ServiceClass;
import ru.krylov.service.ServletServiceClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    ServletServiceClass servletServiceClass;
    private static ApplicationContext context = new ClassPathXmlApplicationContext("daos.xml");

    /*
    @GetMapping("/cPatient")
    public String createPatient(Model model) {
        servletServiceClass = context.getBean("ServletServiceDAO", ServletServiceClass.class);
        model.addAttribute("message", "Patient created\n Index of created patient: ");
        Patient patient = new Patient();
        patient.setName("Servlet");
        patient.setSnilsNum(120);
        patient.setPolisId(120);
        Integer createdIndex = null;
        try {
            createdIndex = servletServiceClass.createPatient(patient);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("createdIndex",createdIndex);
        return "cPatient";
    }
     */
    @GetMapping("/patient")
    public String patientForm(Model model){
        model.addAttribute("patient", new Patient());
        return "patient";
    }
    @PostMapping("/patient")
    public String patientSubmit(@ModelAttribute Patient patient, Model model) throws IOException {
        model.addAttribute("patient",patient);
        servletServiceClass = context.getBean("ServletServiceDAO", ServletServiceClass.class);
        Integer createdIndex = null;
        createdIndex = servletServiceClass.createPatient(patient);
        model.addAttribute("createdIndex",createdIndex);
        return "patients";
    }
    @RequestMapping("/rPatient/{patientId}")
    public String readPatient(@PathVariable Integer patientId, Model model) throws IOException {
        servletServiceClass = context.getBean("ServletServiceDAO", ServletServiceClass.class);
        Patient patient = new Patient();
        patient = servletServiceClass.findPatient(patientId);
        model.addAttribute(patient);
        return "rvPatient";
    }
    @RequestMapping("/dPatient/{patientId}")
    public String deletePatient(@PathVariable Integer patientId, Model model) throws IOException {
        servletServiceClass = context.getBean("ServletServiceDAO", ServletServiceClass.class);
        servletServiceClass.deletePatient(patientId);
        model.addAttribute(patientId);
        return "dvPatient";
    }
    /*
    @GetMapping("/ListPatients")
    public String listPatientForm(Model model){
        List<Patient> patients = new ArrayList<>();
        model.addAttribute("patients",patients);
        return "vListPatients";
    }
    @RequestMapping("/vListPatients")
    public String getAllPatients(@ModelAttribute("patients")List<Patient> patients, Model model){
        return "vListPatients";

    }
     */
    @GetMapping("/vListPatients")
    public String listPatientForm(Model model){
        servletServiceClass = context.getBean("ServletServiceDAO", ServletServiceClass.class);
        List<Patient> patients;
        patients = new ArrayList<>();
        patients = servletServiceClass.getAllPatients();
        model.addAttribute("patients",patients);
        return "vListPatients";
    }
    @GetMapping("/main")
    public String patientMain(Model model){
        model.addAttribute("id", new Patient());
        return "main";
    }
    @PostMapping("/mainDelete")
    public String patientDeleteById(@ModelAttribute Patient patient, Model model) throws IOException {
        model.addAttribute("id",patient.getId());
        servletServiceClass = context.getBean("ServletServiceDAO", ServletServiceClass.class);
        Integer getInteger = patient.getId();
        servletServiceClass.deletePatient(getInteger);
        return "/mainDelete";
    }
    @PostMapping("/mainView")
    public String patientViewById(@ModelAttribute Patient patient, Model model) throws IOException {
        //model.addAttribute("id",patient.getId());
        servletServiceClass = context.getBean("ServletServiceDAO", ServletServiceClass.class);
        Integer getInteger = patient.getId();
        servletServiceClass.findPatient(getInteger);
        Patient patientNew = new Patient();
        patientNew = servletServiceClass.findPatient(getInteger);
        model.addAttribute(patientNew);
        return "rvPatient";
    }

}
