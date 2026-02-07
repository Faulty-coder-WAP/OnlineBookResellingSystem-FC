package OnlineBookResellingSystem.OBRS_BackEnd.user.entity;

import OnlineBookResellingSystem.OBRS_BackEnd.user.enums.AllowedRoles;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Users")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String userName;
    private String password;
    @Column(unique = true)
    private String email;

    // seperate table for allowed roles
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns =@JoinColumn(name = "user_id"))
    @Column(name ="roles")
    Set<AllowedRoles> roles=new HashSet<>();

}
