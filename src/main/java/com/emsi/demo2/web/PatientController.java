package com.emsi.demo2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emsi.demo2.dao.PatientRepos;
import com.emsi.demo2.entities.Patient;

@Controller
public class PatientController {
	@Autowired
	private PatientRepos pr;
@GetMapping(path = "/index")
	public String index() {
		
		return "index";
	}

@GetMapping(path = "/patients")
	public String listPatients(Model model,
			@RequestParam(name="page",defaultValue ="0")int page,
			@RequestParam(name="size",defaultValue ="5")int size,
			@RequestParam(name="keyword",defaultValue ="")String kw) {
		Page<Patient> pagePatients = pr.findByNomContains(kw,PageRequest.of(page,size));
		model.addAttribute("patients",pagePatients.getContent());
		model.addAttribute("pages",new int [pagePatients.getTotalPages()]);
		model.addAttribute("currentPage",page);
		model.addAttribute("keyword",kw);
		model.addAttribute("size",size);
		
		return "patients";
	}
@GetMapping(path = "/deletePatient")
public String deletePatient(Long id,String keyword,int page,int size) {
	pr.deleteById(id);
	return "redirect:/patients?page="+page+"&size="+size+"&keyword="+keyword;
}
@GetMapping("/formPatient")
public String formPatient(Model model) {
	model.addAttribute("patient",new Patient());
	model.addAttribute("mode","new");
	return "formPatient";
}
@PostMapping("/savePatient")
public String savePatient(Model model,Patient patient) {
	pr.save(patient);
	return "redirect:/patients";
}
@GetMapping("/editPatient")
public String editPatient(Model model,Long id) {
	Patient p = pr.findById(id).get();
	model.addAttribute("patient",p);
	model.addAttribute("mode","edit");
	return "formPatient";
}
@GetMapping("/listpatients")
public List<Patient> patients(){
	return pr.findAll();
}
}
