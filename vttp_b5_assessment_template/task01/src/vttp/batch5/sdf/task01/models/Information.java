package vttp.batch5.sdf.task01.models;

public class Information {
    private int total; 
    private String month;
    private String season;
    private String day;
    private String weather;
    private boolean holiday;
    
    public Information(int total, String month, String season, String day, String weather, boolean holiday) {
        this.total = total;
        this.month = month;
        this.season = season;
        this.day = day;
        this.weather = weather;
        this.holiday = holiday;
    }

    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getWeather() {
        return weather;
    }
    public void setWeather(String weather) {
        this.weather = weather;
    }

    public boolean isHoliday() {
        return holiday;
    }

    public void setHoliday(boolean holiday) {
        this.holiday = holiday;
    }
    
    
}