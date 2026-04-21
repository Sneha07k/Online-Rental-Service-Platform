package findmyroomie;

public class Room {
    private int id;
    private String title;
    private String city;
    private double rent;
    private boolean furnished;
    private int hostId;
    private String description;


    private String imagePath;       
    private int bedrooms;           
    private int bathrooms;          
    private String roomType;        
    private boolean petsAllowed;
    private boolean smokingAllowed;
    private String amenities;       
    private String availableFrom;   

    public Room(int id, String title, String city, double rent, boolean furnished, int hostId,
                String description, String imagePath, int bedrooms, int bathrooms,
                String roomType, boolean petsAllowed, boolean smokingAllowed,
                String amenities, String availableFrom) {
        this.id = id;
        this.title = title;
        this.city = city;
        this.rent = rent;
        this.furnished = furnished;
        this.hostId = hostId;
        this.description = description;
        this.imagePath = imagePath;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.roomType = roomType;
        this.petsAllowed = petsAllowed;
        this.smokingAllowed = smokingAllowed;
        this.amenities = amenities;
        this.availableFrom = availableFrom;
    }


    public Room(int id, String title, String city, double rent, boolean furnished, int hostId, String description) {
        this(id, title, city, rent, furnished, hostId, description,
                "", 1, 1, "SINGLE", false, false, "", "");
    }


    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCity() { return city; }
    public double getRent() { return rent; }
    public boolean isFurnished() { return furnished; }
    public int getHostId() { return hostId; }
    public String getDescription() { return description; }
    public String getImagePath() { return imagePath; }
    public int getBedrooms() { return bedrooms; }
    public int getBathrooms() { return bathrooms; }
    public String getRoomType() { return roomType; }
    public boolean isPetsAllowed() { return petsAllowed; }
    public boolean isSmokingAllowed() { return smokingAllowed; }
    public String getAmenities() { return amenities; }
    public String getAvailableFrom() { return availableFrom; }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " | " + city + " | Rs." + rent
                + "/mo | " + bedrooms + "BHK | Furnished: " + (furnished ? "Yes" : "No")
                + " | Type: " + roomType;
    }
}


