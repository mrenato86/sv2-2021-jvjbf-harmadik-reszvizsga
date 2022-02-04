package skirental;

public class Equipment {

    private static final int CHILD_BOOT = 37;
    private static final int CHILD_SKIS = 120;

    private final int sizeOfBoot;
    private final int sizeOfSkis;

    public Equipment(int sizeOfSkis, int sizeOfBoot) {
        if (sizeOfBoot < 0 || sizeOfSkis < 0) {
            throw new IllegalArgumentException("Sizes cannot be negative");
        }
        this.sizeOfSkis = sizeOfSkis;
        this.sizeOfBoot = sizeOfBoot;
    }

    public int getSizeOfBoot() {
        return sizeOfBoot;
    }

    public int getSizeOfSkis() {
        return sizeOfSkis;
    }

    public boolean isBothRented() {
        return sizeOfBoot > 0 && sizeOfSkis > 0;
    }

    //Gyerekméretnél a 0 állapot nincs definiálva a feladatban.
    public boolean isChildSize() {
        return isBothRented() && sizeOfBoot <= CHILD_BOOT && sizeOfSkis <= CHILD_SKIS;
    }
}
