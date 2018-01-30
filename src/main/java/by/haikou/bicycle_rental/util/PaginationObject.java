package by.haikou.bicycle_rental.util;

import java.util.List;

/**
 * @param <T> the type of objects that need to be stored and divided into several pages.
 */
public class PaginationObject<T> {
    public static final int PER_PAGE = 8;
    public static final int DEFAULT_PAGE = 1;

    /**
     * Current page.
     */
    private int page;

    /**
     * Total number of pages for a given list of objects.
     */
    private int pageCount;

    /**
     * List of objects for the current page.
     */
    private List<T> elementList;

    public PaginationObject() {
    }

    public PaginationObject(int page, int pageCount, List<T> elementList) {
        this.page = page;
        this.pageCount = pageCount;
        this.elementList = elementList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getElementList() {
        return elementList;
    }

    public void setElementList(List<T> elementList) {
        this.elementList = elementList;
    }
}
