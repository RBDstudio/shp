package GameLogic;

import GameGraphics.GameFrame;
import GameGraphics.Sprites;
import GameObjects.Figures;


public class Selection 
{
    public static int SelectedX;
    public static int SelectedY;
    
    public static int[][] SelectionMap;
    
    public static void GenerateSelection()
    {
        SelectedX = -1;
        SelectedY = -1;
        SelectionMap = new int[Settings.LevelWidth][Settings.LevelHeight];
    }
    
    public static void update()
    {
        if(GameFrame.MouseIsClicked)
        {
            int x = GameFrame.MouseX/Settings.CellSize/Settings.Scale;
            int y = GameFrame.MouseY/Settings.CellSize/Settings.Scale;
            if((Figures.FigureMap[x][y] == 1 || Figures.FigureMap[x][y] == 3) && Settings.CurrentTurn == 0)
            {
                SelectedX = x;
                SelectedY = y;
                CulcMove();
                return;
            }
            if((Figures.FigureMap[x][y] == 2 || Figures.FigureMap[x][y] == 4) && Settings.CurrentTurn == 1)
            {
                SelectedX = x;
                SelectedY = y;
                CulcMove();
                return;
            }
            
            if(SelectionMap[x][y] == 1)
            {
                boolean IsDeleted;
                GameFrame.ChangeName("+");
                Figures.FigureMap[x][y] = Figures.FigureMap[SelectedX][SelectedY];
                if(Figures.FigureMap[x][y] == 1 && y == 0) Figures.FigureMap[x][y] = 3;
                if(Figures.FigureMap[x][y] == 2 && y == Settings.LevelHeight-1) Figures.FigureMap[x][y] = 4;
                Figures.FigureMap[SelectedX][SelectedY] = 0;
                IsDeleted = CheckForDelete(x,y);
                SelectionMap = new int[Settings.LevelWidth][Settings.LevelHeight];
                if(!IsDeleted)
                {
                    if(Settings.CurrentTurn == 0) Settings.CurrentTurn = 1; else Settings.CurrentTurn = 0;
                }
                
                SelectedX = -1;
                SelectedY = -1;
            }
            if(Settings.CurrentTurn == 0) GameFrame.ChangeName("Ходят белые");
            if(Settings.CurrentTurn == 1) GameFrame.ChangeName("Ходят чёрные");
            GameFrame.MouseIsClicked = false;
        }
    }
    
    public static void render()
    {
        Sprites.render(SelectedX, SelectedY, Sprites.Selected);
        for(int i = 0;i < Settings.LevelWidth;i++)
        {
            for(int j = 0;j < Settings.LevelHeight;j++)
            {
                if(SelectionMap[i][j] == 1)
                {
                    Sprites.render(i, j, Sprites.GreenCell);
                }
            }
        }
    }
    
    public static boolean CheckForDelete(int x, int y)
    {
        boolean Result = false;
        
        int bX = SelectedX;
        int bY = SelectedY;
        while(bX != x && bY != y)
        {
            if(bX < x) bX++; else bX--;
            if(bY < y) bY++; else bY--;
            if(Figures.FigureMap[bX][bY] != 0)
            {
                if(Figures.FigureMap[bX][bY] != Settings.CurrentTurn+1 && Figures.FigureMap[bX][bY] != Settings.CurrentTurn+3)
                {
                    if(Figures.FigureMap[bX][bY] == 1 || Figures.FigureMap[bX][bY] == 3) Settings.WhiteCount--;
                    if(Figures.FigureMap[bX][bY] == 2 || Figures.FigureMap[bX][bY] == 4) Settings.BlackCount--;
                    if(Settings.WhiteCount <=0)
                    {
                        Settings.IsRunning = false;
                    }
                    if(Settings.BlackCount <=0)
                    {
                        Settings.IsRunning = false;
                    }
                    Figures.FigureMap[bX][bY] = 0;
                    Result = true;
                }
            }
        }
        return Result;
    }
    
