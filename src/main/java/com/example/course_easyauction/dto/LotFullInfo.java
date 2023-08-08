package com.example.course_easyauction.dto;

import com.example.course_easyauction.model.Bid;
import com.example.course_easyauction.model.Lot;
import com.example.course_easyauction.model.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LotFullInfo {

    private Integer id;
    private Status status;
    private String title;
    private String description;
    private int startPrice;
    private int bidPrice;
    private int currentPrice;
    private String lastBidderName;
    private LocalDateTime lastBidderDateTime;

    public static LotFullInfo fromLot(Lot lot) {
        LotFullInfo lotFullInfo = new LotFullInfo();
        lotFullInfo.setId(lot.getId());
        lotFullInfo.setStatus(lot.getStatus());
        lotFullInfo.setTitle(lot.getTitle());
        lotFullInfo.setDescription(lot.getDescription());
        lotFullInfo.setStartPrice(lot.getStartPrice());
        lotFullInfo.setBidPrice(lot.getBidPrice());
        if (lot.getBidList().size() != 0) {
            Bid bid = lot.getBidList().get(lot.getBidList().size()-1);
            lotFullInfo.setLastBidderName(bid.getBidderName());
            lotFullInfo.setLastBidderDateTime(bid.getBidderDate());
        }
        if(lot.getStatus() == Status.CREATED){
            lotFullInfo.setCurrentPrice(lot.getStartPrice());
        }else {
            lotFullInfo.setCurrentPrice(lot.getBidList().size() * lot.getBidPrice() + lot.getStartPrice());
        }
        return lotFullInfo;
    }

}
