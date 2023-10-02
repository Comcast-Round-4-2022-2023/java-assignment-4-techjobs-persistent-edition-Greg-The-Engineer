package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository; // Inject SkillRepository

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "My Jobs");
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("job", new Job());

        // Load employer and skill data into the form
        List<Employer> employers = new ArrayList<>();
        List<Skill> skills = new ArrayList<>(); // Add skills
        employerRepository.findAll().forEach(employers::add);
        skillRepository.findAll().forEach(skills::add); // Load skills
        model.addAttribute("employers", employers);
        model.addAttribute("skills", skills); // Add skills to the model

        return "add";
    }


    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model,
                                    @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }

        // Select the employer object based on the provided employerId
        Employer selectedEmployer = employerRepository.findById(employerId).orElse(null);
        newJob.setEmployer(selectedEmployer);

        // Select the skill objects based on the provided skill ids
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);

        // Your logic for saving the new job...

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        // Your logic for displaying a job...

        return "view";
    }
}