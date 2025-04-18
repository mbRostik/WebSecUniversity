package web.security.laba1.person;

/*
    @author Daskaliuk
    @project laba1
    @class PersonService
    @version 1.0.0
    @since 24.02.2025 - 15.21
*/

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository repository;
    private List<Person> persons;

    @PostConstruct
    void init() {
        persons.add(new Person("1", "Bob", 23));
        persons.add(new Person("2", "Alice", 40));
        persons.add(new Person("3", "Ann", 37));
        repository.saveAll(persons);
    }

    public List<Person> getAll() {
        return repository.findAll();
    }

    public Person getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Person create(Person person) {
        return repository.save(person);
    }

    public Person update(Person person) {
        return repository.save(person);
    }
}
