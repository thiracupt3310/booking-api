package com.example.transaction.controller;

import com.example.transaction.data.TransactionRepository;
import com.example.transaction.model.Transaction;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionRestController {
    private TransactionRepository repository;

    public TransactionRestController(TransactionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Transaction> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{date}&{timeIndex}")
    public List<Transaction> getTransList(@PathVariable String date, @PathVariable int timeIndex){
        SimpleDateFormat sdfPath = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = new Date(sdfPath.parse(date).getTime());
            Calendar calendar = Calendar.getInstance();
            Date now = new Date(sdf.parse(sdf.format(new Date(calendar.getTimeInMillis()))).getTime());
            long diff = (date1.getTime() - now.getTime()) / (1000*60*60*24);
            Time time ;
            switch(timeIndex) {
                case 1:
                    time = Time.valueOf("08:00:00");
                    break;
                case 2:
                    time = Time.valueOf("09:00:00");
                    break;
                case 3:
                    time = Time.valueOf("10:00:00");
                    break;
                case 4:
                    time = Time.valueOf("11:00:00");
                    break;
                case 5:
                    time = Time.valueOf("12:00:00");
                    break;
                case 6:
                    time = Time.valueOf("13:00:00");
                    break;
                case 7:
                    time = Time.valueOf("14:00:00");
                    break;
                case 8:
                    time = Time.valueOf("15:00:00");
                    break;
                case 9:
                    time = Time.valueOf("16:00:00");
                    break;
                case 10:
                    time = Time.valueOf("17:00:00");
                    break;
                default:
                    return new ArrayList<>();
            }
            if (diff < 0 || diff > 4) {
                return new ArrayList<>();
            }
//            System.out.println('1');
//            System.out.println(time);

            if (repository.findByDateAndStartTime(date1, time).isEmpty()){
                List<Integer> rooms = Arrays.asList(702, 703, 704, 708, 709, 710);
                for (int room : rooms){
                    for (int i = 0 ; i < 10; i++){
                        Transaction transaction = new Transaction();
                        transaction.setRoom_id(room);
                        transaction.setDate(sdf.format(date1));
                        transaction.setStartTime(new Time(Time.valueOf("08:00:00").getTime() + (3600000 * i)));
                        transaction.setEndTime(new Time(Time.valueOf("08:00:00").getTime() + (3600000 * (i + 1))));
                        transaction.setStatus(0);
                        transaction.setUsername("");
                        create(transaction);
                    }
                }
            }
            return repository.findByDateAndStartTime(date1, time);
        } catch (ParseException e) {
            return null;
        }
    }
    @GetMapping("/{username}/")
    public Transaction getOne(@PathVariable String username){
        return repository.findByUsername(username);
    }
    @PostMapping
    public void create(@RequestBody Transaction transaction){
        repository.save(transaction);
        repository.flush();
    }

    @PutMapping
    public void update(@RequestBody Transaction transaction) {

        if (transaction.getStatus() == 1){
            transaction.setStatus(0);
            transaction.setUsername("");
        }else {
            transaction.setStatus(1);
        }
        repository.save(transaction);
        repository.flush();
    }


}
