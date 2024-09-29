package ua.privat.paymentdataservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.privat.paymentdataservice.dto.WiringDTO;
import ua.privat.paymentdataservice.dto.convertor.WiringConverter;
import ua.privat.paymentdataservice.exceptions.*;
import ua.privat.paymentdataservice.models.Wiring;
import ua.privat.paymentdataservice.services.RegularPaymentService;
import ua.privat.paymentdataservice.services.WiringService;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class WiringController {

    private final WiringService wiringService;
    private final WiringConverter wiringConverter;
    private final RegularPaymentService regularPaymentService;

    @PostMapping("/create-wiring")
    public ResponseEntity<WiringDTO> createWiring(@RequestBody WiringDTO wiringDTO) {
        Wiring convertedWiring = wiringConverter.convertToModel(wiringDTO);
        Long regularPaymentId = convertedWiring.getPaymentInstructionsId();
        regularPaymentService.findById(regularPaymentId)
                .orElseThrow(() -> new RegularPaymentNotFoundException("Regular payment with id " + regularPaymentId + " not found!"));
        Wiring wiring = wiringService.save(convertedWiring)
                .orElseThrow(WiringNotSaveException::new);
        return ResponseEntity.status(HttpStatus.CREATED).body(wiringConverter.convertToDTO(wiring));
    }

    @GetMapping("/wiring/{id}")
    public ResponseEntity<WiringDTO> getWiring(@PathVariable Long id) {
        Wiring wiring = wiringService.findById(id)
                .orElseThrow(WiringNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK).body(wiringConverter.convertToDTO(wiring));
    }

    @GetMapping("/wiring")
    public ResponseEntity<List<Wiring>> getAllWiring() {
        List<Wiring> wiringList = wiringService.findAll();
        if (wiringList.isEmpty()) throw new NoWiringInDBException();
        return ResponseEntity.status(HttpStatus.OK).body(wiringList);
    }

    @GetMapping("/wiring/payment-id/{paymentId}")
    public ResponseEntity<List<Wiring>> getListWiringByPayment(@PathVariable Long paymentId) {
        List<Wiring> wiringList = wiringService.findWiringByPaymentId(paymentId);
        if (wiringList.isEmpty()) throw new WiringByPaymentIdNotFoundException();
        return ResponseEntity.status(HttpStatus.OK).body(wiringList);
    }

    @PatchMapping("/update-wiring/{id}")
    public ResponseEntity<WiringDTO> update(@PathVariable Long id, @RequestBody WiringDTO wiringDTO) {
        Wiring wiringOld = wiringService.findById(id)
                .orElseThrow(WiringNotFoundException::new);
        Wiring wiring = wiringService
                .update(id, wiringConverter.convertToModel(wiringDTO))
                .orElseThrow(WiringNotUpdatedException::new);
        wiring.setId(wiringOld.getId());
        return ResponseEntity.status(HttpStatus.OK).body(wiringConverter.convertToDTO(wiring));
    }

    @PatchMapping("/update-wiring-status")
    public ResponseEntity<WiringDTO> reverseWiring(@RequestParam Long id, @RequestParam String status) {
        Wiring wiringOld = wiringService.findById(id)
                .orElseThrow(WiringNotFoundException::new);
        wiringOld.setStatus(status);
        Wiring wiring = wiringService
                .update(id, wiringOld)
                .orElseThrow(WiringNotUpdatedException::new);
        return ResponseEntity.status(HttpStatus.OK).body(wiringConverter.convertToDTO(wiring));
    }

    @DeleteMapping("/delete-wiring/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        wiringService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("The wiring was deleted successfully!");
    }
}
