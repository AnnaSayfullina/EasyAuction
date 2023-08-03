package com.example.course_easyauction.controller;

import com.example.course_easyauction.dto.CreateLot;
import com.example.course_easyauction.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lot")
@RequiredArgsConstructor
public class LotController {

    private final LotService lotService;

    /**
     * Получить информацию о первом ставившем на лот. Возвращает первого ставившего на этот лот
     */
    @GetMapping("{id}/first")
    public Bidder getFirstBidder(@PathVariable int id){
        return null;
    }

    /**
     *Возвращает имя ставившего на данный лот наибольшее количество раз
     */
    @GetMapping("{id}/frequent")
    public Bidder getMostFrequentBidder(@PathVariable int id){
        return null;
    }

    /**
     * Получить полную информацию о лоте
     */

    @GetMapping("{id}")
    public LotFullInfo getFullLot(@PathVariable int id){
        return null;
    }

    /** Начать торги по лоту
     Переводит лот в состояние "начато", которое позволяет делать ставки на лот.
     Если лот уже находится в состоянии "начато", то ничего не делает и возвращает 200     *
     */
    @PostMapping("{id}/start")
    public void startBidding(@PathVariable int id){

    }

    /**
     * Сделать ставку по лоту
     * Создает новую ставку по лоту. Если лот в статусе CREATED или STOPPED, то должна вернутся ошибка
     */
    @PostMapping("{id}/bid")
    public void createBid(@PathVariable int id){

    }

    /**Остановить торги по лоту
     Переводит лот в состояние "остановлен", которое запрещает делать ставки на лот.
     Если лот уже находится в состоянии "остановлен", то ничего не делает и возвращает 200
     */
    @PostMapping("{id}/stop")
    public void stopBidding(@PathVariable int id){

    }

    /**Создает новый лот
     Метод создания нового лота,
     если есть ошибки в полях объекта лота - то нужно вернуть статус с ошибкой
     */

    @PostMapping("/")
    public void createLot(@RequestBody CreateLot createLot){

    }

    /**
     * Получить все лоты, основываясь на фильтре статуса и номере страницы
     *  Возвращает все записи о лотах без информации о текущей цене и победителе постранично.
     *  Если страница не указана, то возвращается первая страница.
     *  Номера страниц начинаются с 0. Лимит на количество лотов на странице - 10 штук.
     */
    @GetMapping("page")
    public void findLots(@RequestParam(value = "status", defaultValue = "CREATED") Status status,
                         @RequestParam(required = false, defaultValue = "0") int page) {

    }

    /**
     * Экспортировать все лоты в файл CSV
     * Экспортировать все лоты в формате id,title,status,lastBidder,currentPrice в одном файле CSV
     */
    @GetMapping(value = "export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getCSVFile (){

    }



}
