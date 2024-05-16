package com.org.lasantos.api.fast.servicesImpl;

import com.org.lasantos.api.fast.services.EncryptService;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptServiceImpl implements EncryptService {

    public EncryptServiceImpl() {
    }

    @Override
    public String encryptCode(String code) {
        return BCrypt.hashpw(code, BCrypt.gensalt());
    }

    @Override
    public Boolean verifyCode(String code, String codeEncrypted) {
        return BCrypt.checkpw(code, codeEncrypted);
    }
}
