package by.boginsky.entity;

public class Solution extends AbstractCommonMedicine {

    private Solvent solvent;

    public Solution(){
    }

    public Solution(String nameOfMedicine, String groupOfMedicine, Manufacturer manufacturer, String analogs, Version version, Form form, Solvent solvent) {
        super(nameOfMedicine, groupOfMedicine, manufacturer, analogs, version, form);
        this.solvent = solvent;
    }

    public Solvent getSolvent() {
        return solvent;
    }

    public void setSolvent(Solvent solvent) {
        this.solvent = solvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || this.getClass() != o.getClass()){
            return false;
        }
        if(!super.equals(o)){
            return false;
        }
        Solution solution = (Solution) o;

        return solvent == solution.solvent;
    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        return 31 * result + ((solvent == null) ? 0 : solvent.hashCode());
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Solution{");
        sb.append("solvent=").append(solvent);
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Solution solution;

        private Builder() {
            solution = new Solution();
        }

        public Builder nameOfMedicineSet(String nameOfMedicine) {
            solution.setNameOfMedicine(nameOfMedicine);
            return this;
        }

        public Builder groupOfMedicineSet(String groupOfMedicine) {
            solution.setGroupOfMedicine(groupOfMedicine);
            return this;
        }

        public Builder manufacturerSet(Manufacturer manufacturer) {
            solution.setManufacturer(manufacturer);
            return this;
        }

        public Builder analogsSet(String analogs) {
            solution.setAnalogs(analogs);
            return this;
        }

        public Builder versionSet(Version version) {
            solution.setVersion(version);
            return this;
        }

        public Builder formSet(Form form) {
            solution.setForm(form);
            return this;
        }

        public Builder solventSet(Solvent solvent) {
            solution.setSolvent(solvent);
            return this;
        }

        public Solution build() {
            return solution;
        }
    }
}
