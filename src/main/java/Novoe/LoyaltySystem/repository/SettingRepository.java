package Novoe.LoyaltySystem.repository;

import Novoe.LoyaltySystem.model.Setting;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

    Setting findByKey(String key);
}
