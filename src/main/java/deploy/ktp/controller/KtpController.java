package deploy.ktp.controller;

import deploy.ktp.model.dto.KtpRequest;
import deploy.ktp.model.dto.KtpResponse;
import deploy.ktp.service.KtpService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;
import java.util.List;

@RestController
@RequestMapping("/ktp")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@NoArgsConstructor

public class KtpController {

    @Autowired
    private KtpService ktpService;


    @PostMapping
    public ResponseEntity<KtpResponse> addKtp(@RequestBody KtpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ktpService.addKtp(request));
    }

    @GetMapping
    public ResponseEntity<List<KtpResponse>> getAllKtp() {
        return ResponseEntity.ok(ktpService.getAllktp());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KtpResponse> getKtpById(@PathVariable Integer id) {
        return ResponseEntity.ok(ktpService.getKtpById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KtpResponse> updateKtp(@PathVariable Integer id, @RequestBody KtpRequest request) {
        return ResponseEntity.ok(ktpService.updateKtp(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKtp(@PathVariable Integer id) {
        ktpService.deleteKtp(id);
        return ResponseEntity.ok("KTP dengan id " + id + " berhasil dihapus!");
    }
}
