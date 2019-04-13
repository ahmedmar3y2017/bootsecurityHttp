package rc.bootsecurity.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rc.bootsecurity.model.Task;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    private static List<Task> tasks = new ArrayList<>();

    static {
        tasks.add(new Task("task 1 ", "Desc 1 "));
        tasks.add(new Task("Task 2 ", "DEsc 2 "));
    }

    // Inject via application.properties
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("message", message);
        return "admin/index";
    }

    @RequestMapping(value = { "/taskList" }, method = RequestMethod.GET)
    public String personList(Model model) {

        // get from database
        model.addAttribute("tasks", tasks);

        return "admin/taskList";
    }

    @RequestMapping(value = { "/addTask" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        Task task = new Task();
        model.addAttribute("task", task);

        return "admin/addTask";
    }

    @RequestMapping(value = { "/addTask" }, method = RequestMethod.POST)
    public ModelAndView savePerson(@Valid  //
                             Task task ,BindingResult result) {
        ModelAndView mav = new ModelAndView();

        String taskName = task.getTaskName();
        String taskDesc = task.getTaskDesc();

        if(!result.hasErrors()) {
            Task task1 = new Task(taskName, taskDesc);
            // added To List
            tasks.add(task1);

            mav.setViewName("redirect:/admin/taskList");
            return mav;
        }

        mav.addObject("errorMessage", errorMessage);

        mav.setViewName("admin/addTask");
        return mav;
    }

}
