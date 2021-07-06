public class DepartmentFactory {
    public enum DepartmentType {
        Finance,
        IT,
        Management,
        Marketing
    }
    public static Department createDepartment(DepartmentType departmentType) {
        switch(departmentType) {
            case Finance:
                return new Finance();
            case IT:
                return new IT();
            case Management:
                return new Management();
            case Marketing:
                return new Marketing();
        }
        throw new IllegalArgumentException("The departmentType" + departmentType + "is not recognized");
    }
}
