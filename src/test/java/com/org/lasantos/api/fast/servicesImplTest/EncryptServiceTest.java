package com.org.lasantos.api.fast.servicesImplTest;

import com.org.lasantos.api.fast.services.EncryptService;
import com.org.lasantos.api.fast.servicesImpl.EncryptServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EncryptServiceTest {

    @InjectMocks
    private EncryptService encryptService = new EncryptServiceImpl();

    @Test
    public void testEncryptPassword() {
        String password = "mySecretPassword";
        String salt = "$2a$10$DowJonesIndex1234567890";
        String hashedPassword = "$2a$10$DowJonesIndex1234567890ABCDE";

        try (MockedStatic<BCrypt> mockedBCrypt = Mockito.mockStatic(BCrypt.class)) {
            mockedBCrypt.when(BCrypt::gensalt).thenReturn(salt);
            mockedBCrypt.when(() -> BCrypt.hashpw(anyString(), eq(salt))).thenReturn(hashedPassword);

            String result = encryptService.encryptCode(password);

            assertNotNull(result, "El resultado no debería ser nulo");

            assertEquals(hashedPassword, result, "El hash de la contraseña no coincide con el esperado");

            mockedBCrypt.verify(() -> BCrypt.hashpw(eq(password), eq(salt)), times(1));
        }
    }

    @Test
    public void testCheckPassword() {
        String password = "mySecretPassword";
        String hashedPassword = "$2a$10$DowJonesIndex1234567890ABCDE";
        boolean expectedCheckResult = true;

        try (MockedStatic<BCrypt> mockedBCrypt = Mockito.mockStatic(BCrypt.class)) {
            mockedBCrypt.when(() -> BCrypt.checkpw(anyString(), anyString())).thenReturn(expectedCheckResult);

            boolean result = encryptService.verifyCode(password, hashedPassword);

            assertTrue(result, "La verificación de la contraseña debería ser verdadera");

            // Verificar que el método estático fue llamado con los argumentos correctos
            mockedBCrypt.verify(() -> BCrypt.checkpw(password, hashedPassword), times(1));
        }
    }

}
