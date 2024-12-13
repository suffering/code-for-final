package unit13.assignment2.ritclass;

import java.util.Comparator;

// Compare courses but not sections, in descending number order
public class CourseComparator implements Comparator <RITClass> {

    @Override
    public int compare(RITClass o1, RITClass o2) {
        if (o1.getDepartment().equals(o2.getDepartment())) {
            return o2.getNumber() - o1.getNumber();
        }
        else {
            return o1.getDepartment().compareTo (o2.getDepartment());
        }
    }
    
}
