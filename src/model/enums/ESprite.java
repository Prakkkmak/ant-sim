package model.enums;

public enum ESprite {
    NOTHING(0),
    WORKER(1),
    SOLDIER(2),
    SEXUAL(3),
    PREY(8),
    GARBAGE(9),
    QUEEN(10);
    
    private int value;
    private ESprite(int value) {
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
}
