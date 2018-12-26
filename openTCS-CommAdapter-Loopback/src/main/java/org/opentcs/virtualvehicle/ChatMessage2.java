package org.opentcs.virtualvehicle;

public class ChatMessage2 extends ChatMessage {

      private int X;
      private int Y;
      private int prevX;
      private int prevY;
      private boolean drawing;

       public ChatMessage2()
       {
          super();
       }
       
       public ChatMessage2(String name, String msg)
       {
          super(name, msg);
       }

       public ChatMessage2(int X, int Y, int prevX, int prevY)
       {
           this.X = X;
           this.Y = Y;
           this.prevX = prevX;
           this.prevY = prevY;
       }

       public void SetCoordinates(int X, int Y, int prevX, int prevY)
       {
             this.X = X;
             this.Y = Y;
             this.prevX = prevX;
             this.prevY = prevY;
       }

       public int getX()
       {
           return X;
       }

       public int getY()
       {
           return Y;
       }

       public int getPrevX()
       {
           return prevX;
       }

       public int getPrevY()
       {
           return prevY;
       }
       
       public void SetDrawing(boolean flag)
       {
           drawing = flag;
       }

       public boolean isDrawing()
       {
          return drawing;
       }
}
