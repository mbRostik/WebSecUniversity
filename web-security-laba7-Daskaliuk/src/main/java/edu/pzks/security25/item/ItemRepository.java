package edu.pzks.security25.item;

import com.mongodb.MongoCredential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
@author   Daskaliuk
@project  security25
@class  Item
@version  1.0.0
@since ${DATE}  17.03.2025 -19.40
*/
@Repository

public interface ItemRepository  extends MongoRepository<Item,String> {
}
