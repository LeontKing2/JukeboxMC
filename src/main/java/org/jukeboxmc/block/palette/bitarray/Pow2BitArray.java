package org.jukeboxmc.block.palette.bitarray;

import lombok.Getter;
import org.jukeboxmc.util.Utils;

import java.util.Arrays;

@Getter
public final class Pow2BitArray implements BitArray {

    private final int[] words;
    private final BitArrayVersion version;
    private final int size;

    Pow2BitArray(BitArrayVersion version, int size, int[] words) {
        this.size = size;
        this.version = version;
        this.words = words;
        int expectedWordsLength = Utils.ceil((float) size / version.entriesPerWord);
        if(words.length != expectedWordsLength) {
            throw new IllegalArgumentException("Invalid length given for storage, got: " + words.length +
                    " but expected: " + expectedWordsLength);
        }
    }

    public void set(int index, int value) {
        final int bitIndex = index * this.version.bits;
        final int arrayIndex = bitIndex >> 5;
        final int offset = bitIndex & 31;
        this.words[arrayIndex] = this.words[arrayIndex] & ~(this.version.maxEntryValue << offset) | (value & this.version.maxEntryValue) << offset;
    }

    public int get(int index) {
        final int bitIndex = index * this.version.bits;
        final int arrayIndex = bitIndex >> 5;
        final int wordOffset = bitIndex & 31;
        return this.words[arrayIndex] >>> wordOffset & this.version.maxEntryValue;
    }

    @Override
    public BitArray copy() {
        return new Pow2BitArray(this.version, this.size, Arrays.copyOf(this.words, this.words.length));
    }

}