package my.vlong.java.homework6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private String id;
    private String email;
    private String password;
    private UserRole userRole;

    @Override
    public String toString() {
        return "AccountDTO{" + "id=" + id + ", email=" + email + ", password=" + password + ", userRole=" + userRole + '}';
    }

}
