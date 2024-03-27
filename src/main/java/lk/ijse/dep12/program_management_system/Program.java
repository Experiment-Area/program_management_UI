package lk.ijse.dep12.program_management_system;

import java.util.ArrayList;

public class Program{

    private String id;
    private String name;
    private String shortCode;
    private String type;
    private ArrayList<String> audience;

    public Program(String id, String name, String shortCode, String type, ArrayList<String> audience) {
        this.id = id;
        this.name = name;
        this.shortCode = shortCode;
        this.type = type;
        this.audience = audience;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getAudience() {
        return audience;
    }

    public void setAudience(ArrayList<String> audience) {
        this.audience = audience;
    }
}
