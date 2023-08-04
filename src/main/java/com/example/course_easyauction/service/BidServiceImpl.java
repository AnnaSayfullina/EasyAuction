package com.example.course_easyauction.service;

import com.example.course_easyauction.dto.BidDTO;
import com.example.course_easyauction.dto.Bidder;
import com.example.course_easyauction.dto.LotDTO;
import com.example.course_easyauction.exceptions.IncorrectLotStatus;
import com.example.course_easyauction.exceptions.LotNotFoundException;
import com.example.course_easyauction.model.Bid;
import com.example.course_easyauction.model.Lot;
import com.example.course_easyauction.model.Status;
import com.example.course_easyauction.repository.BidRepository;
import com.example.course_easyauction.repository.LotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidServiceImpl implements BidService{

    Logger logger = LoggerFactory.getLogger(BidServiceImpl.class);
    private final BidRepository bidRepository;
    private final LotRepository lotRepository;

    public BidServiceImpl(BidRepository bidRepository, LotRepository lotRepository) {
        this.bidRepository = bidRepository;
        this.lotRepository = lotRepository;
    }

    @Override
    public Bidder getFirstBidder(int id) {
        logger.info("Вызван метод получения информации о первом ставившем на лот по id = {}", id);

        BidDTO bidDTO = BidDTO.fromBid(bidRepository.findFirstBid(id)
                .orElseThrow(() -> {
                    logger.error("Лот с id = {} не найден", id);
                    return new LotNotFoundException();
                }));

        Bidder bidderFirst = Bidder.fromBidDto(bidDTO);
        logger.info("получен первый ставивший на лот {} ", bidderFirst);


        return  bidderFirst;
    }

    @Override
    public Bidder getMostFrequentBidder(int id) {
        logger.info("Вызван метод получения информации о ставившем наибольшее количество раз и дата его последней ставки на лот по id = {}", id);

        Bidder bidderMax = bidRepository.findMostFrequentBidder(id)
                .orElseThrow(() -> {
                    logger.error("Лот с id = {} не найден", id);
                    return new LotNotFoundException();
                });
        logger.info("получен ставивший наибольшее количество раз на лот {} ", bidderMax);

        return  bidderMax;
    }

//    Попытаться обернуть в ДТО
    @Override
    public void createBid(int id, String bidderName) {
        logger.info("Сделать ставку по лоту по id = {}", id);

        Lot lot = lotRepository.findLotById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с id = {} не найден", id);
                    return new LotNotFoundException();
                });
        if(lot.getStatus() == Status.STARTED){
            Bid bid = new Bid(bidderName);
            bid.setLot(lot);

            bidRepository.save(bid);

            List<Bid> bidList = lot.getBidList();
            bidList.add(bid);
            lot.setBidList(bidList);

            lotRepository.save(lot);

            logger.info("Получена новая ставка по лоту {} от {}", lot.getTitle(), bidderName);

        }else {
            logger.error("Лот с id = {} в неверном статусе", id);
            throw new IncorrectLotStatus();

        }




    }


}
