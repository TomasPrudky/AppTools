package cs.prudkytomas.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class URLRecord {

    private String url;
    private String shortUrl;
    private LocalDateTime expirationTime;
}