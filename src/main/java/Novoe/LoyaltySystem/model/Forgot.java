package Novoe.LoyaltySystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Класс ссылок восстановления пароля
 * (Функционал доступен админам компаний)
 */
@Getter
@Setter
@Entity
@Table(name="t_forgot")
public class Forgot extends BaseEntity{

    /**
     * Почта админа
     */
    @Column(name = "email")
    private String email;

    /**
     * Уникальный токен как часть ссылки восстановления
     */
    @Column(name = "token")
    private String token;

    /**
     * время создания ссылки (срок годности 24 часа)
     */
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    /**
     * Время окончания срока годности ссылки
     */
    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;
}
