package Novoe.LoyaltySystem.repository;

import Novoe.LoyaltySystem.model.Forgot;
import Novoe.LoyaltySystem.model.Setting;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ForgotRepository extends JpaRepository<Forgot, Long> {

    Forgot findByToken(String token);
}
