package com.qinfengsa.algorithm.sort2.impl;

import com.qinfengsa.algorithm.sort2.ISort;

import java.util.Comparator;

/**
 * TimSort
 * @author: qinfengsa
 * @date: 2019/8/10 14:52
 */
public class TimSort implements ISort {

    private static final int MIN_MERGE = 32;

    @Override
    public int[] sort(int[] sortArray) {
        timSort(sortArray, 0, sortArray.length, null, 0,0);
        return sortArray;
    }

    private  int countRunAndMakeAscending(int[] a, int lo, int hi) {
        assert lo < hi;
        int runHi = lo + 1;
        if (runHi == hi) {
            return 1;
        }
        // Find end of run, and reverse range if descending
        if ( a[runHi++] < a[lo] ) { // Descending
            while (runHi < hi && a[runHi]< a[runHi - 1] ) {
                runHi++;
            }
            reverseRange(a, lo, runHi);
        } else {                              // Ascending
            while (runHi < hi && a[runHi]< a[runHi - 1]) {
                runHi++;
            }
        }

        return runHi - lo;
    }

    private   void reverseRange(int[] a, int lo, int hi) {
        hi--;
        while (lo < hi) {
            int t = a[lo];
            a[lo++] = a[hi];
            a[hi--] = t;
        }
    }


    private void binarySort(int[] a, int lo, int hi, int start) {
        assert lo <= start && start <= hi;
        if (start == lo) {
            start++;
        }

        for ( ; start < hi; start++) {
            int pivot = a[start];

            // Set left (and right) to the index where a[start] (pivot) belongs
            int left = lo;
            int right = start;
            assert left <= right;
            /*
             * Invariants:
             *   pivot >= all in [lo, left).
             *   pivot <  all in [right, start).
             */
            while (left < right) {
                int mid = (left + right) >>> 1;
                if ( pivot < a[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            assert left == right;

            /*
             * The invariants still hold: pivot >= all in [lo, left) and
             * pivot < all in [left, start), so pivot belongs at left.  Note
             * that if there are elements equal to pivot, left points to the
             * first slot after them -- that's why this sort is stable.
             * Slide elements over to make room for pivot.
             */
            int n = start - left;  // The number of elements to move
            // Switch is just an optimization for arraycopy in default case
            switch (n) {
                case 2:  a[left + 2] = a[left + 1];
                case 1:  a[left + 1] = a[left];
                    break;
                default: System.arraycopy(a, left, a, left + 1, n);
            }
            a[left] = pivot;
        }
    }

    private void timSort(int[] a, int lo, int hi,
                         int[] work, int workBase, int workLen) {
        assert  a != null && lo >= 0 && lo <= hi && hi <= a.length;

        int nRemaining  = hi - lo;
        if (nRemaining < 2) {
            return;  // Arrays of size 0 and 1 are always sorted
        }

        // If array is small, do a "mini-TimSort" with no merges
        if (nRemaining < MIN_MERGE) {
            int initRunLen = countRunAndMakeAscending(a, lo, hi );
            binarySort(a, lo, hi, lo + initRunLen );
            return;
        }

        /**
         * March over the array once, left to right, finding natural runs,
         * extending short natural runs to minRun elements, and merging runs
         * to maintain stack invariant.
         */
        /*TimSort<T> ts = new TimSort<>(a, c, work, workBase, workLen);
        int minRun = minRunLength(nRemaining);
        do {
            // Identify next run
            int runLen = countRunAndMakeAscending(a, lo, hi, c);

            // If run is short, extend to min(minRun, nRemaining)
            if (runLen < minRun) {
                int force = nRemaining <= minRun ? nRemaining : minRun;
                binarySort(a, lo, lo + force, lo + runLen, c);
                runLen = force;
            }

            // Push run onto pending-run stack, and maybe merge
            ts.pushRun(lo, runLen);
            ts.mergeCollapse();

            // Advance to find next run
            lo += runLen;
            nRemaining -= runLen;
        } while (nRemaining != 0);

        // Merge all remaining runs to complete sort
        assert lo == hi;
        ts.mergeForceCollapse();
        assert ts.stackSize == 1;*/
    }
}
