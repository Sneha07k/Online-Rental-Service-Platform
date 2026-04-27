package findmyroomie;

public class Application {
    private int id;
    private int seekerId;
    private int roomId;
    private String message;
    private String status;


    private String appliedOn;        
    private String moveInDate;       
    private int durationMonths;      

    public Application(int id, int seekerId, int roomId, String message, String status,
                       String appliedOn, String moveInDate, int durationMonths) {
        this.id = id;
        this.seekerId = seekerId;
        this.roomId = roomId;
        this.message = message;
        this.status = status;
        this.appliedOn = appliedOn;
        this.moveInDate = moveInDate;
        this.durationMonths = durationMonths;
    }


    public Application(int id, int seekerId, int roomId, String message, String status) {
        this(id, seekerId, roomId, message, status, "", "", 0);
    }


    public int getId() { return id; }
    public int getSeekerId() { return seekerId; }
    public int getRoomId() { return roomId; }
    public String getMessage() { return message; }
    public String getStatus() { return status; }
    public String getAppliedOn() { return appliedOn; }
    public String getMoveInDate() { return moveInDate; }
    public int getDurationMonths() { return durationMonths; }


    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Application #" + id +
                " | Room ID: " + roomId +
                " | Status: " + status +
                " | Move-in: " + moveInDate +
                " | Duration: " + durationMonths + " months" +
                " | Message: " + message;
    }
}


