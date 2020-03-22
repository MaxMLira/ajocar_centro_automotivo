package br.com.ajocar.Ajocar.dto;

public class ClientDto {

    private String name;
    private String tel;
    private String address;
    private String distric;
    private String number;
    private String state;
    private String vehicle[];
    private String board[];
    private String km[];
    private String color[];
    private String fuel[];

    public String[] getVehicle() {
        return vehicle;
    }

    public void setVehicle(String[] vehicle) {
        this.vehicle = vehicle;
    }

    public String[] getBoard() {
        return board;
    }

    public void setBoard(String[] board) {
        this.board = board;
    }

    public String[] getKm() {
        return km;
    }

    public void setKm(String[] km) {
        this.km = km;
    }

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    public String[] getFuel() {
        return fuel;
    }

    public void setFuel(String[] fuel) {
        this.fuel = fuel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistric() {
        return distric;
    }

    public void setDistric(String distric) {
        this.distric = distric;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "ClientDto{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", distric='" + distric + '\'' +
                ", number='" + number + '\'' +
                ", state='" + state + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", board='" + board + '\'' +
                ", km='" + km + '\'' +
                ", color='" + color + '\'' +
                ", fuel='" + fuel + '\'' +
                '}';
    }
}
