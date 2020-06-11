package fi.tuni.hoursapplication;

/* This class contains TimeEntry which consists of String date, int hours and int minutes

 */
public class TimeEntry {
    String date;
    int hours;
    int minutes;

    public TimeEntry(String date, int hours, int minutes) {
        setDate(date);
        setHours(hours);
        setMinutes(minutes);
    }
    public String toString() {
        String entry = date + " " + hours + " h " + minutes + " m";
        return entry;
    }
    public void setDate(String d) {
        date=d;
    }
    public String getDate() {
        return date;
    }
    public void setHours(int h) {
        hours = h;
    }
    public int getHours() {
        return hours;
    }
    public void setMinutes(int m) {
        minutes = m;
    }
    public int getMinutes() {
        return minutes;
    }
}
