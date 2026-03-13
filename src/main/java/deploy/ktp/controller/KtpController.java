package deploy.ktp.controller;

import deploy.ktp.model.entity.Ktp;
import deploy.ktp.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ktp")
@CrossOrigin(origins = "*")

public class KtpController {

    @Autowired
    private KtpService ktpService;

    @PostMapping
    public ResponseEntity<?> addKtp(@RequestBody Ktp ktp) {
        return ResponseEntity.ok(ktpService.addKtp(ktp))
    }

    @GetMapping
    public List<Ktp> getAllKtp() {
        return ktpService.getAllktp();
    }

    @GetMapping("/{id}")
    public Ktp getKtpById(@PathVariable Integer id) {
        return ktpService.getKtpById(id);
    }

    @PutMapping("/{id}")
    public Ktp updateKtp(@PathVariable Integer id, @RequestBody Ktp ktp) {
        return ktpService.updateKtp(id, ktp);
    }

    @DeleteMapping("/{id}")
    public String deleteKtp(@PathVariable Integer id) {
        ktpService.deleteKtp(id);
        return "KTP dengan id " + id + " berhasil dihapus.";
    }
}
