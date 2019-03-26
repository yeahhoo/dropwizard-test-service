package potato.db;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import potato.api.PotatoBag;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static com.google.common.base.Preconditions.checkArgument;

/** Stores potato bags related data. The implementation is thread safe. */
public final class Storage {

    private final ConcurrentHashMap<String, PotatoBag> bagsMap;
    private final AtomicLong idGenerator;

    private Storage() {
        bagsMap = new ConcurrentHashMap<>();
        idGenerator = new AtomicLong(0);
    }

    /**
     * Returns list of random bags with size up to the given limit.
     *
     * @param limit number of the bags to return
     * @return found bags
     */
    public ImmutableList<PotatoBag> getBagsWithLimit(int limit) {
        return bagsMap.values().stream().limit(limit).collect(ImmutableList.toImmutableList());
    }

    /**
     * Returns bag by given id.
     *
     * @param id of the bag to look for
     * @return found bag
     */
    public PotatoBag getBag(String id) {
        return bagsMap.get(id);
    }

    /**
     * Updates existing bag.
     * @param entry  bag to be updated.
     *
     * @return updated bag
     */
    public PotatoBag updateBag(PotatoBag entry) {
        checkArgument(!StringUtils.isEmpty(entry.getId()), "Can not update bag with empty id");
        bagsMap.put(entry.getId(), entry);
        return entry;
    }

    /**
     * Stores new bag.
     * @param entry  bag to be stored.
     *
     * @return created bag
     */
    public PotatoBag putBag(PotatoBag entry) {
        checkArgument(StringUtils.isEmpty(entry.getId()), "Can not persist new bag with non empty id");
        String newId = String.valueOf(idGenerator.getAndIncrement());
        PotatoBag newEntry = new PotatoBag(newId, entry);
        bagsMap.put(newId, newEntry);
        return newEntry;
    }

    /**
     * Returns instance of the Storage.
     *
     * @return lazy-loaded instance of the storage.
     */
    public static Storage getInstance() {
        return Holder.INSTANCE;
    }

    /** Provides the lazy-loaded instance. */
    private static class Holder {
        private final static Storage INSTANCE = new Storage();
    }
}
