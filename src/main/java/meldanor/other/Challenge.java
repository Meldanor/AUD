package meldanor.other;

/**
 * @author Meldanor
 * 
 */
public class Challenge {
    public static int lower_bound(int[] a, int x) {
        int left = 0, right = a.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (x == a[mid])
                return mid;
            else if (x < a[mid])
                right = mid - 1;
            else
                left = mid + 1;
        }
        System.out.println("left = " + left);
        System.out.println("right = " + right);
        return (left + right) / 2;
    }
}
