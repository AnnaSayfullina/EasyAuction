package com.example.course_easyauction.dto;

import com.example.course_easyauction.model.Bid;
import com.example.course_easyauction.model.Lot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BidDTO {

    private int id;
    private String bidderName;
    private LocalDateTime bidderDate;
    private Lot lot;

    public BidDTO(String bidderName, Lot lot) {
        this.bidderName = bidderName;
        this.bidderDate = LocalDateTime.now();
        this.lot = lot;
    }

    public static BidDTO fromBid(Bid bid) {
        BidDTO bidDTO = new BidDTO();
        bidDTO.setId(bid.getId());
        bidDTO.setBidderName(bid.getBidderName());
        bidDTO.setBidderDate(bid.getBidderDate());
        bidDTO.setLot(bid.getLot());
        return bidDTO;
    }
    public Bid toBid(){
        Bid bid = new Bid();
        bid.setId(this.getId());
        bid.setBidderName(this.getBidderName());
        bid.setBidderDate(this.getBidderDate());
        bid.setLot(this.getLot());
        return bid;
    }
}
