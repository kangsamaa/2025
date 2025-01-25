package com.kang.nenpi.controller;

import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PythonController {
   
    @Value("${python.script.path}")
    private String scriptPath;

    @GetMapping("/run-python")
    public String runPythonScript(@RequestParam(value = "name", defaultValue = "TestWorld") String name) {
        try{
            ProcessBuilder processBuilder = new ProcessBuilder("python", scriptPath, name);

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while((line = reader.readLine())!= null){
                output.append(line).append("\n");

            }

            int exitCode = process.waitFor();
            if(exitCode==0){
                return "Python script executed suceesfully:<br> " +  output.toString().replace("\n", "<br>");
            }else{
                System.out.println("script path : " + scriptPath);
                return "Python script failed to execute";
            }
        } catch(Exception e){
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }
    
}
