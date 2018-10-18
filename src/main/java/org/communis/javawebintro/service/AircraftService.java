package org.communis.javawebintro.service;

import org.communis.javawebintro.dto.AircraftWrapper;
import org.communis.javawebintro.dto.filters.AircraftFilterWrapper;
import org.communis.javawebintro.entity.Aircraft;
import org.communis.javawebintro.enums.AircraftStatus;
import org.communis.javawebintro.exception.ServerException;
import org.communis.javawebintro.exception.error.ErrorCodeConstants;
import org.communis.javawebintro.exception.error.ErrorInformationBuilder;
import org.communis.javawebintro.repository.*;
import org.communis.javawebintro.repository.specifications.AircraftSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = ServerException.class)
public class AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AircraftNameRepository nameRepository;
    private final AircraftTypeRepository typeRepository;
    private final CompanyRepository companyRepository;
    private final PilotRepository pilotRepository;

    @Autowired
    public AircraftService(AircraftRepository aircraftRepository, AircraftNameRepository nameRepository,
                           AircraftTypeRepository typeRepository, CompanyRepository companyRepository,
                           PilotRepository pilotRepository){
        this.aircraftRepository = aircraftRepository;
        this.nameRepository = nameRepository;
        this.typeRepository = typeRepository;
        this.companyRepository = companyRepository;
        this.pilotRepository = pilotRepository;
    }

    /**
     * Получает из базы страницу объектов {@link AircraftWrapper} в зависимости от информации о пагинаторе и параметрах фильтра
     *
     * @param filter   информация о фильтре
     * @param pageable информация о пагинаторе
     * @return страница объектов
     */
    public Page getPageByFilter(Pageable pageable, AircraftFilterWrapper filter) throws ServerException {
        try {
            return aircraftRepository.findAll(AircraftSpecification.build(filter), pageable)
                    .map(AircraftWrapper::new);
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.AIRCRAFT_LIST_ERROR), ex);
        }
    }

    /**
     * Добавляет в базу информациб о новом ldap-сервере из объекта {@link AircraftWrapper}
     *
     * @param aircraftWrapper информация о новом самолете
     * @return CustomHttpObject с кодом "OK" или с кодом "ERROR" и сообщением об ошибке
     */
    public Long add(AircraftWrapper aircraftWrapper) throws ServerException {
        try {
            Aircraft aircraft = new Aircraft();
            aircraftWrapper.fromWrapper(aircraft);

            aircraft.setStatus(AircraftStatus.AVAILABLE);
            return aircraftRepository.save(aircraft).getId();
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.AIRCRAFT_ADD_ERROR), ex);
        }
    }

    /**
     * Обновляет информацию о самолете в базу из объекта {@link AircraftWrapper}
     *
     * @param aircraftWrapper информация о самолете
     * @return CustomHttpObject с кодом "OK" или с кодом "ERROR" и сообщением об ошибке
     */
    public void edit(AircraftWrapper aircraftWrapper) throws ServerException {
        try {
            Aircraft ldapAuth = aircraftRepository.findOne(aircraftWrapper.getId());
            aircraftWrapper.fromWrapper(ldapAuth);
            System.out.println("ldapAuthWrapper:");
            System.out.println(aircraftWrapper);
            aircraftRepository.save(ldapAuth);
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.AIRCRAFT_UPDATE_ERROR), ex);
        }
    }

    /**
     * Получает информацию о самолете по идентификатору из базы
     *
     * @param id идентификатор самолета
     * @return информация о самолете
     */
    public AircraftWrapper getForEdit(Long id) throws ServerException {
        try {
            return new AircraftWrapper(aircraftRepository.findOne(id));
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.AIRCRAFT_INFO_ERROR), ex);
        }
    }

    public List<String> getGroups(AircraftWrapper wrapper) throws ServerException {
        try {
            //TODO получать
            return null;

        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.AIRCRAFT_GROUPS_ERROR), ex);
        }
    }

    /**
     * Получает из базы список всех доступных самолетов
     *
     * @return список всех доступных самолетов в виде объектов класса {@link AircraftWrapper}
     */
    public List<AircraftWrapper> getAllAvailable() throws ServerException {
        try {
            return aircraftRepository.findByStatus(AircraftStatus.AVAILABLE).stream().map(AircraftWrapper::new).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.AIRCRAFT_LIST_ERROR), ex);
        }
    }

    /**
     * Устанавливает самолету значение статуса AVAILABLE и сохраняет изменения в базе
     *
     * @param id идентификатор самолета
     * @return CustomHttpObject с кодом "OK" или с кодом "ERROR" и сообщением об ошибке
     */
    public void available(Long id) throws ServerException {
        try {
            Aircraft auth = aircraftRepository.findOne(id);
            auth.setStatus(AircraftStatus.AVAILABLE);
            aircraftRepository.save(auth);
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.AIRCRAFT_ACTIVATE_ERROR), ex);
        }
    }

    /**
     * Устанавливает самолету значение состояние REPAIR и сохраняет изменения в базе
     *
     * @param id идентификатор самолета
     * @return CustomHttpObject с кодом "OK" или с кодом "ERROR" и сообщением об ошибке
     */
    public void repair(Long id) throws ServerException {
        try {
            Aircraft auth = aircraftRepository.findOne(id);
            auth.setStatus(AircraftStatus.REPAIR);
            aircraftRepository.save(auth);
        } catch (Exception ex) {
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.AIRCRAFT_DEACTIVATE_ERROR), ex);
        }
    }

    /**
     * Удаляет самолет из базы
     *
     * @param id идентификатор самолета
     * @return CustomHttpObject с кодом "OK" или с кодом "ERROR" и сообщением об ошибке
     */
    public void delete(Long id) throws ServerException{
        try {
            Aircraft auth = aircraftRepository.findOne(id);
            aircraftRepository.delete(auth);
        }catch (Exception ex){
            throw new ServerException(ErrorInformationBuilder.build(ErrorCodeConstants.AIRCRAFT_DELETE_ERROR));
        }
    }
}
