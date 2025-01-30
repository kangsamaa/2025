package com.kang.nenpi.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PythonService {
   
    @Value("${python.script.path}")
    private String scriptPath;

    public String runPythonScript(String name) {
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
