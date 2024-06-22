package GameLogic;

import GameGraphics.GameFrame;
import GameGraphics.Sprites;
import GameObjects.Figures;
import GameObjects.Level;

public class GameProcess extends Thread
{
    private Thread GameThread;
    
    
    public GameProcess()
    {
        Settings.IsRunning = false;
    }
    
    public synchronized void StartGame()
    {
        Settings.IsRunning = true;
        GameThread = new Thread(this);
        GameThread.start();
    }
    
    public synchronized void StopGame()
    {
        try
        {
        GameThread.join();
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        Clean();
    }
    
    private void Update()
    {
        Selection.update();
    }
    
    private void Render()
    {
        GameFrame.ClearFrame();
        
        Level.render();
        Figures.render();
        Selection.render();
        
        GameFrame.SwapFrame();
    }
    
    private void Clean()
    {
        GameFrame.DestroyWindow();
    }
    
    public void run()
    {
        //Порядок загрузки \/ \/ \/
        Sprites.LoadSprites();
        Level.GenerateLevelMap();
        Figures.GenerateFigureMap();
        Selection.GenerateSelection();
        //Порядок загрузки /\ /\ /\
        
        if(Settings.CurrentTurn == 0) GameFrame.ChangeName("Ходят белые");
        if(Settings.CurrentTurn == 1) GameFrame.ChangeName("Ходят чёрные");
        
        int c = 0;
        int FPS = 0;
        int Upd = 0;
        int UpdL = 0;
        float UpdateDelta = 0;
        
        long Count = 0;
        GameTimer.LastTime = GameTimer.GetTime();
        while(Settings.IsRunning)
        {
            c++;
            //Обновление времени
            GameTimer.SwapTime();
            Count += GameTimer.Diff;
            UpdateDelta += (GameTimer.Diff/ Settings.FrameInterval);
            //расчёт физики
            boolean FrameIsChanged = false;
            while(UpdateDelta > 1)
            {
                Update();
                Upd++;
                UpdateDelta--;
                
                if(FrameIsChanged) UpdL++;
                else FrameIsChanged = true;
            }
            
            //рендер кадра
            if(FrameIsChanged)
            {
                Render();
                FPS++;
            }else
            {
                try 
                {
                    Thread.sleep(Settings.ThreadSaveTime);
                } catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
            }
            if(Count >= GameTimer.Second) 
            {
                //GameFrame.ChangeName(Settings.Name + " | FPS: " + FPS + " | Upd: " + Upd + " | UpdL: " + UpdL);
                Upd = 0;
                FPS = 0;
                UpdL = 0;
                Count = 0;
            }
        }
        if(Settings.WhiteCount <=0)
            {
                GameFrame.ChangeName("Чёрные победили!");
            }
            if(Settings.BlackCount <=0)
            {
                GameFrame.ChangeName("Белые победили!");
            }
    }
}
