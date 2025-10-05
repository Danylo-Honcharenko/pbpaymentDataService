package ua.privat.paymentdataservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.privat.clientlib.http.request.ext.RegularPaymentInstructionsExtRequest;
import ua.privat.clientlib.http.response.RegularPaymentInstructionsResponse;
import ua.privat.paymentdataservice.entity.RegularPaymentInstructions;
import ua.privat.paymentdataservice.services.RegularPaymentServiceI;

import java.util.List;

/**
 * Контроллер регулярных платежей
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RegularPaymentController {

    // Сервис для работы с регулярными платежами
    private final RegularPaymentServiceI regularPayment;

    /**
     * Создание инструкции проведения платежа
     *
     * @param regularPaymentInstructionsRequest инструкция проведения платежа
     * @return ResponseEntity<Long> ответ API
     */
    @PostMapping("/create-regular-payment")
    public ResponseEntity<Long> createRegularPayment(@RequestBody RegularPaymentInstructionsExtRequest regularPaymentInstructionsRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.regularPayment.create(regularPaymentInstructionsRequest));
    }

    /**
     * Получения инструкции регулярного платежа по ID
     *
     * @param id ID регулярного платежа
     * @return ResponseEntity<RegularPaymentInstructionsResponse> ответ API
     */
    @GetMapping("/regular-payment/{id}")
    public ResponseEntity<List<RegularPaymentInstructionsResponse>> getRegularPayment(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.regularPayment.findById(id));
    }

    /**
     * Получения всех инструкций регулярных платежей
     *
     * @return ResponseEntity<List<RegularPayment>> ответ API
     */
    @GetMapping("/regular-payments")
    public ResponseEntity<List<RegularPaymentInstructionsResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.regularPayment.findAll());
    }

    /**
     * Получения регулярного платежа или платежей по ИНН
     *
     * @param inn ИНН
     * @return ResponseEntity<List<RegularPaymentInstructionsResponse>> ответ API
     */
    @GetMapping("/regular-payment/inn/{inn}")
    public ResponseEntity<List<RegularPaymentInstructionsResponse>> getRegularPaymentByINN(@PathVariable String inn) {
        return ResponseEntity.status(HttpStatus.OK).body(this.regularPayment.findByINN(inn));
    }

    /**
     * Получения регулярного платежа или платежей по ОКПО
     *
     * @param okpo ОКПО
     * @return ResponseEntity<List<RegularPayment>> ответ API, инструкция всех регулярных платежей или платежа
     */
    @GetMapping("/regular-payment/okpo/{okpo}")
    public ResponseEntity<List<RegularPaymentInstructionsResponse>> getRegularPaymentByOKPO(@PathVariable String okpo) {
        return ResponseEntity.status(HttpStatus.OK).body(this.regularPayment.findByOKPO(okpo));
    }

    /**
     * Обновление инструкции регулярного платежа по ID
     *
     * @param regularPaymentInstructionsRequest инструкция проведения платежа
     * @return ResponseEntity<Long> ответ API
     */
    @PatchMapping("/update-regular-payment")
    public ResponseEntity<Long> update(@RequestBody RegularPaymentInstructions regularPaymentInstructionsRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(this.regularPayment.update(regularPaymentInstructionsRequest));
    }

    /**
     * Удаление инструкции регулярного платежа по ID
     *
     * @param id ID инструкции регулярного платежа
     * @return ResponseEntity<String> ответ API
     */
    @DeleteMapping("/delete-regular-payment/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        regularPayment.delete(id);
        return ResponseEntity.ok("The regular payment was deleted successfully!");
    }
}
