package com.bonitasoft.ici.xesimporter.model;

import java.util.List;


public class Event {

    private static List <Attribute> attributes;

    public Event(List <Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}
