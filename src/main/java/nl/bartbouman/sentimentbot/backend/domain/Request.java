package nl.bartbouman.sentimentbot.backend.domain;

import lombok.*;

import javax.persistence.Column;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    private @Column(nullable = false) String sentence;

}
