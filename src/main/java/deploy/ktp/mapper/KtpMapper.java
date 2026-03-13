package deploy.ktp.mapper;

import deploy.ktp.model.dto.KtpRequest;
import deploy.ktp.model.dto.KtpResponse;
import deploy.ktp.model.entity.Ktp;
import org.springframework.stereotype.Component;

@Component

public class KtpMapper {
    public Ktp toEntity(KtpRequest request) {
        return Ktp.builder()
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();
    }

    public KtpResponse toResponse(Ktp ktp) {
        return KtpResponse.builder()
                .id(ktp.getId())
                .nomorKtp(ktp.getNomorKtp())
                .namaLengkap(ktp.getNamaLengkap())
                .alamat(ktp.getAlamat())
                .tanggalLahir(ktp.getTanggalLahir())
                .jenisKelamin(ktp.getJenisKelamin())
                .build();
    }
}
