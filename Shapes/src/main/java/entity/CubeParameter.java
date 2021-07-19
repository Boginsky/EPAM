package entity;

public class CubeParameter {
    private double volume;
    private double sideSquare;
    private double cubeSquare;
    private Point oppositePoint;

    public CubeParameter(double volume, double sideSquare, double cubeSquare, Point oppositePoint) {
        this.volume = volume;
        this.sideSquare = sideSquare;
        this.cubeSquare = cubeSquare;
        this.oppositePoint = oppositePoint;
    }

    public double getVolume() {
        return volume;
    }

    public double getSideSquare() {
        return sideSquare;
    }

    public double getCubeSquare() {
        return cubeSquare;
    }

    public Point getOppositePoint() {
        return oppositePoint;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(getClass().equals(other.getClass()))) {
            return false;
        }

        CubeParameter cubeParameter = (CubeParameter) other;
        if (Double.compare(cubeParameter.volume, volume) != 0) return false;
        if (Double.compare(cubeParameter.sideSquare, sideSquare) != 0) return false;
        if (Double.compare(cubeParameter.cubeSquare, cubeSquare) != 0) return false;
        return oppositePoint.equals(cubeParameter.oppositePoint);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(volume);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sideSquare);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cubeSquare);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return 31 * result + (((oppositePoint) == null) ? 0 : oppositePoint.hashCode());
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CubeParameter{");
        sb.append("volume=").append(volume);
        sb.append(", sideSquare=").append(sideSquare);
        sb.append(", cubeSquare=").append(cubeSquare);
        sb.append(", oppositePoint=").append(oppositePoint);
        sb.append('}');
        return sb.toString();
    }
    public static class Builder {

        private double volume;
        private double sideSquare;
        private double cubeSquare;
        private Point oppositePoint;

        public Builder setVolume(double volume) {
            this.volume = volume;
            return this;
        }

        public Builder setSideSquare(double sideSquare) {
            this.sideSquare = sideSquare;
            return this;
        }

        public Builder setCubeSquare(double cubeSquare) {
            this.cubeSquare = cubeSquare;
            return this;
        }

        public Builder setOppositePoint(Point oppositePoint) {
            this.oppositePoint = oppositePoint;
            return this;
        }

        public CubeParameter build() {
            return new CubeParameter(volume, sideSquare, cubeSquare, oppositePoint);
        }
    }
}
