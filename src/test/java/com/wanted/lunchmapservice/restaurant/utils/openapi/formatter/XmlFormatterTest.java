package com.wanted.lunchmapservice.restaurant.utils.openapi.formatter;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Import(XmlFormatter.class)
@ExtendWith(SpringExtension.class)
class XmlFormatterTest {

    @Autowired
    XmlFormatter xmlFormatter;

    @DisplayName("xml 데이터 포맷터 테스트 : 성공")
    @Test
    void xmlFormatterTest() {
        //given
        String xmlData = """
            <?xml version="1.0" encoding="UTF-8"?>

            <Genrestrtcate>
                <head></head>
                <row></row>
            </Genrestrtcate>""";

        //when
        String formattedXmlData = xmlFormatter.format(xmlData);

        //then
        assertThat(formattedXmlData).isEqualTo("""
            <OpenApiDto>
                <head></head>
                <row></row>
            </OpenApiDto>""");
    }
}
