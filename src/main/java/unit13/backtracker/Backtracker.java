package unit13.backtracker;

/**
 * This class represents the classic recursive backtracking algorithm.
 * It has a solver that can take a valid configuration and return a
 * solution, if one exists.
 * 
 * @author GCCIS Faculty
 */
// faculty: this is a future version that does not declare a type parameter
// at the class level so that the same backtracker can be used with 
// different configurations.
// public class Backtracker {
public class Backtracker<C extends Configuration<C>> {
    /*
     * Should debug output be enabled?
     */
    private boolean debug;
    
    /**
     * Initialize a new backtracker
     * 
     * @param debug Is debugging output enabled?
     */
    public Backtracker(boolean debug) {
        this.debug = debug;
        if (this.debug) {
            System.out.println("backtracker.Backtracker debugging enabled...");
        }
    }
    
    /**
     * A utility routine for printing out various debug messages.
     * 
     * @param msg The type of config being looked at (current, goal, 
     *  successor, e.g.)
     * @param config The config to display
     */
    private void debugPrint(String msg, Configuration<?> config) {
        if (this.debug) {
            System.out.println(msg + ":\n" + config);
        }
    }
    
    /**
     * Try to find a solution, if one exists, for a given configuration.
     * 
     * @param config A valid configuration
     * @return A solution config, or null if no solution
     */
    // faculty: this is a future version that does not declare a type parameter
    // at the class level so that the same backtracker can be used with 
    // different configurations.
    // public <C extends Configuration<C>> C solve(C config) {
    public C solve(C config) {
        debugPrint("Current config", config);
        if (config.isGoal()) {
            debugPrint("\tGoal config", config);
            return config;
        } else {
            for (C child : config.getSuccessors()) {
                if (child.isValid()) {
                    debugPrint("\tValid successor", child);
                    C sol = solve(child);
                    if(sol != null) {
                        return sol;
                    }
                } else {
                    debugPrint("\tInvalid successor", child);
                }
            }
            // implicit backtracking happens here
        } 
        return null;
    }
}
