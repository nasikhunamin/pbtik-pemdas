package com.devgrafis.www.grafis.model;

import java.util.ArrayList;
import java.util.List;

public class value {
    String value;
    String message;

    ArrayList<Material> resultMaterial;
    ArrayList<Group> resultGroup;


    public String getValue() {
            return value;
        }

    public String getMessage() {
            return message;
        }

    public ArrayList<Material> getMaterial() { return resultMaterial; }

    public ArrayList<Group> getResultGroup() { return resultGroup; }
}
