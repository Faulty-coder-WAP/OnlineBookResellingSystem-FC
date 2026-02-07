package OnlineBookResellingSystem.OBRS_BackEnd.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userDto
{

    //auto id generation
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]{3,30}$",
            message = "Name Should Only Contain Alphabets And Min Size of 3")
    private String username;

    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@$&*#])[A-Za-z0-9@$&*#]{8,}$",
            message = "password must contain min 8 characters with a number,special character(@$&*#),BothUpper And LowerCase Alphabets")
    private String password;

    @NotBlank
    @Email(message = "Invalid Email Format")
    @Size(min=6,max = 254)
    private String email;
}
