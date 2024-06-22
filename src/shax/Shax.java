package shax;

import GameGraphics.GameFrame;
import GameLogic.GameProcess;
import GameLogic.Settings;


public class Shax {


    public static void main(String[] args) 
    {
        GameFrame.CreateWindow(Settings.Name, Settings.Width, Settings.Height, Settings.ClearColor, Settings.BuffersCount);
        GameProcess GP = new GameProcess();
        GP.StartGame();
    }
    
}
