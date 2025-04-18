package edu.pzks.security25.item;

/*
@author   Daskaliuk
@project  security25
@class  Item
@version  1.0.0
@since ${DATE}  17.03.2025 -19.40
*/

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Item  extends AuditMetaData{

    @Id
    private String id;
    private String name;
    private String description;
   // private LocalDateTime createdAt;
   // private String createdBy;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item item)) return false;
        return getId().equals(item.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
