package sample;

import javafx.beans.property.*;
import java.time.LocalDate;
import java.time.LocalTime;


public class CallRecord {
    private IntegerProperty num;
    private StringProperty from;
    private StringProperty to;
    private ObjectProperty<LocalDate> date;
    private ObjectProperty<LocalTime> time;
    private IntegerProperty duration;

    public CallRecord(){};

    public CallRecord(int num, String from, String to, int day, int month, int year, int hour, int minutes, int duration){
        this.num = new SimpleIntegerProperty(num);
        this.from = new SimpleStringProperty(from);
        this.to = new SimpleStringProperty(to);
        this.date = new SimpleObjectProperty<LocalDate>(LocalDate.of(year, month, day));
        this.time = new SimpleObjectProperty<LocalTime>(LocalTime.of(hour, minutes));
        this.duration = new SimpleIntegerProperty(duration);
    }

    public IntegerProperty prNum(){return num;};
    public int getNum(){return num.get();};
    public void setNum(int num){this.num.set(num);};

    public StringProperty prFrom(){return from;};
    public String getFrom(){return from.get();};
    public void setFrom(String from){this.from.set(from);};

    public StringProperty prTo(){return to;};
    public String getTo(){return to.get();};
    public void setTo(String to){this.to.set(to);};

    public ObjectProperty<LocalDate> prDate(){return date;};
    public LocalDate getDate(){return date.get();};
    public void setDate(LocalDate date){this.date.set(date);};

    public ObjectProperty<LocalTime> prTime(){return time;};
    public LocalTime getTime(){return time.get();};
    public void setTime(LocalTime time){this.time.set(time);};

    public IntegerProperty prDuration(){return duration;};
    public int getDuration(){return duration.get();};
    public void setDuration(int duration){this.duration.set(duration);};
}
