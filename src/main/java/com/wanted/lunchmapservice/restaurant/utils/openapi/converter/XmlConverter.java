package com.wanted.lunchmapservice.restaurant.utils.openapi.converter;

import com.wanted.lunchmapservice.restaurant.utils.openapi.OpenApiDto;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import org.springframework.stereotype.Component;

@Component
public class XmlConverter {

    public OpenApiDto convert(String xmlData) {
        try {
            JAXBContext context = JAXBContext.newInstance(OpenApiDto.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (OpenApiDto) unmarshaller.unmarshal(new StringReader(xmlData));
        } catch (JAXBException e) {
            throw new RuntimeException(); //TODO: 예외처리
        }
    }
}
