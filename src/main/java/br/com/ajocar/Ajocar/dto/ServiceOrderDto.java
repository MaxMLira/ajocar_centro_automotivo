package br.com.ajocar.Ajocar.dto;

public class ServiceOrderDto {

    private int car;
    private int price;
    private int workCuster;
    private int total;
    private String qtd[];
    private String value[];
    private String piece[];


    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public String[] getQtd() {
        return qtd;
    }

    public void setQtd(String[] qtd) {
        this.qtd = qtd;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    public String[] getPiece() {
        return piece;
    }

    public void setPiece(String[] piece) {
        this.piece = piece;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWorkCuster() {
        return workCuster;
    }

    public void setWorkCuster(int workCuster) {
        this.workCuster = workCuster;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
