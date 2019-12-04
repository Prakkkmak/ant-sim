package main.model.enums;

public enum EType {
    NOTHING(0),
    EGG(1),
    LARVA(2),
    NYMPH(3),
    WORKER(4),
    SOLDIER(5),
    SEXUAL(6),
    PREY(8),
    GARBAGE(9),
    ANTHILL(10);

    private int value;

    EType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
