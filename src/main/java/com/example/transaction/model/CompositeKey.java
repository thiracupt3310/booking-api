package com.example.transaction.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class CompositeKey implements Serializable {
    private int room_id;
    private Date date;
    private Time startTime;
}
