package com.kang.nenpi.controller;

import com.kang.nenpi.service.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/python")
public class PythonController {

    private final PythonService pythonService;

    // 생성자 주입을 통해 PythonService를 주입받습니다.
    @Autowired
    public PythonController(PythonService pythonService) {
        this.pythonService = pythonService;
    }

    //python test run
    @GetMapping("/run")
    public String executePythonScript(@RequestParam(value = "name", defaultValue="hello world") String name) {
        return pythonService.runPythonScript(name);
    }
}