package ua.privat.paymentdataservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.privat.clientlib.http.request.ext.RegularPaymentInstructionsExtRequest;
import ua.privat.clientlib.http.response.RegularPaymentInstructionsResponse;
import ua.privat.paymentdataservice.converters.RegularPaymentInstructionsRequestToRegularPaymentInstructions;
import ua.privat.paymentdataservice.converters.RegularPaymentInstructionsToRegularPaymentInstructionsResponse;
import ua.privat.paymentdataservice.entity.RegularPaymentInstructions;
import ua.privat.paymentdataservice.dao.RegularPaymentInstructionsDaoI;
import ua.privat.paymentdataservice.services.RegularPaymentServiceI;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Сервис для работы с инструкциями регулярных платежей
 */
@Service
@RequiredArgsConstructor
public class RegularPaymentService implements RegularPaymentServiceI {

    // Репозиторий для работы с инструкциями регулярных платежей
    private final RegularPaymentInstructionsDaoI regularPaymentInstructions;
    // Конвертор
    private final RegularPaymentInstructionsRequestToRegularPaymentInstructions regularPaymentInstructionsRequestToRegularPaymentInstructions;
    // Конвертор
    private final RegularPaymentInstructionsToRegularPaymentInstructionsResponse regularPaymentInstructionsToRegularPaymentInstructionsResponse;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(RegularPaymentInstructionsExtRequest regularPaymentInstructionsRequest) {
        RegularPaymentInstructions regularPaymentInstructions = this.regularPaymentInstructionsRequestToRegularPaymentInstructions.convert(regularPaymentInstructionsRequest);
        return Objects.nonNull(regularPaymentInstructions) ? this.regularPaymentInstructions.create(regularPaymentInstructions) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long update(RegularPaymentInstructions regularPaymentInstructionsRequest) {
        RegularPaymentInstructions regularPaymentInstruction = this.regularPaymentInstructions.getById(regularPaymentInstructionsRequest.getId()).stream()
                .findFirst()
                .orElse(null);

        if (Objects.isNull(regularPaymentInstruction)) return null;

        Optional.ofNullable(regularPaymentInstructionsRequest.getPayerid()).ifPresent(regularPaymentInstruction::setPayerid);
        Optional.ofNullable(regularPaymentInstructionsRequest.getRecipientid()).ifPresent(regularPaymentInstruction::setRecipientid);
        Optional.ofNullable(regularPaymentInstructionsRequest.getWriteOffPeriod()).filter(StringUtils::hasLength).ifPresent(regularPaymentInstruction::setWriteOffPeriod);
        Optional.ofNullable(regularPaymentInstructionsRequest.getWriteoffdate()).ifPresent(regularPaymentInstruction::setWriteoffdate);
        Optional.ofNullable(regularPaymentInstructionsRequest.getPaymentAmount()).ifPresent(regularPaymentInstruction::setPaymentAmount);
        Optional.ofNullable(regularPaymentInstructionsRequest.getState()).ifPresent(regularPaymentInstruction::setState);

        return this.regularPaymentInstructions.update(regularPaymentInstruction) == 0 ? null : regularPaymentInstructionsRequest.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        this.regularPaymentInstructions.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPaymentInstructionsResponse> findById(Long id) {
        return this.regularPaymentInstructions.getById(id).stream()
                .map(this.regularPaymentInstructionsToRegularPaymentInstructionsResponse::convert)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPaymentInstructionsResponse> findAll() {
        return this.regularPaymentInstructions.getAll().stream()
                .map(this.regularPaymentInstructionsToRegularPaymentInstructionsResponse::convert)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPaymentInstructionsResponse> findByINN(String inn) {
        return this.regularPaymentInstructions.getByINN(inn).stream()
                .map(this.regularPaymentInstructionsToRegularPaymentInstructionsResponse::convert)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPaymentInstructionsResponse> findByOKPO(String okpo) {
        return this.regularPaymentInstructions.getByOKPO(okpo)
                .stream()
                .map(this.regularPaymentInstructionsToRegularPaymentInstructionsResponse::convert)
                .toList();
    }
}
