package web.security.laba1.person;

/*
    @author Daskaliuk
    @project laba1
    @class PersonController
    @version 1.0.0
    @since 24.02.2025 - 15.22
*/

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@AllArgsConstructor
public class PersonRestController {
    private final PersonService service;

    @GetMapping
    public List<Person> getPersons() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable String id) {
        service.deleteById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return service.create(person);
    }

    @PutMapping
    public Person updatePerson(@RequestBody Person person) {
        return service.update(person);
    }
}
