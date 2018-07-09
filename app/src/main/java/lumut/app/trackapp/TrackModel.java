package lumut.app.trackapp;

/**
 * Created by macx on 09/07/18.
 */

public class TrackModel {

    int idtrack = 0;
    String date = "";
    long latitude = 0;
    long longitude = 0;
    long distance = 0;
    int ordering = 0;
    int idinspection = 0;

    public TrackModel(int idtrack, String date, long latitude, long longitude, long distance, int ordering, int idinspection) {
        this.idtrack = idtrack;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.ordering = ordering;
        this.idinspection = idinspection;
    }

    public int getIdtrack() {
        return idtrack;
    }

    public void setIdtrack(int idtrack) {
        this.idtrack = idtrack;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public int getIdinspection() {
        return idinspection;
    }

    public void setIdinspection(int idinspection) {
        this.idinspection = idinspection;
    }
}
