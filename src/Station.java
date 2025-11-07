// Station.java
public class Station {
    private int stationNumber;
    private boolean isAvailable;
    private int durationSeconds; 

    public Station(int stationNumber) {
        this.stationNumber = stationNumber;
        this.isAvailable = true;
        this.durationSeconds = 0;
    }

    public void bookStation(Paket paket) {
        this.isAvailable = false;
        this.durationSeconds = paket.getDurationInHours() * 3600; 
    }

    public String getFormattedDuration() {
        int h = durationSeconds / 3600;
        int m = (durationSeconds % 3600) / 60;
        int s = durationSeconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public int getStationNumber() { return stationNumber; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public void setDurationSeconds(int seconds) { durationSeconds = seconds; }
}