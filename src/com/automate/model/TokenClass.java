package com.automate.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TokenClass {
    private String name;

    private FiniteStateAutomate automate;

    private int priority;

    public TokenClass() { }

    public TokenClass(String name, FiniteStateAutomate automate, int priority)
    {
        this.name = name;
        this.automate = automate;
        this.priority = priority;
    }

    public TokenClass(String path)
    {
        try {
            List<String> temp = Files.readAllLines(Paths.get(path));
            this.name = temp.get(0);
            this.automate = new FiniteStateAutomate(temp.get(1));
            this.priority = Integer.parseInt(temp.get(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FiniteStateAutomate getAutomate() {
        return automate;
    }

    public void setAutomate(FiniteStateAutomate automate) {
        this.automate = automate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

