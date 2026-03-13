package deploy.ktp.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "ktp")
@getter
@Setter
@builder
@NoArgsConstructor
@AllArgsConstructor

public class Ktp {
    @Id
    @generatedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String nomorKtp;

    private String namaLengkap;
    private String alamat;
    private LocalDate tanggalLahir;
    private String jenisKelamin;
}
