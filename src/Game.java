public class Game {
    private String title;
    private String genre;
    private int year;
    private double rating;
    private String director;
    private String description;
    private String posterPath;
    private String imdbUrl;

    public Game(String title, String genre, int year, double rating, String director, String description, String posterPath, String imdbUrl) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.director = director;
        this.description = description;
        this.posterPath = posterPath;
        this.imdbUrl = imdbUrl;
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
    public double getRating() { return rating; }
    public String getDirector() { return director; }
    public String getDescription() { return description; }
    public String getPosterPath() { return posterPath; }
    public String getImdbUrl() { return imdbUrl; }
}