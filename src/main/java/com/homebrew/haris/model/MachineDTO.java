package com.homebrew.haris.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

public class MachineDTO {

    private String id;
    @Past(message = "{machine.createdAt.Past}")
    private LocalDateTime createdAt;
    @Past(message = "{machine.updatedAt.Past}")
    private LocalDateTime updatedAt;
    @NotBlank(message = "{machine.name.notBlank}")
    private String name;


    public MachineDTO() {}

    public MachineDTO(Machine machine) {
        this.id = machine.getId();
        this.createdAt = machine.getCreatedAt();
        this.updatedAt = machine.getUpdatedAt();
        this.name = machine.getName();
    }

    public Machine toMachine() {
        Machine machine = new Machine();
        machine.setId(this.getId());
        machine.setCreatedAt(this.createdAt);
        machine.setUpdatedAt(this.updatedAt);
        machine.setName(this.getName());

        return machine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
