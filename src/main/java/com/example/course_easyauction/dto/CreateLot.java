package com.example.course_easyauction.dto;

import com.example.course_easyauction.model.Lot;
import com.example.course_easyauction.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLot implements Serializable {

    private String title;
    private String description;
    private int startPrice;
    private int bidPrice;

    public Lot toLot() {
        Lot lot = new Lot();
        lot.setStatus(Status.CREATED);
        lot.setTitle(this.getTitle());
        lot.setDescription(this.getDescription());
        lot.setStartPrice(this.getStartPrice());
        lot.setBidPrice(this.getBidPrice());
        return lot;
    }

    @Override
    public String toString() {
        return "CreateLot{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startPrice=" + startPrice +
                ", bidPrice=" + bidPrice +
                '}';
    }
}
