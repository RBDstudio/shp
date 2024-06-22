package GameObjects;


import GameGraphics.Sprites;
import GameLogic.Settings;

public class Level 
{
    public static int[][] LevelMap;
    
    public static void GenerateLevelMap()
    {
        LevelMap = new int[Settings.LevelWidth][Settings.LevelHeight];
        int Color = 0;
        for(int i = 0;i < Settings.LevelWidth;i++)
        {
            for(int j = 0;j < Settings.LevelHeight;j++)
            {
                LevelMap[i][j] = Color;
                if(j+1 < Settings.LevelHeight)
                {
                    if(Color == 0) {Color = 1;}
                    else {Color = 0;}
                }
            }
        }
    }
    
    public static void render()
    {
        for(int i = 0;i < Settings.LevelWidth;i++)
        {
            for(int j = 0;j < Settings.LevelHeight;j++)
            {
                if(LevelMap[i][j] == 0)
                {
                    Sprites.render(i, j, Sprites.WhiteCell);
                }
                if(LevelMap[i][j] == 1)
                {
                    Sprites.render(i, j, Sprites.BlackCell);
                }
            }
        }
    }
}
