package deploy.ktp.service.impl;

import deploy.ktp.model.entity.Ktp;
import deploy.ktp.repository.KtpRepository;
import deploy.ktp.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository ktpRepository;

    @Override
    public Ktp addKtp(Ktp ktp) {
        if (ktpRepository.findByNomorKtp(ktp.getNomorKtp()).isPresent()) {
            throw new RuntimeException("nomor KTP sudah terdaftar!");
        }

        return ktpRepository.save(ktp);
    }

    @Override
    public List<Ktp> getAllktp() {
        return ktpRepository.findAll();
    }

    @Override
    public Ktp getKtpById(Integer id) {
        return ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("KTP tidak ditemukan dengan id: " + id));
    }

    @Override
    public Ktp updateKtp(Integer id, Ktp ktp) {
        Ktp existing = getKtpById(id);
        existing.setNamaLengkap(ktp.getNamaLengkap());
        existing.setAlamat(ktp.getAlamat());
        existing.setTanggalLahir(ktp.getTanggalLahir());
        existing.setJenisKelamin(ktp.getJenisKelamin());
        return ktpRepository.save(existing);
    }

    @Override
    public void deleteKtp(Integer id) {
        ktpRepository.deleteById(id);
    }
}
