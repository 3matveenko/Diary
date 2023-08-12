package Novoe.LoyaltySystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Таблица настроек
 */
@Getter
@Setter
@Entity
@Table(name="t_settings")
public class Setting extends BaseEntity{

    /**
     * наименование настройки
     */
    @Column(name = "this_key")
    private String key;

    /**
     * значение
     */
    @Column(name = "value")
    private String value;

    /*
    Добавить в базу:
    key: system_name value: "название этой системы"
    key:
     */
}
