package evolution;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Vector2d {
    private final int x;
    private final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d substract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Vector2d that))
            return false;
        return that.x == this.x && that.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public Vector2d opposite() {
        return new Vector2d(this.x * (-1), this.y * (-1));
    }

    public Vector2d vectorInMap(Vector2d mapEnd) {
        int x = this.x;
        int y = this.y;
        if (x > mapEnd.x) {
            x = 0;
        } else if (x < 0) {
            x = mapEnd.x;
        }
        if (y > mapEnd.y) {
            y = 0;
        } else if (y < 0) {
            y = mapEnd.y;
        }
        return new Vector2d(x, y);
    }

    public Vector2d randomInMap() {
        int x = ThreadLocalRandom.current().nextInt(0, this.x + 1);
        int y = ThreadLocalRandom.current().nextInt(0, this.y + 1);
        return new Vector2d(x, y);
    }
}
