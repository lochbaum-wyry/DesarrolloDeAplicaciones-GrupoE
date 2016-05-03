package helpers;



public class CalculationHelpers
{
    public static boolean rangesOverlap(Integer start1, Integer end1, Integer start2, Integer end2)
    {
        return Math.max(end1,end2) - Math.min(start1,start2) < (end1 - start1) + (end2 - start2);
    }
}
