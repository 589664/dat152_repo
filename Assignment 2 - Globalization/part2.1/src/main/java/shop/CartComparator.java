package shop;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;


public class CartComparator implements Comparator<Cart> {

    private Locale locale;

    /**
     * Creates a new EmployeeComparator with default locale.
     */
    public CartComparator() {
        this.locale = Locale.getDefault();
    }

    /**
     * Creates a new EmployeeComparator.
     *
     * @param locale locale
     */
    public CartComparator(final Locale locale) {
        this.locale = locale;
    }

    public final int compare(final Cart employee1, final Cart employee2) {
        Collator col = Collator.getInstance(locale);
        return col.compare(employee1.getCartId(), employee2.getCartId());
    }
}
