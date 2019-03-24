package potato.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

/** Presents entity for info related to potato bags. */
public final class PotatoBag {

    private final String id;

    @Min(1)
    @Max(100)
    private final int capacity;

    private final Supplier supplier;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private final Date date;

    @Min(1)
    @Max(50)
    private final int price;

    public PotatoBag(String id, PotatoBag bag) {
        this.id = id;
        this.capacity = bag.capacity;
        this.supplier = bag.supplier;
        this.date = bag.date;
        this.price = bag.price;
    }

    @JsonCreator
    public PotatoBag(@JsonProperty("id") String id,
                     @JsonProperty("capacity") int capacity,
                     @JsonProperty("supplier") Supplier supplier,
                     @JsonProperty("date") Date date,
                     @JsonProperty("price") int price) {
        this.id = id;
        this.capacity = capacity;
        this.supplier = supplier;
        this.date = date;
        this.price = price;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public int getCapacity() {
        return capacity;
    }


    @JsonProperty
    public Supplier getSupplier() {
        return supplier;
    }

    @JsonProperty
    public Date getDate() {
        return date;
    }

    @JsonProperty
    public int getPrice() {
        return price;
    }

    /** Potatoes supplier. */
    public enum Supplier {

        @JsonProperty("De Coster")
        DE_COSTER("De Coster"),
        @JsonProperty("Owel")
        OWEL("Owel"),
        @JsonProperty("Patatas Ruben")
        POTATAS_RUBEN("Patatas Ruben"),
        @JsonProperty("Yunnan Spices")
        YUNNAN_SPICES("Yunnan Spices");

        private final String name;

        @JsonCreator
        Supplier(@JsonProperty("name") String name) {
            this.name = name;
        }

        @JsonValue
        public String getName() {
            return name;
        }
    }
}