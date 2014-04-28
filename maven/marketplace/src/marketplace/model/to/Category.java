/**
 * 
 */
package marketplace.model.to;

/**
 * Transfer Object for Category.
 * 
 * @author Roman_Ten
 * 
 */
public class Category {
    private int categoryID;
    private String title;
    private int parentCategoryID;

    /**
     * Get Category ID.
     * 
     * @return the categoryID
     */
    public int getCategoryID() {
	return categoryID;
    }

    /**
     * Set Category ID.
     * 
     * @param categoryID
     *            the categoryID to set
     */
    public void setCategoryID(int categoryID) {
	this.categoryID = categoryID;
    }

    /**
     * Get Title.
     * 
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * Set title.
     * 
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * Get Parent Category ID.
     * 
     * @return the parentCategoryID
     */
    public int getParentCategoryID() {
	return parentCategoryID;
    }

    /**
     * Set Parent Category ID.
     * 
     * @param parentCategoryID
     *            the parentCategoryID to set
     */
    public void setParentCategoryID(int parentCategoryID) {
	this.parentCategoryID = parentCategoryID;
    }

}
