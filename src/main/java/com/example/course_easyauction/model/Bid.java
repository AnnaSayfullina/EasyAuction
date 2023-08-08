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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lot_id")
    private Lot lot;

    public Bid(String bidderName) {
        this.bidderName = bidderName;
        this.bidderDate = LocalDateTime.now();

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return id == bid.id && Objects.equals(bidderName, bid.bidderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bidderName);
    }
}
