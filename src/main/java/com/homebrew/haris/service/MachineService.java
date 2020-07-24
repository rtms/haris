package com.homebrew.haris.service;

import com.homebrew.haris.model.Machine;
import com.homebrew.haris.model.MachineDTO;
import com.homebrew.haris.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MachineService {

    @Autowired
    MachineRepository repository;


    public void saveMachine(MachineDTO machineDTO) {
        Machine machine = machineDTO.toMachine();

        // saving a new machine
        if (StringUtils.isEmpty(machineDTO.getId())) {
            machine.setCreatedAt(LocalDateTime.now());
            machine.setUpdatedAt(LocalDateTime.now());
        }

        repository.save(machine);
    }

    public void updateMachine(MachineDTO machineDTO) {
        if (repository.existsById(machineDTO.getId())) {
            repository.save(machineDTO.toMachine());
        }
    }

    public MachineDTO getMachineById(String id) {
        Machine machine = repository.findById(id).get();
        return new MachineDTO(machine);
    }

    public List<MachineDTO> getAllMachinesSorted() {
        return sortByOrder(repository.findAll()).stream()
                .filter(Machine::isActive)
                .map(machine -> new MachineDTO(machine))
                .collect(Collectors.toList());
    }

    public void deleteMachine(String id) {
        if (repository.existsById(id)) {
            Machine machine = repository.findById(id).get();
            machine.setActive(false);
            repository.save(machine);
        }
    }

    public List<Machine> sortByOrder(Iterable<Machine> machines) {
        return StreamSupport.stream(machines.spliterator(), false)
                .sorted(Comparator.comparing(Machine::getUpdatedAt).reversed())
                .collect(Collectors.toList());
    }

}
