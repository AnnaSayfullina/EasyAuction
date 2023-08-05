package com.example.course_easyauction.dto;

import com.example.course_easyauction.model.Bid;
import com.example.course_easyauction.model.Lot;
import com.example.course_easyauction.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotForCSVFile {

    private int id;
    private String title;
    private Status status;
    private String lastBidderName;
    private int currentPrice;

    public static LotForCSVFile fromLot (Lot lot){

    LotForCSVFile forCSVFile = new LotForCSVFile();
        forCSVFile.setId(lot.getId());
        forCSVFile.setStatus(lot.getStatus());
        forCSVFile.setTitle(lot.getTitle());
        if (lot.getBidList().size() != 0) {
            Bid bid = lot.getBidList().get(lot.getBidList().size()-1);
            forCSVFile.setLastBidderName(bid.getBidderName());
        }
        if(lot.getStatus() == Status.CREATED){
        forCSVFile.setCurrentPrice(lot.getStartPrice());
        }else {
            forCSVFile.setCurrentPrice(lot.getBidList().size() * lot.getBidPrice() + lot.getStartPrice());
        }
        return forCSVFile;
    }

    @Override
    public String toString() {
        return "LotForCSVFile{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", lastBidderName='" + lastBidderName + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
