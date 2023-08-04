package com.example.course_easyauction.service;

import com.example.course_easyauction.model.Bid;
import com.example.course_easyauction.repository.BidRepository;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService{

    private final BidRepository bidRepository;

    public BidServiceImpl(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public Bid getFirstBidder(int id) {
        return null;
    }

    @Override
    public Bid getMostFrequentBidder(int id) {
        return null;
    }

    @Override
    public void createBid(int id, String bidderName) {

    }


}
