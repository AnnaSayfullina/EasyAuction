package com.example.course_easyauction.controller;

import com.example.course_easyauction.dto.CreateLot;
import com.example.course_easyauction.dto.LotFullInfo;
import com.example.course_easyauction.model.Bid;
import com.example.course_easyauction.model.Status;
import com.example.course_easyauction.service.BidService;
import com.example.course_easyauction.service.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lot")
@RequiredArgsConstructor
public class LotController {

    private final LotService lotService;

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
    public void getCSVFile (){

    }



}
