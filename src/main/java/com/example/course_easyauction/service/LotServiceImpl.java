package com.example.course_easyauction.service;

import com.example.course_easyauction.dto.CreateLot;
import com.example.course_easyauction.dto.LotDTO;
import com.example.course_easyauction.dto.LotFullInfo;
import com.example.course_easyauction.model.Status;
import com.example.course_easyauction.repository.LotRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotServiceImpl implements LotService{

    private final LotRepository lotRepository;

    public LotServiceImpl(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }

    @Override
    public LotFullInfo getFullLot(int id) {
        return null;
    }

    @Override
    public void startBidding(int id) {

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
