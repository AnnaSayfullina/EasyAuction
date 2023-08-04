package com.example.course_easyauction.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "bid")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bid {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "bidder_name")
    private String bidderName;

    @Column(name = "bidder_date_time")
    private LocalDateTime bidderDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "lot_id")
    private Lot lot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return id == bid.id && Objects.equals(bidderName, bid.bidderName) && Objects.equals(bidderDate, bid.bidderDate) && Objects.equals(lot, bid.lot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bidderName, bidderDate, lot);
    }
}
