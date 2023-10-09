package cs.prudkytomas.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoURLRecord {

    private String url;
    private String shortUrl;
    private LocalDateTime expirationTime;
}
