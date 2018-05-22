package com.bonitasoft.ici.xesimporter.model;

import java.util.List;

public class LogFile {

    private List<Trace> traces;

    public LogFile(List <Trace> traces) {
        this.traces = traces;
    }

    public List <Trace> getTraces() {
        return traces;
    }
}
