import com.bonitasoft.ici.xesimporter.model.Attribute;
import com.bonitasoft.ici.xesimporter.model.Event;
import com.bonitasoft.ici.xesimporter.model.LogFile;
import com.bonitasoft.ici.xesimporter.model.Trace;
import org.deckfour.xes.in.XesXmlParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParseXML {

    public static void main(String argv[]) {


        try {

            File fXmlFile = new File("/home/bonitasoft/Téléchargements/XES certification import logs/Artificial/LevelD1.xes");
            new ParseXML().parseFile(fXmlFile);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }





    public LogFile parseFile(File fXmlFile) throws ParserConfigurationException, SAXException, IOException {
        FileInputStream inputStream = new FileInputStream(fXmlFile);


        return parse(inputStream);
    }

    public LogFile parse(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document xesFile = dBuilder.parse(inputStream);

        System.out.println("Root element :" + xesFile.getDocumentElement().getNodeName());
        NodeList traceNodes = xesFile.getElementsByTagName("trace");
        List<Trace> traces = toTraces(traceNodes);
        return new LogFile(traces);
    }

    private List<Trace> toTraces(NodeList tracesNodes) {
        ArrayList <Trace> traces = new ArrayList <>();
        for (int j = 0; j < tracesNodes.getLength(); j++) {
            Node traceNode = tracesNodes.item(j);
            System.out.println("trace" + j);
            traces.add(toTrace(traceNode));
        }
        return traces;
    }

    private Trace toTrace(Node traceNode) {
        NodeList eventsList = traceNode.getChildNodes();
        List <Event> events = toEvents(eventsList);
        return new Trace(events);
    }

    private List<Event> toEvents(NodeList eventsList) {
        ArrayList <Event> events = new ArrayList <>();
        for (int i = 0; i < eventsList.getLength(); i++) {
            Node event = eventsList.item(i);
            if (event.getNodeName().equals("event")) {
                System.out.println("event" + i);
                System.out.println();
                events.add(toEvent(event));
            }
        }
        return events;
    }


    private Event toEvent(Node event) {
        NodeList taskAttributes = event.getChildNodes();
        List<Attribute> attributes = toAttributes(taskAttributes);
        return new Event(attributes);
    }

    private List<Attribute> toAttributes(NodeList task) {
        ArrayList <Attribute> attributes = new ArrayList <>();
        for (int j = 0; j < task.getLength(); j++) {
            Node attribute = task.item(j);
            if (attribute.getNodeName().equals("string") || attribute.getNodeName().equals("date")) {
                attributes.add(toAttribute());
            }
        }
        return attributes;
    }

    private Attribute toAttribute() {
        return new Attribute();
    }




}


