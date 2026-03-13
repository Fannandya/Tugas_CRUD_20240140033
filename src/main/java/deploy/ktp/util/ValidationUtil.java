package deploy.ktp.util;

import deploy.ktp.model.dto.KtpRequest;
import deploy.ktp.model.entity.Ktp;
import org.springframework.stereotype.Component;

@Component

public class ValidationUtil {
    public void validate(KtpRequest request) {
        if (request.getNomorKtp() == null || request.getNomorKtp().isBlank()) {
            throw new RuntimeException("nomor KTP tidak boleh kosong!");
        }
        if (request.getNomorKtp().length() < 16) {
            throw new RuntimeException("nomor KTP harus terdiri dari 16 digit!");
        }
    }
}
