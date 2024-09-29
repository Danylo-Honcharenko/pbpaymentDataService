package ua.privat.paymentdataservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.privat.paymentdataservice.dto.RegularPaymentDTO;
import ua.privat.paymentdataservice.dto.convertor.RegularPaymentConvertor;
import ua.privat.paymentdataservice.exceptions.*;
import ua.privat.paymentdataservice.models.RegularPayment;
import ua.privat.paymentdataservice.services.RegularPaymentService;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RegularPaymentController {

    private final RegularPaymentService regularPaymentService;
    private final RegularPaymentConvertor regularPaymentConvertor;

    @PostMapping("/create-regular-payment")
    public ResponseEntity<RegularPaymentDTO> createRegularPayment(@RequestBody RegularPaymentDTO regularPaymentDTO) {
        RegularPayment regularPayment = regularPaymentService.save(regularPaymentConvertor.convertToModel(regularPaymentDTO))
                .orElseThrow(RegularPaymentWasNotSavedException::new);
        return ResponseEntity.status(HttpStatus.CREATED).body(regularPaymentConvertor.convertToDTO(regularPayment));
    }

    @GetMapping("/regular-payment/{id}")
    public ResponseEntity<RegularPaymentDTO> getRegularPayment(@PathVariable Long id) {
        RegularPayment regularPayment = regularPaymentService.findById(id)
                .orElseThrow(() -> new RegularPaymentNotFoundException("Regular payment with id " + id + " not found!"));
        return ResponseEntity.status(HttpStatus.OK).body(regularPaymentConvertor.convertToDTO(regularPayment));
    }

    @GetMapping("/regular-payments")
    public ResponseEntity<List<RegularPayment>> getAll() {
        List<RegularPayment> regularPaymentList = regularPaymentService.findAll();
        if (regularPaymentList.isEmpty()) throw new NoRegularPaymentInDBException();
        return ResponseEntity.status(HttpStatus.OK).body(regularPaymentList);
    }

    @GetMapping("/regular-payment/inn/{INN}")
    public ResponseEntity<List<RegularPayment>> getRegularPaymentByINN(@PathVariable Long INN) {
        List<RegularPayment> regularPaymentList = regularPaymentService.findByINN(INN);
        if (regularPaymentList.isEmpty()) throw new NoRegularPaymentWithThisINNException();
        return ResponseEntity.status(HttpStatus.OK).body(regularPaymentList);
    }

    @GetMapping("/regular-payment/okpo/{OKPO}")
    public ResponseEntity<List<RegularPayment>> getRegularPaymentByOKPO(@PathVariable Long OKPO) {
        List<RegularPayment> regularPaymentList = regularPaymentService.findByOKPO(OKPO);
        if (regularPaymentList.isEmpty()) throw new NoRegularPaymentWithThisOKPOException();
        return ResponseEntity.status(HttpStatus.OK).body(regularPaymentList);
    }

    @PatchMapping("/update-regular-payment/{id}")
    public ResponseEntity<RegularPaymentDTO> update(@PathVariable Long id, @RequestBody RegularPaymentDTO regularPaymentDTO) {
        RegularPayment regularPaymentOld = regularPaymentService.findById(id)
                .orElseThrow(() -> new RegularPaymentNotFoundException("Regular payment with id " + id + " not found!"));
        RegularPayment regularPayment = regularPaymentService
                .update(id, regularPaymentConvertor.convertToModel(regularPaymentDTO))
                .orElseThrow(RegularPaymentNotUpdateException::new);
        regularPayment.setId(regularPaymentOld.getId());
        return ResponseEntity.status(HttpStatus.OK).body(regularPaymentConvertor.convertToDTO(regularPayment));
    }

    @DeleteMapping("/delete-regular-payment/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        regularPaymentService.findById(id)
                .orElseThrow(() -> new RegularPaymentNotFoundException("Regular payment with id " + id + " not found!"));
        regularPaymentService.delete(id);
        return ResponseEntity.ok("The regular payment was deleted successfully!");
    }
}
