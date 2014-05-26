import java.io.FileFilter;
import java.util.LinkedList;

import clids.ex2.filescript.actions.Action;
import clids.ex2.filescript.filters.NoFilter;
import clids.ex2.filescript.orders.Order;

/**
 * class who's instance hold filters, actions and order as described in the main command File.
 * each instance holds a single section commands.
 */
public class Section {
    
    private String comments = "";
    private String warnings = "";
    private Order order = new Order();
    private FileFilter filter = new NoFilter();
    private LinkedList<Action> actions;
    

    /**
     * performs the listed actions in listed order on particular files (filtered in required order).
     */
    public void activate() {
            // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    }
    
    /**
     * sets comments from parameter.
     * @param comments
     */
    public void addComments(String comments) {
        this.comments = comments;
    }
    
    /**
     * sets filters from parameter.
     * @param parseFilters
     */
    public void addFilter(FileFilter parseFilter) {
        filter = parseFilter;
    }
    
    /**
     * sets actions from parameter.
     * @param parseActions
     */
    public void addAction(Action parseActions) {
        actions.add(parseActions);
    }
    
    /**
     * sets orders from parameter.
     * @param order2
     */
    public void addOrder(Order order2) {
        order = order2;
    }
    
    /**
     * 
     * @param i
     */

    public void addWarning(int i) {
        
    }

}
