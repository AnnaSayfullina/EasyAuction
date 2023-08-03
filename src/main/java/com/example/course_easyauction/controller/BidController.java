package com.example.course_easyauction.controller;


import com.example.course_easyauction.model.Bid;
import com.example.course_easyauction.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bid")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;

    /**
     * Получить информацию о первом ставившем на лот. Возвращает первого ставившего на этот лот
     */
    @GetMapping("{id}/first")
    public Bid getFirstBidder(@PathVariable int id){
        return bidService.getFirstBidder(id);
    }


    /**
     *Возвращает имя ставившего на данный лот наибольшее количество раз
     */
    @GetMapping("{id}/frequent")
    public Bid getMostFrequentBidder(@PathVariable int id){
        return bidService.getMostFrequentBidder(id);
    }

    /**
     * Сделать ставку по лоту
     * Создает новую ставку по лоту. Если лот в статусе CREATED или STOPPED, то должна вернутся ошибка
     */
    @PostMapping("{id}/bid")
    public void createBid(@PathVariable int id,
                          @RequestParam("Bidder name") String bidderName) {
        return bidService.createBid(id, bidderName);

    }

}
