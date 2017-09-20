package com.ujujzk.vrgweather.util;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ujujzk on 19.09.2017
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

public class Foo {

    public static void main() {

        int[] sequece;
        List<int[]> test = new ArrayList<>();
        List<Boolean> testAns = new ArrayList<>();

        sequece = new int[]{1, 2, 3, 1, 2};
        test.add(sequece);
        testAns.add(false);

        sequece = new int[]{1, 2, 3, 4, 5};
        test.add(sequece);
        testAns.add(true);

        sequece = new int[]{1, 2, 1, 2, 1};
        test.add(sequece);
        testAns.add(false);

        sequece = new int[]{3, 4, 1, 1, 1};
        test.add(sequece);
        testAns.add(false);

        sequece = new int[]{4, 3, 2, 3, 4};
        test.add(sequece);
        testAns.add(false);

        sequece = new int[]{1, 2, 1, 3, 4};
        test.add(sequece);
        testAns.add(true);

        sequece = new int[]{1, 2, 2, 3, 4};
        test.add(sequece);
        testAns.add(true);

        sequece = new int[]{1, 2, 2, 2, 3, 4};
        test.add(sequece);
        testAns.add(false);

        sequece = new int[]{1, 2, 3, 2, 2};
        test.add(sequece);
        testAns.add(false);

        sequece = new int[]{1, 2, 1, 2};
        test.add(sequece);
        testAns.add(false);


        sequece = new int[]{10, 1, 2, 3, 4, 3};
        test.add(sequece);
        testAns.add(false);


        sequece = new int[]{10, 1, 2};
        test.add(sequece);
        testAns.add(true);


        sequece = new int[]{4, 1, 2, 3, 4};
        test.add(sequece);
        testAns.add(true);


        sequece = new int[]{1, 2, 10, 3, 4};
        test.add(sequece);
        testAns.add(true);


        sequece = new int[]{10, 1, 2, 3, 4};
        test.add(sequece);
        testAns.add(true);


        sequece = new int[]{3, 5, 67, 98, 3};
        test.add(sequece);
        testAns.add(true);


        for (int i = 0; i < test.size(); i++) {
            Log.w("TAG", Arrays.toString(test.get(i)));
            Log.w("TAG", almostIncreasingSequence(test.get(i)) + " -> " + testAns.get(i) + "\n");
            Log.w("TAG", " ");
        }


    }


    private static boolean almostIncreasingSequence(int[] sequence) {

        if (sequence.length < 2) {
            throw new IllegalArgumentException("Must be two or more elements");
        }

        if (increasingSequence(sequence)) {
            return true;
        }

        int[] subSequence = new int[sequence.length - 1];

        for (int i = 0; i < sequence.length; i++) {
            for (int j = 0; j < subSequence.length; j++) {
                subSequence[j] = sequence[j >= i ? j + 1 : j];
                if (increasingSequence(subSequence)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean increasingSequence(int[] sequence) {

        for (int i = 0; i < sequence.length - 1; i++) {
            if (sequence[i] >= sequence[i + 1]) {
                return false;
            }
        }
        return true;
    }


}
