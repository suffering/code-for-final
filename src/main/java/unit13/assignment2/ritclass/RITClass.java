package unit13.assignment2.ritclass;

public class RITClass implements Comparable <RITClass> {
    private final String department;
    private final int number;
    private final int section;

    public RITClass (String courseCode) {
        String[] tokens = courseCode.split ("[- ]");
        department = tokens [0];
        number = Integer.parseInt (tokens[1]);
        section = Integer.parseInt (tokens[2]);
    }

    @Override
    public String toString() {
        return String.format ("%s %03d-%02d", department, number, section);
    }

    public String getDepartment() {
        return department;
    }

    public int getNumber() {
        return number;
    }

    public int getSection() {
        return section;
    }

    @Override
    public boolean equals (Object obj) {
        if (!(obj instanceof RITClass)) {
            return false;
        }

        RITClass other = (RITClass) obj;

        return department.equals(other.department) && number == other.number &&
                section == other.section;

    }

    @Override
    public int compareTo (RITClass o) {
        if (department.equals(o.department)) {
            if (number == o.number) {
                return section - o.section;
            }
            else {
                return number - o.number;
            }
        }
        else {
            return department.compareTo (o.department);
        }
    }
}
