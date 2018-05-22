import com.bonitasoft.ici.xesimporter.model.Event;
import com.bonitasoft.ici.xesimporter.model.LogFile;
import com.bonitasoft.ici.xesimporter.model.Trace;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class ParseXMLTest {

    @Test
    public void should_read_traces_from_xml_file() throws Exception {
        //given
        byte[] bytes = IOUtils.resourceToByteArray("/LevelD1.xes");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        //when
        LogFile logFile = new ParseXML().parse(byteArrayInputStream);

        //then
        Assertions.assertThat(logFile.getTraces()).hasSize(3);

    }


    @Test
    public void should_read_events_from_xml_file() throws Exception {
        //given
        byte[] bytes = IOUtils.resourceToByteArray("/LevelD1.xes");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        //when
        LogFile logFile = new ParseXML().parse(byteArrayInputStream);

        //then
        Trace trace = logFile.getTraces().get(0);
        Assertions.assertThat(trace.getEvents()).hasSize(9);
    }


    @Test
    public void should_read_attribute_from_xml_file() throws Exception {
        //given
        byte[] bytes = IOUtils.resourceToByteArray("/LevelD1.xes");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        //when
        LogFile logFile = new ParseXML().parse(byteArrayInputStream);

        //then
        Trace trace = logFile.getTraces().get(0);
        Event event = trace.getEvents().get(0);
        Assertions.assertThat(event.getAttributes()).hasSize(6);
    }

}