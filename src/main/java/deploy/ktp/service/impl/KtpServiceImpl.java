package deploy.ktp.service.impl;

import deploy.ktp.mapper.KtpMapper;
import deploy.ktp.model.dto.KtpRequest;
import deploy.ktp.model.dto.KtpResponse;
import deploy.ktp.model.entity.Ktp;
import deploy.ktp.repository.KtpRepository;
import deploy.ktp.service.KtpService;
import deploy.ktp.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository ktpRepository;

    @Autowired
    private KtpMapper ktpMapper;

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public KtpResponse addKtp(KtpRequest request) {

        validationUtil.validate(request);

        if (ktpRepository.findByNomorKtp(request.getNomorKtp()).isPresent()) {
            throw new RuntimeException("nomor KTP sudah terdaftar!");
        }
        Ktp ktp = ktpMapper.toEntity(request);
        Ktp savedKtp = ktpRepository.save(ktp);
        return ktpMapper.toResponse(savedKtp);
    }

    @Override
    public List<KtpResponse> getAllktp() {
        return ktpRepository.findAll().stream()
                .map(ktpMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public KtpResponse getKtpById(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KTP dengan id " + id + " tidak ditemukan!"));
        return ktpMapper.toResponse(ktp);
    }

    @Override
    public KtpResponse updateKtp(Integer id, KtpRequest request) {
        validationUtil.validate(request);

        Ktp existingKtp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KTP dengan id " + id + " tidak ditemukan!"));

        existingKtp.setNamaLengkap(request.getNamaLengkap());
        existingKtp.setAlamat(request.getAlamat());
        existingKtp.setTanggalLahir(request.getTanggalLahir());
        existingKtp.setJenisKelamin(request.getJenisKelamin());

        Ktp updatedKtp = ktpRepository.save(existingKtp);
        return ktpMapper.toResponse(updatedKtp);

    }

    @Override
    public void deleteKtp(Integer id) {
        if (!ktpRepository.existsById(id)) {
            throw new RuntimeException("gagal menghapus KTP dengan id " + id + " karena tidak ditemukan!");
        }
        ktpRepository.deleteById(id);
    }
}
