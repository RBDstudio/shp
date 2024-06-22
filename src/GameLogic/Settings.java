package GameLogic;

import java.awt.Graphics2D;

public class Settings 
{
    public static final float FrameRate = 60.0f;
    public static final long ThreadSaveTime = 1;
    
    public static final int LevelHeight = 8;
    public static final int LevelWidth = 8;
    public static final int CellSize = 24;
    public static final int Scale = 3;
    
    public static final String Name = "shax";
    public static final int BuffersCount = 3;
    public static final int ClearColor = 0xff000000;
    
    public static int CurrentTurn = 0;
    public static int WhiteCount = 0;
    public static int BlackCount = 0;
    public static boolean IsRunning;
    
    
    public static final float FrameInterval = GameTimer.Second / FrameRate;
    public static final int Width = (int)(LevelWidth * CellSize * Scale);
    public static final int Height = (int)(LevelHeight * CellSize * Scale);
}
