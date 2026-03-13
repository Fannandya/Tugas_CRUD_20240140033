package deploy.ktp.service;

import deploy.ktp.model.dto.KtpRequest;
import deploy.ktp.model.dto.KtpResponse;
import java.util.List;

public interface KtpService {
    KtpResponse addKtp(KtpRequest request);
    List<KtpResponse> getAllktp();
    KtpResponse getKtpById(Integer id);
    KtpResponse updateKtp(Integer id, KtpRequest request);
    void deleteKtp(Integer id);
}


