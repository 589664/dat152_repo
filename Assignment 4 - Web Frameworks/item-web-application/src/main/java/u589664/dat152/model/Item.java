package u589664.dat152.model;

import java.io.Serializable;


public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private Double price;
    private String description;

    public Item() {
    }

    public Item(final String id) {
        this.id = id;
    }

    public Item(final String name, final Double price, final String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public final String getId() {
        return id;
    }

    public final void setId(final String id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final Double getPrice() {
        return price;
    }

    public final void setPrice(final Double price) {
        this.price = price;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public final int hashCode() {
        return getId().hashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;
        return this.id.equals(other.id);
    }

}
