package web.security.laba1.person;

import org.springframework.data.mongodb.repository.MongoRepository;

/*
    @author Daskaliuk
    @project laba1
    @class PersonRepository
    @version 1.0.0
    @since 24.02.2025 - 15.20
*/

public interface PersonRepository extends MongoRepository<Person, String> {
}
