package GameGraphics;

import GameLogic.Settings;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprites 
{
    public static BufferedImage WhiteFigure;
    public static BufferedImage BlackFigure;
    public static BufferedImage WhiteFigureQ;
    public static BufferedImage BlackFigureQ;
    public static BufferedImage WhiteCell;
    public static BufferedImage BlackCell;
    public static BufferedImage GreenCell;
    public static BufferedImage Selected;
    

    private BufferedImage ImageBuf;

    public static void LoadSprites()
    {
        BufferedImage FullImage = LoadImage("SHsprites.png");
        WhiteFigure = Resize(Cut(0,0,FullImage),Settings.Scale);
        BlackFigure = Resize(Cut(1,0,FullImage),Settings.Scale);
        WhiteFigureQ = Resize(Cut(0,1,FullImage),Settings.Scale);
        BlackFigureQ = Resize(Cut(1,1,FullImage),Settings.Scale);
        WhiteCell = Resize(Cut(2,0,FullImage),Settings.Scale);
        BlackCell = Resize(Cut(2,1,FullImage),Settings.Scale);
        GreenCell = Resize(Cut(3,0,FullImage),Settings.Scale);
        Selected = Resize(Cut(3,1,FullImage),Settings.Scale);
    }
    
    public static BufferedImage Resize(BufferedImage image, float Scale)
    {
        int Width = (int)(image.getWidth() * Scale);
        int Height = (int)(image.getHeight() * Scale);
        BufferedImage Buffer = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_ARGB);
        Buffer.getGraphics().drawImage(image,0,0,Width,Height,null);
        return Buffer;
    }
    
    public static BufferedImage LoadImage(String FileName)
    {
        BufferedImage Image = null;
        
        try
        {
            Image = ImageIO.read(new File("Resurses/" + FileName));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        
        return Image;
    }
    
    public static BufferedImage Cut(int x, int y,BufferedImage FullImage)
    {
        
        return FullImage.getSubimage(x*Settings.CellSize, y*Settings.CellSize, Settings.CellSize, Settings.CellSize);
    }
    
    public static void render(float x, float y,BufferedImage RImage)
    {
        GameFrame.g.drawImage(RImage,(int)(x*Settings.CellSize*Settings.Scale),(int)(y*Settings.CellSize*Settings.Scale),null);
    }
}
