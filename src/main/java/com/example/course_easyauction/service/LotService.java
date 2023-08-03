package com.example.course_easyauction.service;

import com.example.course_easyauction.dto.CreateLot;
import com.example.course_easyauction.dto.LotFullInfo;
import com.example.course_easyauction.model.Status;
import org.springframework.core.io.Resource;

public interface LotService {
    LotFullInfo getFullLot(int id);

    void startBidding(int id);

    void stopBidding(int id);

    void createLot(CreateLot createLot);

    List<LotDTO> findLots(Status status, int page);

    Resource getCSVFile();
}
