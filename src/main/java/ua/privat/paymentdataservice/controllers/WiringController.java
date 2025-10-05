package ua.privat.paymentdataservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.privat.clientlib.http.request.WiringRequest;
import ua.privat.clientlib.http.response.WiringResponse;
import ua.privat.paymentdataservice.services.impl.WiringService;

import java.util.List;

/**
 * Контроллер проводок по регулярным платежам
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class WiringController {
    // Сервис для работы с проводками
    private final WiringService wiringService;

    /**
     * Создать проводку
     *
     * @param wiringRequest запрос
     * @return ResponseEntity<Long> ответ API
     */
    @PostMapping("/create-wiring")
    public ResponseEntity<Long> createWiring(@RequestBody WiringRequest wiringRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.wiringService.create(wiringRequest));
    }

    /**
     * Получить проводку по ID
     *
     * @param id ID проводки
     * @return ResponseEntity<WiringResponse> ответ API
     */
    @GetMapping("/wiring/{id}")
    public ResponseEntity<List<WiringResponse>> getWiring(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.wiringService.findById(id));
    }

    /**
     * Получить все проводки
     *
     * @return ResponseEntity<List<WiringResponse>> ответ API
     */
    @GetMapping("/wiring")
    public ResponseEntity<List<WiringResponse>> getAllWiring() {
        return ResponseEntity.status(HttpStatus.OK).body(this.wiringService.findAll());
    }

    /**
     * Получить все проводки по платежу
     *
     * @param paymentId ID платежа
     * @return ResponseEntity<List<WiringResponse>> ответ API
     */
    @GetMapping("/wiring/payment-id/{paymentId}")
    public ResponseEntity<List<WiringResponse>> getListWiringByPayment(@PathVariable Long paymentId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.wiringService.findWiringByPaymentId(paymentId));
    }

    /**
     * Обновить проводку по ID
     *
     * @param wiringRequest запрос
     * @return ResponseEntity<WiringResponse> ответ API
     */
    @PatchMapping("/update-wiring")
    public ResponseEntity<WiringResponse> update(@RequestBody WiringRequest wiringRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(this.wiringService.update(wiringRequest));
    }

    /**
     * Удалить проводку
     *
     * @param id ID проводки
     * @return ResponseEntity<String> ответ API
     */
    @DeleteMapping("/delete-wiring/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        wiringService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("The wiring was deleted successfully!");
    }
}
