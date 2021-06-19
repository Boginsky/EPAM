package by.boginsky.entity;

public class Package {

    private String typeOfPackage;
    private int amountOfMedicine;
    private float price;

    public Package() {
    }

    public Package(String typeOfPackage, int amountOfMedicine, float price) {
        this.typeOfPackage = typeOfPackage;
        this.amountOfMedicine = amountOfMedicine;
        this.price = price;
    }

    public String getTypeOfPackage() {
        return typeOfPackage;
    }

    public void setTypeOfPackage(String typeOfPackage) {
        this.typeOfPackage = typeOfPackage;
    }

    public int getAmountOfMedicine() {
        return amountOfMedicine;
    }

    public void setAmountOfMedicine(int amountOfMedicine) {
        this.amountOfMedicine = amountOfMedicine;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Package that = (Package) o;
        if (typeOfPackage != that.typeOfPackage) {
            return false;
        }
        if (amountOfMedicine != that.amountOfMedicine) {
            return false;
        }
        if (price != that.price) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = typeOfPackage == null ? 0 : typeOfPackage.hashCode();
        result = 31 * result + amountOfMedicine;
        result = 31 * result + (int) price;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Package{");
        sb.append("typeOfPackage='").append(typeOfPackage).append('\'');
        sb.append(", amountOfMedicine=").append(amountOfMedicine);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Package aPackage;

        private Builder() {
            aPackage = new Package();
        }

        public Builder typeOfPackageSet(String typeOfPackage) {
            aPackage.setTypeOfPackage(typeOfPackage);
            return this;
        }

        public Builder amountOfMedicineSet(int amountOfMedicine){
            aPackage.setAmountOfMedicine(amountOfMedicine);
            return this;
        }

        public Builder priceSet(float price){
            aPackage.setPrice(price);
            return this;
        }

        public Package build() {
            return aPackage;
        }
    }
}
