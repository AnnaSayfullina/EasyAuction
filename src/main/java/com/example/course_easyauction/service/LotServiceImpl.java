package com.example.course_easyauction.service;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import com.example.course_easyauction.dto.CreateLot;
import com.example.course_easyauction.dto.LotDTO;
import com.example.course_easyauction.dto.LotForCSVFile;
import com.example.course_easyauction.dto.LotFullInfo;
import com.example.course_easyauction.exceptions.IncorrectLotInformation;
import com.example.course_easyauction.exceptions.LotNotFoundException;
import com.example.course_easyauction.model.Lot;
import com.example.course_easyauction.model.Status;
import com.example.course_easyauction.repository.LotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
        logger.info("Остановить торги по лоту по id = {}", id);

        LotDTO lotDTO = LotDTO.fromLot(lotRepository.findLotById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с id = {} не найден", id);
                    return new LotNotFoundException();
                }));


        lotDTO.setStatus(Status.STOPPED);
        lotRepository.save(lotDTO.toLot());
        logger.info("Лот {} перемещен в статус остановлен", lotDTO.getTitle());

    }

    @Override
    public void createLot(CreateLot createLot) {
        logger.info("Вызван метод создания нового лота {}", createLot);

        if(!createLot.getTitle().isEmpty() & !createLot.getDescription().isEmpty()
            & createLot.getStartPrice() != 0 & createLot.getBidPrice()!= 0) {
            Lot lot = createLot.toLot();
            lotRepository.save(lot);
            logger.info("Лот {} успешно создан", lot.getTitle());

        }else {
            logger.error("Лот {} передан с ошибкой", createLot);
            throw new IncorrectLotInformation();
        }
    }

    @Override
    public List<LotDTO> findLots(Status status, int page) {
        logger.info("Вызван метод получения лотов, основываясь на фильтре статуса и номере страницы");

        Pageable lotsOfConcretePage = PageRequest.of(page, 10);
        Page<Lot> pageOfLots = lotRepository.findAllByStatus(status, lotsOfConcretePage);
        List<LotDTO> lots = pageOfLots.stream()
                .map(LotDTO::fromLot)
                .collect(Collectors.toList());
        return lots;

    }

    @Override
    public Resource getCSVFile() {

        logger.info("Вызван метод экспортировать все лоты в файл CSV");


        List<LotForCSVFile> forCSVFile = lotRepository.findAll().stream()
                .map(LotForCSVFile::fromLot)
                .collect(Collectors.toList());

        String fileName = "lots.csv";

        try {
            FileWriter out = new FileWriter(fileName);

            try (CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT)) {
                csvPrinter.printRecord("id", "status", "title", "currentPrice", "lastBidderName");
                for (LotForCSVFile lot : forCSVFile) {
                    csvPrinter.printRecord(lot.getId(),
                            lot.getStatus(),
                            lot.getTitle(),
                            lot.getCurrentPrice(),
                            lot.getLastBidderName());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Resource resource = new PathResource(fileName);
        return resource;
    }


}
