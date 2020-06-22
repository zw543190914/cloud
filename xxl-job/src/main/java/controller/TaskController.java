package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xxl-job/task")
public class TaskController {

    public String test(){
        return "sss";
    }
}
