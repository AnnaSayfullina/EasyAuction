package com.example.course_easyauction.service;

import com.example.course_easyauction.dto.CreateLot;
import com.example.course_easyauction.dto.LotDTO;
import com.example.course_easyauction.dto.LotFullInfo;
import com.example.course_easyauction.exceptions.LotNotFoundException;
import com.example.course_easyauction.model.Status;
import com.example.course_easyauction.repository.LotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotServiceImpl implements LotService{

    private final LotRepository lotRepository;

    Logger logger = LoggerFactory.getLogger(BidServiceImpl.class);

    public LotServiceImpl(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }

    @Override
    public LotFullInfo getFullLot(int id) {
        logger.info("Вызван метод получения полной информации о лоте, с учетом текущей цены, по id = {}", id);

        LotFullInfo lotFullInfo = LotFullInfo.fromLot(lotRepository.findLotById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с id = {} не найден", id);
                    return new LotNotFoundException();
                }));
        logger.info("получена полная информация по лоту {} ", lotFullInfo);

        return  lotFullInfo;
    }

    @Override
    public void startBidding(int id) {
        logger.info("Начать торги по лоту по id = {}", id);

        LotDTO lotDTO = LotDTO.fromLot(lotRepository.findLotById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с id = {} не найден", id);
                    return new LotNotFoundException();
                }));

        if (lotDTO.getStatus() == Status.CREATED) {
            lotDTO.setStatus(Status.STARTED);
            lotRepository.save(lotDTO.toLot());
            logger.info("Начаты торги по лоту {}", lotDTO.getTitle());
        }
    }

    @Override
    public void stopBidding(int id) {

    }

    @Override
    public void createLot(CreateLot createLot) {

    }

    @Override
    public List<LotDTO> findLots(Status status, int page) {
        return null;
    }

    @Override
    public Resource getCSVFile() {
        return null;
    }


}
