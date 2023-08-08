package com.example.course_easyauction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bidder implements Serializable {

    private String bidderName;
    private LocalDateTime bidderDate;

    public static Bidder fromBidDto(BidDTO bidDTO){
        Bidder bidderFirst = new Bidder();

        bidderFirst.setBidderName(bidDTO.getBidderName());
        bidderFirst.setBidderDate(bidDTO.getBidderDate());

        return bidderFirst;
    }

}
