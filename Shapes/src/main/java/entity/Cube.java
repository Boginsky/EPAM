package entity;

import observer.CubeEvent;
import observer.CubeObservable;
import observer.CubeObserver;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cube implements CubeObservable {
    private long cubeId;
    private Point point;
    private double sideLength;
    private final Set<CubeObserver> observers = new HashSet<>();

    public Cube(){
    }

    public Cube(long cubeId, Point point, double sideLength) {
        this.cubeId = cubeId;
        this.point = point;
        this.sideLength = sideLength;
    }

    public long getCubeId() {
        return cubeId;
    }

    public void setCubeId(long cubeId) {
        this.cubeId = cubeId;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public void attach(CubeObserver observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    @Override
    public void detach(CubeObserver observer) {
        if (observer != null) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        CubeEvent event = new CubeEvent(this);

        observers.forEach(observer -> observer.updateParameters(event));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Cube cube = (Cube) o;

        return cubeId == cube.cubeId && point == cube.point
                && sideLength == cube.sideLength;
        }

    @Override
    public int hashCode() {
        long temp;
        int result = 1;
        result = (int) (getCubeId() ^ (getCubeId() >>> 32));
        result = 31 * result + (getPoint() != null ? getPoint().hashCode() : 0);
        temp = Double.doubleToLongBits(getSideLength());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cube{");
        sb.append("cubeId=").append(cubeId);
        sb.append(", point=").append(point);
        sb.append(", sideLength=").append(sideLength);
        sb.append('}');
        return sb.toString();
    }
}
