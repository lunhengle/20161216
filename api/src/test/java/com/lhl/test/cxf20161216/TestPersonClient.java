package com.lhl.test.cxf20161216;


import com.lhl.api20161216.cxf.vo.Person;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by lunhengle on 2016/12/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/rs/cxf/applicationContext-rs-client.xml")
public class TestPersonClient {
    private static String baseAddress = "http://localhost:8080/webservice";
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(TestPersonClient.class);

    @Test
    public void testPersonById() {
        Person person = WebClient.create(baseAddress).path("/person/id1").accept(MediaType.APPLICATION_XML).get(Person.class);
        logger.info("id = " + person.getId() + " name = " + person.getName() + " description = " + person.getDescription());
    }

    @Test
    public void testPerson() {
        List<Person> persons = WebClient.create(baseAddress).path("/person").accept(MediaType.APPLICATION_XML).get(new GenericType<List<Person>>() {
        });
        for (Person person : persons) {
            logger.info("id = " + person.getId() + " name = " + person.getName() + " description = " + person.getDescription());
        }
    }

    @Test
    public void testPersonClient() throws IOException {
        String rsUrl = "http://localhost:8080/webservice/person/id0";
        URL url = new URL(rsUrl);
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection connection = (HttpURLConnection) urlConnection;
        connection.setRequestMethod("GET");
        int code = connection.getResponseCode();
        if (200 == code) {
            InputStream in = connection.getInputStream();
            byte[] b = new byte[1024];
            int len = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((len = in.read(b)) != -1) {
                String s = new String(b, 0, len, "UTF-8");
                stringBuffer.append(s);
            }
            String json = stringBuffer.toString();
            logger.info(json);
        }
    }
}
