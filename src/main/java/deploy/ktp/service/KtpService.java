package deploy.ktp.service;

import deploy.ktp.model.entity.Ktp;
import java.util.List;

public interface KtpService {
    Ktp addKtp(Ktp ktp);
    List<Ktp> getAllktp();
    Ktp getKtpById(Integer id);
    Ktp updateKtp(Integer id, Ktp ktp);
    void deleteKtp(Integer id);
}


