package pl.kawkadnia.common.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private int statusCode;

    public ErrorMessage(String message) {
        this.message = message;
        this.statusCode = 500;
    }
}
