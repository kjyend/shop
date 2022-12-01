package shopprj.shop.domain.entity.status;

public enum Point {
    one("1점"),two("2점"),three("3점"),four("4점"),five("5점");

    private final String point;

    Point(String point){
        this.point=point;
    }
    public String getPoint(){
        return point;
    }

}
