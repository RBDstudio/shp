package GameGraphics;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.util.Arrays;

public abstract class GameFrame 
{
    private static JFrame Window;
    private static Canvas FrameContent;
    
    private static BufferedImage RenderedFrame;
    private static int[] RenderedFrameData;
    private static Graphics RenderedFrameGraphics;
    private static int ClearColor;
    private static BufferStrategy RenderedFrameStrategy;
    
    public static Graphics2D g;
    public static boolean MouseIsClicked;
    public static int MouseX;
    public static int MouseY;
    

    
    
    public static void CreateWindow(String Name, int Wight, int Height, int NewClearColor, int BuffersCount)
    {
        MouseIsClicked = false;
        ClearColor = NewClearColor;
        
        FrameContent = new Canvas();
        FrameContent.setPreferredSize(new Dimension(Wight,Height));
            
        Window = new JFrame(Name);
        Window.setResizable(false);
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window.getContentPane().add(FrameContent);
        Window.pack();
        Window.setVisible(true);
        
        RenderedFrame = new BufferedImage(Wight,Height,BufferedImage.TYPE_INT_ARGB);
        RenderedFrameData = ((DataBufferInt)RenderedFrame.getRaster().getDataBuffer()).getData();
        RenderedFrameGraphics = RenderedFrame.getGraphics();
        ((Graphics2D)RenderedFrameGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        FrameContent.createBufferStrategy(BuffersCount);
        RenderedFrameStrategy = FrameContent.getBufferStrategy();
        
        FrameContent.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(e.getButton() == MouseEvent.BUTTON1)
                {
                    int x = e.getX();
                    int y = e.getY();
                    
                    MouseX = x;
                    MouseY = y;
                    MouseIsClicked = true;
                }
            }
        });
        
        g = GetGraphics();
    }
    
    public static void ClearFrame()
    {
        Arrays.fill(RenderedFrameData,ClearColor);
    }
    
    
    public static void SwapFrame()
    {
        Graphics g = RenderedFrameStrategy.getDrawGraphics();
        g.drawImage(RenderedFrame,0,0,null);
        RenderedFrameStrategy.show();
    }
    
    public static Graphics2D GetGraphics()
    {
        return (Graphics2D)RenderedFrameGraphics;
    }
    
    public static void DestroyWindow()
    {
        Window.dispose();
    }
    
    public static void ChangeName(String NewName)
    {
        Window.setTitle(NewName);
    }
}
