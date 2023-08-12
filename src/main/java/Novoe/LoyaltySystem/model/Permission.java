package Novoe.LoyaltySystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * В приложении есть 2 роли.
 * Admin: создает всех админов,компании.
 * User: ведет учетную запись своей компании
 */
@Entity
@Table (name = "t_permissions")
@Getter
@Setter
public class Permission extends BaseEntity implements GrantedAuthority {

    /**
     * Название роли
     */
    @Column(name = "name")
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
