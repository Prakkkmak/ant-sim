package model.enums;

public enum ESprite {
    NOTHING(0),
    WORKER(1),
    SOLDIER(2),
    SEXUAL(3),
    QUEEN(10),
    GARBAGE(20);
    private int value;
    private ESprite(int value) {
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
}
