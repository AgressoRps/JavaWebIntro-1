package org.communis.javawebintro.service;

import org.communis.javawebintro.dto.PilotWrapper;
import org.communis.javawebintro.dto.filters.PilotFilterWrapper;
import org.communis.javawebintro.entity.Pilot;
import org.communis.javawebintro.enums.PilotStatus;
import org.communis.javawebintro.exception.ServerException;
import org.communis.javawebintro.exception.error.ErrorCodeConstants;
import org.communis.javawebintro.exception.error.ErrorInformationBuilder;
import org.communis.javawebintro.repository.PilotRepository;
import org.communis.javawebintro.repository.specifications.PilotSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ServerException.class)
public class PilotService {

    private final PilotRepository pilotRepository;

    @Autowired
    public PilotService(PilotRepository pilotRepository){
        this.pilotRepository = pilotRepository;
    }

    /**
     * Добавляет информацию о новом пилоте в базу из {@link PilotWrapper}
     *
     * @param pilotWrapper инфомарция о новом пилоте
     * @return CustomHttpObject с кодом "OK" или с кодом "ERROR" и сообщением об ошибке
     */
    public void add(PilotWrapper pilotWrapper) throws ServerException {
        try {
            Pilot pilot = new Pilot();
            pilotWrapper.fromWrapper(pilot);
            if (pilotRepository.findFirstByMail(pilotWrapper.getMail()).isPresent()) {
                throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.USER_LOGIN_ALREADY_EXIST));
            }
            pilot.setStatus(PilotStatus.AVAILABLE);

            pilotRepository.save(pilot);
        } catch (ServerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.USER_ADD_ERROR), ex);
        }
    }

    /**
     * Обновляет информацию о пилоте в базе из {@link PilotWrapper}
     *
     * @param pilotWrapper инфомарция о пилоте
     * @return CustomHttpObject с кодом "OK" или с кодом "ERROR" и сообщением об ошибке
     */
    public void edit(PilotWrapper pilotWrapper) throws ServerException {
        try {
            Pilot pilot = getPilot(pilotWrapper.getId());
            pilotWrapper.fromWrapper(pilot);
            pilotRepository.save(pilot);
        } catch (ServerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.USER_UPDATE_ERROR), ex);
        }
    }

    /**
     * Получить экземпляр класса пилот по идентификатору из базы
     * @param id идентификатор пилота
     * @return экземпляр класса Pilot
     */
    private Pilot getPilot(Long id) throws ServerException {
        return pilotRepository.findById(id)
                .orElseThrow(() -> new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.DATA_NOT_FOUND)));
    }

    /**
     * Удаляет пилота из базы
     * @param id идентификатор пилота
     * @return CustomHttpObject с кодом "OK" или с кодом "ERROR" и сообщением об ошибке
     */
    public void delete(Long id) throws ServerException{
        try {
            Pilot pilot = getPilot(id);
            pilotRepository.delete(pilot);
        }catch (ServerException ex){
            throw ex;
        }catch (Exception ex){
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.USER_DELETE_ERROR));
        }
    }

    /**
     * Устанавливает пользователю с заданным идентификатором статус "FLIGHT(В полете)"
     *
     * @param id идентификатор пилота
     * @return CustomHttpObject с кодом "OK" или с кодом "ERROR" и сообщением об ошибке
     */
    public void flight(Long id) throws ServerException {
        try {
            Pilot pilot = getPilot(id);

            pilot.setStatus(PilotStatus.FLIGHT);
            pilotRepository.save(pilot);
        } catch (ServerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.USER_BLOCK_ERROR), ex);
        }
    }

    /**
     * Устанавливает пилоту с заданным идентификатором статус "AVAILABLE(Доступен)"
     *
     * @param id идентификатор пилота
     * @return CustomHttpObject с кодом "OK" или с кодом "ERROR" и сообщением об ошибке
     */
    public void available(Long id) throws ServerException {
        try {
            Pilot pilot = getPilot(id);
            pilot.setStatus(PilotStatus.AVAILABLE);
            pilotRepository.save(pilot);
        } catch (ServerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.USER_UNBLOCK_ERROR), ex);
        }
    }

    /**
     * Получает информацию о пилоте с заданным идентификатором из базы и пребразует ее в объект класса {@link PilotWrapper}
     *
     * @param id идентификатор пилота
     * @return объект, содержащий информацию о пилоте
     */
    public PilotWrapper getById(Long id) throws ServerException {
        try {
            return new PilotWrapper(getPilot(id));
        } catch (ServerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.USER_INFO_ERROR), ex);
        }
    }

    /**
     * Получает из базы страницу объектов {@link PilotWrapper} в зависимости от информации о пагинаторе и параметрах фильтра
     *
     * @param pageable          информация о пагинаторе
     * @param pilotFilterWrapper информация о фильтре
     * @return страница объектов
     */
    public Page getPageByFilter(Pageable pageable, PilotFilterWrapper pilotFilterWrapper) throws ServerException {
        try {
            return pilotRepository.findAll(PilotSpecification.build(pilotFilterWrapper), pageable)
                    .map(PilotWrapper::new);
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.USER_LIST_ERROR), ex);
        }
    }

}
