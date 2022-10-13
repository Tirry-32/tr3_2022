package week03.prac02;

public interface Smallest {
    static int smallest(int x, int y, int z) {
        if (x <= y && x <= z) {
            return x;
        }
        else if (y <= x && y <= z) {
            return y;
        }
        else
            return z;
    }
}
