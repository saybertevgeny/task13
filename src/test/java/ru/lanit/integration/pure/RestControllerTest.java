package ru.lanit.integration.pure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.lanit.dto.Car;
import ru.lanit.dto.Person;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void correctSave() throws Exception{
        Person requestPerson = new Person(1, "TestUser", "01.09.1991");
        mockMvc.perform(post("/person").content(mapper.writeValueAsString(requestPerson)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Car carRequest = new Car(1,200,"BMX-X5",1);
        mockMvc.perform(post("/car").content(mapper.writeValueAsString(carRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        carRequest = new Car(2,200,"BMX-X5-M",1);
        mockMvc.perform(post("/car").content(mapper.writeValueAsString(carRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/personwithcars").param("personid", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cars[0].id").value(1))
                .andExpect(jsonPath("$.cars[1].id").value(2));
    }

    @Test
    public void unCorrectSaveCarTooYoungPerson() throws Exception{
        Person requestPerson = new Person(2, "TestUser", "01.09."+ LocalDate.now().minusYears(10).getYear());
        mockMvc.perform(post("/person").content(mapper.writeValueAsString(requestPerson)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        Car carRequest = new Car(3,200,"BMX-X5",2);

        mockMvc.perform(post("/car").content(mapper.writeValueAsString(carRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/personwithcars").param("personid", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.cars").isEmpty());
    }

    @Test
    public void unCorrectPersonTooYoung() throws Exception{
        Person requestPerson = new Person(3, "TestUser", "01.09."+ LocalDate.now().plusYears(1).getYear());
        mockMvc.perform(post("/person").content(mapper.writeValueAsString(requestPerson)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/personwithcars").param("personid", "3"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void unCorrectSaveCarModel() throws Exception{
        Person requestPerson = new Person(4, "TestUser", "01.09."+ LocalDate.now().minusYears(19).getYear());
        mockMvc.perform(post("/person").content(mapper.writeValueAsString(requestPerson)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Car carRequest = new Car(1,200,"BMXX5",1);
        mockMvc.perform(post("/car").content(mapper.writeValueAsString(carRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/personwithcars").param("personid", "4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.cars").isEmpty());

    }
}