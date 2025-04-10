package ua.privat.paymentdataservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.privat.paymentdataservice.DateHelper;
import ua.privat.paymentdataservice.exceptions.*;
import ua.privat.paymentdataservice.services.impl.RegularPaymentImpl;
import ua.privat.utils.dto.RegularPaymentDTO;
import ua.privat.utils.dto.convertor.RegularPaymentConvertor;
import ua.privat.utils.models.RegularPayment;

import java.util.List;

/**
 * Контроллер регулярных платежей
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RegularPaymentController {

    // Сервис для работы с регулярными платежами
    private final RegularPaymentImpl regularPaymentImpl;
    // Конвертор для конвертации RegularPayment в RegularPaymentDTO
    private final RegularPaymentConvertor regularPaymentConvertor;
    // Хелпер для для работы с датой
    private final DateHelper dateHelper;

    /**
     * Создание инструкции проведения платежа
     *
     * @param regularPaymentDTO инструкция проведения платежа
     * @return ResponseEntity<RegularPaymentDTO> ответ API
     */
    @PostMapping("/create-regular-payment")
    public ResponseEntity<RegularPaymentDTO> createRegularPayment(@RequestBody RegularPaymentDTO regularPaymentDTO) {
        RegularPayment regularPaymentWithPrepareDate = dateHelper
                .prepareDate(regularPaymentConvertor.convertToModel(regularPaymentDTO));
        RegularPayment regularPayment = regularPaymentImpl.save(regularPaymentWithPrepareDate)
                .orElseThrow(RegularPaymentWasNotSavedException::new);
        return ResponseEntity.status(HttpStatus.CREATED).body(regularPaymentConvertor.convertToDTO(regularPayment));
    }

    /**
     * Получения инструкции регулярного платежа по ID
     *
     * @param id ID регулярного платежа
     * @return ResponseEntity<RegularPaymentDTO> ответ API
     */
    @GetMapping("/regular-payment/{id}")
    public ResponseEntity<RegularPaymentDTO> getRegularPayment(@PathVariable Long id) {
        RegularPayment regularPayment = regularPaymentImpl.findById(id)
                .orElseThrow(() -> new RegularPaymentNotFoundException("Regular payment with id " + id + " not found!"));
        return ResponseEntity.status(HttpStatus.OK).body(regularPaymentConvertor.convertToDTO(regularPayment));
    }

    /**
     * Получения всех инструкций регулярных платежей
     *
     * @return ResponseEntity<List<RegularPayment>> ответ API, все инструкции регулярных платежей
     */
    @GetMapping("/regular-payments")
    public ResponseEntity<List<RegularPayment>> getAll() {
        List<RegularPayment> regularPaymentList = regularPaymentImpl.findAll();
        if (regularPaymentList.isEmpty()) throw new NoRegularPaymentInDBException();
        return ResponseEntity.status(HttpStatus.OK).body(regularPaymentList);
    }

    /**
     * Получения регулярного платежа или платежей по ИНН
     *
     * @param INN ИНН
     * @return ResponseEntity<List<RegularPayment>> ответ API, инструкция всех регулярных платежей или платежа
     */
    @GetMapping("/regular-payment/inn/{INN}")
    public ResponseEntity<List<RegularPayment>> getRegularPaymentByINN(@PathVariable Long INN) {
        List<RegularPayment> regularPaymentList = regularPaymentImpl.findByINN(INN);
        if (regularPaymentList.isEmpty()) throw new NoRegularPaymentWithThisINNException();
        return ResponseEntity.status(HttpStatus.OK).body(regularPaymentList);
    }

    /**
     * Получения регулярного платежа или платежей по ОКПО
     *
     * @param OKPO ОКПО
     * @return ResponseEntity<List<RegularPayment>> ответ API, инструкция всех регулярных платежей или платежа
     */
    @GetMapping("/regular-payment/okpo/{OKPO}")
    public ResponseEntity<List<RegularPayment>> getRegularPaymentByOKPO(@PathVariable Long OKPO) {
        List<RegularPayment> regularPaymentList = regularPaymentImpl.findByOKPO(OKPO);
        if (regularPaymentList.isEmpty()) throw new NoRegularPaymentWithThisOKPOException();
        return ResponseEntity.status(HttpStatus.OK).body(regularPaymentList);
    }

    /**
     * Обновление инструкции регулярного платежа по ID
     *
     * @param id ID инструкции регулярного платежа
     * @param regularPaymentDTO инструкция проведения платежа
     * @return ResponseEntity<RegularPaymentDTO> ответ API
     */
    @PatchMapping("/update-regular-payment/{id}")
    public ResponseEntity<RegularPaymentDTO> update(@PathVariable Long id, @RequestBody RegularPaymentDTO regularPaymentDTO) {
        // пробуем найти инструкцию регулярного платежа
        RegularPayment regularPaymentOld = regularPaymentImpl.findById(id)
                .orElseThrow(() -> new RegularPaymentNotFoundException("Regular payment with id " + id + " not found!"));
        // обновляем инструкцию регулярного платежа
        RegularPayment regularPayment = regularPaymentImpl
                .update(id, regularPaymentConvertor.convertToModel(regularPaymentDTO))
                .orElseThrow(RegularPaymentNotUpdateException::new);
        // в сущность ответа добавляем ID инструкции платежа, который обновляли
        regularPayment.setId(regularPaymentOld.getId());
        return ResponseEntity.status(HttpStatus.OK).body(regularPaymentConvertor.convertToDTO(regularPayment));
    }

    /**
     * Удаление инструкции регулярного платежа по ID
     *
     * @param id ID инструкции регулярного платежа
     * @return ResponseEntity ответ API
     */
    @DeleteMapping("/delete-regular-payment/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        // пробуем найти инструкцию регулярного платежа
        regularPaymentImpl.findById(id)
                .orElseThrow(() -> new RegularPaymentNotFoundException("Regular payment with id " + id + " not found!"));
        regularPaymentImpl.delete(id);
        return ResponseEntity.ok("The regular payment was deleted successfully!");
    }

    /**
     * Обновляем дату списания в инструкции регулярного платежа
     *
     * @param id ID инструкции регулярного платежа
     * @return ResponseEntity<RegularPaymentDTO> ответ API
     */
    @PatchMapping("/update-write-off-date/{id}")
    public ResponseEntity<RegularPaymentDTO> updateWriteOffDate(@PathVariable Long id) {
        // пробуем найти инструкцию регулярного платежа
        RegularPayment foundRegularPayment = regularPaymentImpl.findById(id)
                .orElseThrow(() -> new RegularPaymentNotFoundException("Regular payment with id " + id + " not found!"));
        // подготавливаем дату списания
        RegularPayment regularPaymentWithUpdatedDate = dateHelper.prepareDate(foundRegularPayment);
        // обновляем инструкцию регулярного платежа
        RegularPayment regularPayment = regularPaymentImpl
                .update(id, regularPaymentWithUpdatedDate)
                .orElseThrow(RegularPaymentNotUpdateException::new);
        return ResponseEntity.status(HttpStatus.OK).body(regularPaymentConvertor.convertToDTO(regularPayment));
    }
}
