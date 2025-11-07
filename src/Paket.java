import java.util.List;
public class Paket {
    private String name;
    private double price;
    private int durationHours;
    private String description;
    private List<String> facilities;
    private String priceNote;
    
    public Paket(String name, double price, int durationHours, String description, List<String> facilities, String priceNote) {
        this.name = name;
        this.price = price;
        this.durationHours = durationHours;
        this.description = description;
        this.facilities = facilities;
        this.priceNote = priceNote;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getDurationInHours() { return durationHours; }
    public String getDescription() { return description; }
    public List<String> getFacilities() { return facilities; }
    public String getPriceNote() { return priceNote; }
}