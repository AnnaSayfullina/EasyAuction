package com.example.course_easyauction.controller;

import com.example.course_easyauction.dto.Bidder;
import com.example.course_easyauction.dto.CreateLot;
import com.example.course_easyauction.dto.LotFullInfo;
import com.example.course_easyauction.model.Bid;
import com.example.course_easyauction.model.Status;
import com.example.course_easyauction.service.BidService;
import com.example.course_easyauction.dto.LotDTO;
import com.example.course_easyauction.service.LotService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lot")
//@RequiredArgsConstructor
@AllArgsConstructor
public class LotController {

    private final LotService lotService;

    private final BidService bidService;

    /**
     * Получить информацию о первом ставившем на лот. Возвращает первого ставившего на этот лот
     */
    @GetMapping("{id}/first")
    public Bidder getFirstBidder(@PathVariable int id){
        return bidService.getFirstBidder(id);
    }

    /**
     *Возвращает имя ставившего на данный лот наибольшее количество раз
     */
    @GetMapping("{id}/frequent")
    public Bidder getMostFrequentBidder(@PathVariable int id){
        return bidService.getMostFrequentBidder(id);
    }

    /**
     * Получить полную информацию о лоте
     */

    @GetMapping("{id}")
    public LotFullInfo getFullLot(@PathVariable int id){
        return lotService.getFullLot(id);
    }

    /** Начать торги по лоту
     Переводит лот в состояние "начато", которое позволяет делать ставки на лот.
     Если лот уже находится в состоянии "начато", то ничего не делает и возвращает 200     *
     */
    @PostMapping("{id}/start")
    public void startBidding(@PathVariable int id){
        lotService.startBidding(id);

    }

    /**
     * Сделать ставку по лоту
     * Создает новую ставку по лоту. Если лот в статусе CREATED или STOPPED, то должна вернутся ошибка
     */
    @PostMapping("{id}/bid")
    public void createBid(@PathVariable int id,
                          @RequestParam("Bidder name") String bidderName) {
        bidService.createBid(id, bidderName);

    }

    /**Остановить торги по лоту
     Переводит лот в состояние "остановлен", которое запрещает делать ставки на лот.
     Если лот уже находится в состоянии "остановлен", то ничего не делает и возвращает 200
     */
    @PostMapping("{id}/stop")
    public void stopBidding(@PathVariable int id){
        lotService.stopBidding(id);

    }

    /**Создает новый лот
     Метод создания нового лота,
     если есть ошибки в полях объекта лота - то нужно вернуть статус с ошибкой
     */

    @PostMapping("/")
    public void createLot(@RequestBody CreateLot createLot){
        lotService.createLot(createLot);

    }

    /**
     * Получить все лоты, основываясь на фильтре статуса и номере страницы
     *  Возвращает все записи о лотах без информации о текущей цене и победителе постранично.
     *  Если страница не указана, то возвращается первая страница.
     *  Номера страниц начинаются с 0. Лимит на количество лотов на странице - 10 штук.
     */
    @GetMapping("page")
    public List<LotDTO> findLots(@RequestParam(value = "status", defaultValue = "CREATED") Status status,
                                 @RequestParam(required = false, defaultValue = "0") int page) {
        return lotService.findLots(status, page);

    }

    /**
     * Экспортировать все лоты в файл CSV
     * Экспортировать все лоты в формате id,title,status,lastBidder,currentPrice в одном файле CSV
     */
    @GetMapping(value = "export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getCSVFile (){
        Resource resource = lotService.getCSVFile();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "LotsInfo.csv" + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }



}
