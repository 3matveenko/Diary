package Novoe.LoyaltySystem.service;

import Novoe.LoyaltySystem.model.Forgot;
import Novoe.LoyaltySystem.model.Setting;
import Novoe.LoyaltySystem.model.Status;
import Novoe.LoyaltySystem.repository.ForgotRepository;
import Novoe.LoyaltySystem.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class ForgotService {

    @Autowired
    UserService userService;

    @Autowired
    SettingRepository settingRepository;

    @Autowired
    ForgotRepository forgotRepository;

    public void sendLink(String email){
        Forgot forgot = new Forgot();
        forgot.setToken(UUID.randomUUID().toString());
        LocalDateTime creationToken = LocalDateTime.now();
        forgot.setCreationTime(creationToken);
        forgot.setExpiryTime(creationToken.plus(24, ChronoUnit.HOURS));
        String token = UUID.randomUUID().toString();
        forgot.setEmail(email);
        forgot.setToken(token);
        forgotRepository.save(forgot);
        Setting setting = settingRepository.findByKey("server_name");
        String header = "Ссылка для восстановления пароля";
        String text = "Для восстановления пароля перейдите по ссылке "+setting.getValue()+
                "/forgot/change_password/"+token+"/"+email;
        userService.sendmessage(email, header, text);
    }

    public Status outOfTime(String token){
        Forgot forgot = forgotRepository.findByToken(token);
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isBefore(forgot.getExpiryTime())){
        return Status.SUCCESS;
        } else {
        return Status.ERROR;
        }
    }
}
