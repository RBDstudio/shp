package GameLogic;


public class GameTimer
{
    public static final long Second = 1000000000l;
    
    public static long LastTime;
    public static long NowTime;
    public static long Diff;
    
    
    
    public static void UpdateDiff()
    {
        Diff = NowTime - LastTime;
    }
    
    public static void UpdateNowTime()
    {
        NowTime = GetTime();
    }
    
    public static void SwapTime()
    {
        UpdateNowTime();
        UpdateDiff();
        LastTime = NowTime;
    }
    
    public static long GetTime()
    {
        return System.nanoTime();
    }
}
