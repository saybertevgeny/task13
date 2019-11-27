package ru.lanit.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.lanit.dto.CarDto;
import ru.lanit.dto.PersonDto;
import ru.lanit.dto.request.Person;
import ru.lanit.service.PersonService;
import java.time.LocalDate;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void save() throws Exception {
        Person requestPerson = new Person();
        requestPerson.setId(1).setName("TestUser").setBirthdate(LocalDate.now().minusYears(19));

        mockMvc.perform(post("/person").content(mapper.writeValueAsString(requestPerson)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void personWithCars() throws Exception {
        PersonDto personDto = new PersonDto(1, "test", LocalDate.of(2000, 11, 26));
        personDto.addCar(new CarDto(1, "X5", "BMW", 200));
        personDto.addCar(new CarDto(2, "Granta", "LADA", 80));

        when(personService.getWithCars(anyLong())).thenReturn(personDto);

        mockMvc.perform(get("/personwithcars").param("personid", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonPersonWithCars()));
    }

    private String expectedJsonPersonWithCars() {
        String exctpectedJson = "{\"id\":1,\"name\":\"test\",\"birthDay\":\"26.11.2000\",\"cars\":";
        exctpectedJson += "[{\"id\":1,\"model\":\"X5\",\"vendor\":\"BMW\",\"horsepower\":200},";
        exctpectedJson += "{\"id\":2,\"model\":\"Granta\",\"vendor\":\"LADA\",\"horsepower\":80}]}";
        return exctpectedJson;
    }
}