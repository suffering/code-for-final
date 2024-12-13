package ritclass;

/**
 * Represents an RIT class with department, course number, and section number.
 */
public class RITClass implements Comparable<RITClass> {
    private final String department;
    private final int courseNumber;
    private final int sectionNumber;

    /**
     * Constructor to parse and initialize the class details.
     * @param code A string in the format "Department Course-Section" (e.g., "GCIS 124-02").
     */
    public RITClass(String code) {
        String[] parts = code.replace("-", " ").split("\\s+");
        this.department = parts[0];
        this.courseNumber = Integer.parseInt(parts[1]);
        this.sectionNumber = Integer.parseInt(parts[2]);
    }

    /**
     * Converts the class details back into the original formatted string.
     * @return A formatted string (e.g., "GCIS 124-02").
     */
    @Override
    public String toString() {
        return String.format("%s %d-%02d", department, courseNumber, sectionNumber);
    }

    /**
     * Natural order comparison by department, course number, and section number.
     * @param other Another RITClass to compare.
     * @return A negative, zero, or positive integer based on the comparison.
     */
    @Override
    public int compareTo(RITClass other) {
        int deptComp = department.compareTo(other.department);
        if (deptComp != 0) return deptComp;
        int courseComp = Integer.compare(courseNumber, other.courseNumber);
        if (courseComp != 0) return courseComp;
        return Integer.compare(sectionNumber, other.sectionNumber);
    }

    /**
     * Comparator for sorting RITClass in descending course number order within the same department.
     */
    public static class DescendingComparator implements java.util.Comparator<RITClass> {
        @Override
        public int compare(RITClass o1, RITClass o2) {
            int deptComp = o1.department.compareTo(o2.department);
            if (deptComp != 0) return deptComp;
            return Integer.compare(o2.courseNumber, o1.courseNumber);
        }
    }
}
