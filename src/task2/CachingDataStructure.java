package task2;


import java.util.Arrays;

interface TimeProvider {
    long getMillis();
}

public class CachingDataStructure<K, V, ET> {

    private int maxSize;
    private int size;
    private long minTimeToLeave;
    private TimeProvider timeProvider;
    private int DEFAULT_CAPACITY = 16;
    @SuppressWarnings("unchecked")
    private MyEntry<K, V, ET>[] values = new MyEntry[DEFAULT_CAPACITY];

    CachingDataStructure(TimeProvider timeProvider, int maxSize) {
        this.timeProvider = timeProvider;
        this.maxSize = maxSize;
    }

    public void put(K key, V value, long timeToLeaveInMilliseconds) {


        if (minTimeToLeave < timeToLeaveInMilliseconds)
            if(size > maxSize) {
                for(int j = 0 ; j < size ; j++){
                    if(values[j].getExpireTime() == minTimeToLeave)
                        remove(values[j].getKey());
                }
            }
            else if (size < maxSize) {
                boolean insert = true;
                for (int i = 0; i < size; i++) {
                    if (values[i].getExpireTime() < timeProvider.getMillis()) {
                        if (values[i].getKey().equals(key)) {
                            values[i].setValue(value);
                            values[i].setExpireTime(timeToLeaveInMilliseconds);
                            insert = false;
                        }
                    } else {
                        remove(values[i].getKey());
                    }
                }
                if (insert) {
                    ensureCapa();
                    values[size++] = new MyEntry<K, V, ET>(key, value);
                }
            }
        if ((minTimeToLeave == 0 || minTimeToLeave > timeToLeaveInMilliseconds))
            minTimeToLeave = timeToLeaveInMilliseconds;
    }

    public V get(String key) {
        for (int i = 0; i < size; i++) {
            if (values[i] != null) {
                if (values[i].getKey().equals(key) && values[i].getExpireTime() < timeProvider.getMillis()) {
                    return values[i].getValue();
                }
            }
        }
        return null;
    }

    public int size() {
        for (int i = 0; i < size; i++) {
            if (values[i] != null) {
                if (values[i].getExpireTime() > timeProvider.getMillis()) {
                    remove(values[i].getKey());
                }
            }
        }
        return size;
    }

    public void remove(K key) {
        for (int i = 0; i < size; i++) {
            if (values[i].getKey().equals(key)) {
                values[i] = null;
                size--;
                condenseArray(i);
            }
        }
    }

    private void condenseArray(int start) {
        for (int i = start; i < size; i++) {
            values[i] = values[i + 1];
        }
    }

    private void ensureCapa() {
        if (size == values.length) {
            int newSize = values.length * 2;
            values = Arrays.copyOf(values, newSize);
        }
    }

}
