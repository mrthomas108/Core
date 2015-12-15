/*
 * DAPNET CORE PROJECT
 * Copyright (C) 2015
 *
 * Daniel Sialkowski
 *
 * daniel.sialkowski@rwth-aachen.de
 *
 * Institut für Hochfrequenztechnik
 * RWTH AACHEN UNIVERSITY
 * Melatener Str. 25
 * 52074 Aachen
 */

package org.dapnet.core.model;

import org.dapnet.core.model.list.Searchable;
import org.dapnet.core.rest.RestAuthorizable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rubric implements Serializable, RestAuthorizable, Searchable {
    //ID
    @NotNull(message = "nicht vorhanden")
    @Size(min = 3, max = 20, message = "muss zwischen {min} und {max} Zeichen lang sein")
    private String name;

    @NotNull(message = "nicht vorhanden")
    @Min(value = 1, message = "muss zwischen 1 und 95 liegen")
    @Max(value = 95, message = "muss zwischen 1 und 95 liegen")
    private int number;

    @NotNull(message = "nicht vorhanden")
    @Size(min = 1, message = "müssen mindestens einen transmitterGroupNamen enthalten")
    private List<String> transmitterGroupNames;

    @NotNull(message = "nicht vorhanden")
    @Size(min = 1, max = 11, message = "muss zwischen {min} und {max} Zeichen lang sein")
    private String label;

    @NotNull(message = "nicht vorhanden")
    @Size(min = 1, message = "müssen mindestens einen ownerNamen enthalten")
    private ArrayList<String> ownerNames;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getTransmitterGroupNames() {
        return transmitterGroupNames;
    }

    public void setTransmitterGroupNames(List<String> transmitterGroupNames) {
        this.transmitterGroupNames = transmitterGroupNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<String> getOwnerNames() {
        return ownerNames;
    }

    public void setOwnerNames(ArrayList<String> owners) {
        this.ownerNames = owners;
    }

    //Getter returning references instead of String
    private static State state;

    public static void setState(State statePar) {
        state = statePar;
    }

    @NotNull(message = "müssen existieren")
    @Size(min = 1, message = "müssen existieren")
    public ArrayList<TransmitterGroup> getTransmitterGroups() throws Exception {
        if (state == null)
            throw new Exception("StateNotSetException");
        ArrayList<TransmitterGroup> transmitterGroups = new ArrayList<>();
        if (transmitterGroupNames == null)
            return null;
        for (String transmitterGroup : transmitterGroupNames) {
            if (state.getTransmitterGroups().contains(transmitterGroup))
                transmitterGroups.add(state.getTransmitterGroups().findByName(transmitterGroup));
        }
        if (transmitterGroups.size() == transmitterGroups.size())
            return transmitterGroups;
        else
            return null;
    }

    @NotNull(message = "müssen existieren")
    @Size(min = 1, message = "müssen existieren")
    public ArrayList<User> getOwners() throws Exception {
        if (state == null)
            throw new Exception("StateNotSetException");
        ArrayList<User> users = new ArrayList<>();
        if (ownerNames == null)
            return null;
        for (String owner : ownerNames) {
            if (state.getUsers().contains(owner))
                users.add(state.getUsers().findByName(owner));
        }
        if (ownerNames.size() == users.size())
            return users;
        else
            return null;
    }

    @Override
    public String toString() {
        return "Rubric{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
