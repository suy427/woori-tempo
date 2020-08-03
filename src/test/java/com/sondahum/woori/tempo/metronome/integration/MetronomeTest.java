package com.sondahum.woori.tempo.metronome.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sondahum.woori.tempo.WooriTempoApplication;
import com.sondahum.woori.tempo.metronome.TempoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = WooriTempoApplication.class)
@AutoConfigureMockMvc
public class MetronomeTest {

    @Autowired
    protected MockMvc mvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void countTest() throws Exception {
        TempoDto tempo = new TempoDto();
        tempo.bpm = 60;

        mvc.perform(
                post("/tempo/count")
                .content(objectMapper.writeValueAsString(tempo))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

}
