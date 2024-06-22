package GameObjects;

import GameGraphics.Sprites;
import GameLogic.Settings;

public class Figures 
{
    public static int[][] FigureMap;
    
    public static void GenerateFigureMap()
    {
        FigureMap = new int[Settings.LevelWidth][Settings.LevelHeight];
        int Color = 0;
        for(int i = 0;i < Settings.LevelWidth;i++)
        {
            for(int j = 0;j < Settings.LevelHeight;j++)
            {
                if(j < 3 || j > Settings.LevelHeight - 4)
                FigureMap[i][j] = Color;
                if(j+1 < Settings.LevelHeight)
                {
                    if(Color == 0) {Color = 1;}
                    else {Color = 0;}
                }
            }
        }
        
        for(int i = 0;i < Settings.LevelWidth;i++)
        {
            for(int j = 0;j < 3;j++)
            {
                if(FigureMap[i][j] == 1) FigureMap[i][j] = 2;
            }
        }
        
        for(int i = 0;i < Settings.LevelWidth;i++)
        {
            for(int j = 0;j < Settings.LevelHeight;j++)
            {
                if(FigureMap[i][j] == 1 || FigureMap[i][j] == 3) Settings.WhiteCount++;
                if(FigureMap[i][j] == 2 || FigureMap[i][j] == 4) Settings.BlackCount++;
            }
        }
    }
    
    public static void render()
    {
        for(int i = 0;i < Settings.LevelWidth;i++)
        {
            for(int j = 0;j < Settings.LevelHeight;j++)
            {
                if(FigureMap[i][j] == 1)
                {
                    Sprites.render(i, j, Sprites.WhiteFigure);
                }
                
                if(FigureMap[i][j] == 2)
                {
                    Sprites.render(i, j, Sprites.BlackFigure);
                }
                
                if(FigureMap[i][j] == 3)
                {
                    Sprites.render(i, j, Sprites.WhiteFigureQ);
                }
                
                if(FigureMap[i][j] == 4)
                {
                    Sprites.render(i, j, Sprites.BlackFigureQ);
                }
            }
        }
    }
}