    public static void CulcMove()
    {
        SelectionMap = new int[Settings.LevelWidth][Settings.LevelHeight];
        if(Figures.FigureMap[SelectedX][SelectedY] == 1 || Figures.FigureMap[SelectedX][SelectedY] == 2)
        {
            if(SelectedX+1 < Settings.LevelWidth && SelectedY+1 < Settings.LevelHeight)
            {
                if(Figures.FigureMap[SelectedX+1][SelectedY+1] == 0)
                {
                   if(Settings.CurrentTurn == 1)
                   SelectionMap[SelectedX+1][SelectedY+1] = 1;
                }
                else if(Figures.FigureMap[SelectedX+1][SelectedY+1] != Settings.CurrentTurn+1 && Figures.FigureMap[SelectedX+1][SelectedY+1] != Settings.CurrentTurn+3)
                {
                    if(SelectedX+2 < Settings.LevelWidth && SelectedY+2 < Settings.LevelHeight)
                    {
                        if(Figures.FigureMap[SelectedX+2][SelectedY+2] == 0)
                        SelectionMap[SelectedX+2][SelectedY+2] = 1;
                    }
                }
            }
            if(SelectedX+1 < Settings.LevelWidth && SelectedY-1 >= 0)
            {
                if(Figures.FigureMap[SelectedX+1][SelectedY-1] == 0)
                {
                    if(Settings.CurrentTurn == 0)
                    SelectionMap[SelectedX+1][SelectedY-1] = 1;
                }
                else if(Figures.FigureMap[SelectedX+1][SelectedY-1] != Settings.CurrentTurn+1 && Figures.FigureMap[SelectedX+1][SelectedY-1] != Settings.CurrentTurn+3)
                {
                    if(SelectedX+2 < Settings.LevelWidth && SelectedY-2 >= 0)
                    {
                        if(Figures.FigureMap[SelectedX+2][SelectedY-2] == 0)
                        SelectionMap[SelectedX+2][SelectedY-2] = 1;
                    }
                }
            }
            if(SelectedX-1 >= 0 && SelectedY-1 >= 0)
            {
                if(Figures.FigureMap[SelectedX-1][SelectedY-1] == 0)
                {
                    if(Settings.CurrentTurn == 0)
                    SelectionMap[SelectedX-1][SelectedY-1] = 1;
                }
                else if(Figures.FigureMap[SelectedX-1][SelectedY-1] != Settings.CurrentTurn+1 && Figures.FigureMap[SelectedX-1][SelectedY-1] != Settings.CurrentTurn+3)
                {
                    if(SelectedX-2 >= 0 && SelectedY-2 >= 0)
                    {
                        if(Figures.FigureMap[SelectedX-2][SelectedY-2] == 0)
                        SelectionMap[SelectedX-2][SelectedY-2] = 1;
                    }
                }
            }
            if(SelectedX-1 >= 0 && SelectedY+1 < Settings.LevelHeight)
            {
                if(Figures.FigureMap[SelectedX-1][SelectedY+1] == 0)
                {
                    if(Settings.CurrentTurn == 1)
                    SelectionMap[SelectedX-1][SelectedY+1] = 1;
                }
                else if(Figures.FigureMap[SelectedX-1][SelectedY+1] != Settings.CurrentTurn+1 && Figures.FigureMap[SelectedX-1][SelectedY+1] != Settings.CurrentTurn+3)
                {
                    if(SelectedX-2 >= 0 && SelectedY+2 < Settings.LevelHeight)
                    {
                        if(Figures.FigureMap[SelectedX-2][SelectedY+2] == 0)
                        SelectionMap[SelectedX-2][SelectedY+2] = 1;
                    }
                }
            }
        }
        if(Figures.FigureMap[SelectedX][SelectedY] == 3 || Figures.FigureMap[SelectedX][SelectedY] == 4)
        {
            int x = SelectedX;
            int y = SelectedY;
            boolean IsKill = false;
            while(x-1 >=0 && y-1 >=0)
            {
                x--;
                y--;
                if(Figures.FigureMap[x][y] == 0) Selection.SelectionMap[x][y] = 1;
                else if(Figures.FigureMap[x][y] != Settings.CurrentTurn + 1 && Figures.FigureMap[x][y] != Settings.CurrentTurn + 3)
                {
                    if(IsKill) break;
                    else
                    {
                        if(x-1 >=0 && y-1 >=0)
                        {
                            if(Figures.FigureMap[x-1][y-1] == 0)
                            {
                                IsKill = true;
                                continue;
                            }
                            else break;
                            
                        }
                    }
                }
                else break;
            }
            x = SelectedX;
            y = SelectedY;
            IsKill = false;
            while(x+1 < Settings.LevelWidth && y-1 >=0)
            {
                x++;
                y--;
                if(Figures.FigureMap[x][y] == 0) Selection.SelectionMap[x][y] = 1;
                else if(Figures.FigureMap[x][y] != Settings.CurrentTurn + 1 && Figures.FigureMap[x][y] != Settings.CurrentTurn + 3)
                {
                    if(IsKill) break;
                    else
                    {
                        if(x+1 < Settings.LevelWidth && y-1 >=0)
                        {
                            if(Figures.FigureMap[x+1][y-1] == 0)
                            {
                                IsKill = true;
                                continue;
                            }
                            else break;
                        }
                    }
                }
                else break;
            }
            x = SelectedX;
            y = SelectedY;
            IsKill = false;
            while(x-1 >=0 && y+1 < Settings.LevelHeight)
            {
                x--;
                y++;
                if(Figures.FigureMap[x][y] == 0) Selection.SelectionMap[x][y] = 1;
                else if(Figures.FigureMap[x][y] != Settings.CurrentTurn + 1 && Figures.FigureMap[x][y] != Settings.CurrentTurn + 3)
                {
                    if(IsKill) break;
                    else
                    {
                        if(x-1 >=0 && y+1 < Settings.LevelHeight)
                        {
                            if(Figures.FigureMap[x-1][y+1] == 0)
                            {
                                IsKill = true;
                                continue;
                            }
                            else break;
                        }
                    }
                }
                else break;
            }
            x = SelectedX;
            y = SelectedY;
            IsKill = false;
            while(x+1 < Settings.LevelWidth && y+1 < Settings.LevelHeight)
            {
                x++;
                y++;
                if(Figures.FigureMap[x][y] == 0) Selection.SelectionMap[x][y] = 1;
                else if(Figures.FigureMap[x][y] != Settings.CurrentTurn + 1 && Figures.FigureMap[x][y] != Settings.CurrentTurn + 3)
                {
                    if(IsKill) break;
                    else
                    {
                        if(x+1 < Settings.LevelWidth && y+1 < Settings.LevelHeight)
                        {
                            if(Figures.FigureMap[x+1][y+1] == 0)
                            {
                                IsKill = true;
                                continue;
                            }
                            else break;
                        }
                    }
                }
                else break;
            }
        }
    }
}
