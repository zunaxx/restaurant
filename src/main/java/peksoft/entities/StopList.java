package peksoft.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "stop_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StopList extends BaseEntity{

    private String reason;

    private LocalDate date;

    @OneToOne
    private Menu menu;
}
