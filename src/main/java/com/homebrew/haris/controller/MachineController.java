package com.homebrew.haris.controller;

import com.homebrew.haris.model.MachineDTO;
import com.homebrew.haris.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/machines")
public class MachineController {

    @Autowired
    MachineService service;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementExceptions(NoSuchElementException ex) {
        ResourceBundle nls = ResourceBundle.getBundle("messages", new Locale("en"));
        return nls.getString("machine.NoSuchElement");
    }

    @GetMapping("/{id}")
    public MachineDTO getMachine(@PathVariable String id) {
        return service.getMachineById(id);
    }

    @PostMapping()
    public void saveMachine(@RequestBody MachineDTO machineDTO) {
        service.saveMachine(machineDTO);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PATCH)
    public void updateMachine(@RequestBody @Valid MachineDTO machineDTO) {
        service.updateMachine(machineDTO);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteMachine(@PathVariable String id) {
        service.deleteMachine(id);
    }

    @GetMapping()
    public List<MachineDTO> getAllMachines() {
        return service.getAllMachinesSorted();
    }

}
