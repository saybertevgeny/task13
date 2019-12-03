package ru.lanit.integration.mock;

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
import ru.lanit.dto.Car;
import ru.lanit.entity.Person;
import ru.lanit.repository.CarRepositoryInterface;
import ru.lanit.repository.PersonRepositoryInterface;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

    @MockBean
    private PersonRepositoryInterface personRepository;

    @MockBean
    private CarRepositoryInterface carRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void correctSave() throws Exception{
        Person person = new Person();
        person.setId(1).setName("TestUser").setBirthDay(LocalDate.now().minusYears(19));
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        when(personRepository.existsById(anyLong())).thenReturn(true);

        Car carRequest = new Car(1,200,"BMX-X5",1);

        mockMvc.perform(post("/car").content(mapper.writeValueAsString(carRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void unCorrectSavePersonYears() throws Exception{
        Person person = new Person();
        person.setId(1).setName("TestUser").setBirthDay(LocalDate.now().minusYears(17));
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        when(personRepository.existsById(anyLong())).thenReturn(true);

        Car carRequest = new Car(1,200,"BMX-X5",1);

        mockMvc.perform(post("/car").content(mapper.writeValueAsString(carRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void unCorrectSaveCarModel() throws Exception{
        Person person = new Person();
        person.setId(1).setName("TestUser").setBirthDay(LocalDate.now().minusYears(19));
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        when(personRepository.existsById(anyLong())).thenReturn(true);

        Car carRequest = new Car(1,200,"BMXX5",1);

        mockMvc.perform(post("/car").content(mapper.writeValueAsString(carRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}