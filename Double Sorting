import java.util.Arrays;
import java.util.Comparator;

public class DoubleSorting {
    record Pair<V, K>(V value, K key) {
    }
    //sdfgsfdg

    /**
     * Double sorting means to sort an array A according to values sorting key
     * given in array B compared by values given comparator C. You may use
     * Arrays.sort() on either sequence or instances of some class that you add
     * to this class internally.
     *
     * @param values     Array of items to be sorted, may be changed
     * @param keys       Array of items serving as values key, may be changed
     * @param comparator Comparator for Array keys.
     * @throws IllegalArgumentException unless both arrays contain the same
     *                                  number of arguments.
     */
    public static <S, T> void doubleSort(S[] values, T[] keys, Comparator<T> comparator) {
        if (values.length != keys.length){
            throw new IllegalArgumentException();
        }

        Pair<S, T>[] paarsammlung = new Pair[values.length];


        for (int i = 0; i < values.length; i++) {
            paarsammlung[i] = new Pair<>(values[i], keys[i]);
        }
        // irgendein Sortieralgorithmus evtl Bubblesort
        // das paar als solches wird nach den .keys sortiert
        for (int i = 0; i < paarsammlung.length; i++) {
            for (int j = 0; j < paarsammlung.length - i - 1; j++) {
                if (comparator.compare(paarsammlung[j].key, paarsammlung[j + 1].key) > 0) {
                    Pair<S, T> toSwitch = paarsammlung[j];
                    paarsammlung[j] = paarsammlung[j + 1];
                    paarsammlung[j + 1] = toSwitch;
                }
            }
        }
        // und hier werden die values and key arrays mit .value und .key sortiert
        for (int i = 0; i < paarsammlung.length; i++) {
            values[i] = paarsammlung[i].value; // value aus dem pairsammlung
            keys[i] = paarsammlung[i].key;     // keys von pairsammlung
        }

        Arrays.sort(keys);


    }
}
