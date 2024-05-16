package com.org.lasantos.api.fast.services;

public interface EncryptService {

    String encryptCode (String code);

    Boolean verifyCode(String code, String codeEncrypted);

}
