package Novoe.LoyaltySystem.service;

import Novoe.LoyaltySystem.model.Permission;
import Novoe.LoyaltySystem.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    public Permission findByName(String name){
        return permissionRepository.findByName(name);
    }
}
