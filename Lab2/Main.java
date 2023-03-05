public class Main {
    public static void main(String[] args) {
        Location l1 = new Location();
        l1.setName("Iasi");
        l1.setType(locationType.CITY);
        l1.setCoordinates(50, 30);
        System.out.println(l1.toString());

        Road r1 = new Road();
        r1.setLength(20);
        r1.setSpeedLimit(50);
        r1.setType(roadType.EXPRESS);
        System.out.println(r1.toString());       
    }
}
