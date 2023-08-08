package com.example.course_easyauction.repository;

import com.example.course_easyauction.dto.Bidder;
import com.example.course_easyauction.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {

    @Query(value = "SELECT * FROM bid WHERE lot_id=:id ORDER BY bidder_date_time LIMIT 1", nativeQuery = true)
    Optional<Bid> findFirstBid(Integer id);

    @Query("SELECT new com.example.course_easyauction.dto.Bidder(b.bidderName, MAX (b.bidderDate)) " +
            "FROM Bid b WHERE b.lot.id =:id GROUP BY b.bidderName ORDER BY count(b.bidderName) DESC LIMIT 1")
    Optional<Bidder> findMostFrequentBidder(Integer id);




}
