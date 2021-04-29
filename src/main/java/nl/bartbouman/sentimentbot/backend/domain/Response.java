package nl.bartbouman.sentimentbot.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private @Column(nullable = false) OffsetDateTime timestamp;
    private @Column(nullable = false) String sentence;
    private @Column(nullable = false) String sentiment;

}
