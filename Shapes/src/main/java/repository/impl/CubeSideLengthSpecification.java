package repository.impl;

import entity.Cube;
import repository.CubeSpecification;

public class CubeSideLengthSpecification implements CubeSpecification {
        private final double minSideLength;
        private final double maxSideLength;

        public CubeSideLengthSpecification(double minSideLength, double maxSideLength) {
            this.minSideLength = minSideLength;
            this.maxSideLength = maxSideLength;
        }

        public static CubeSideLengthSpecification lessThen(double value) {
            return new CubeSideLengthSpecification(0, value);
        }

        public static CubeSideLengthSpecification moreThen(double value) {
            return new CubeSideLengthSpecification(value, Double.MAX_VALUE);
        }

        public static CubeSideLengthSpecification range(double minSideLength, double maxSideLength) {
            return new CubeSideLengthSpecification(minSideLength, maxSideLength);
        }

        @Override
        public boolean test(Cube cube) {
            double sideLength = cube.getSideLength();
            return ((sideLength >= minSideLength) && (sideLength <= maxSideLength));
        }
    }
