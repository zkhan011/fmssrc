/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.virtualvehicle;

/**
 *
 * @author zisha
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class EchoClientHandler extends ChannelInboundHandlerAdapter {
  
   public  boolean Close = false;  
  
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Got reply from: " + msg.toString().trim());
        while(Close!=false){
        System.out.println("close");
        
        Close = false;
        
        }
        
       ctx.disconnect();
    }
    
     public void disconnect() throws Exception {
     
       
      Close = true;
       
        //ctx.disconnect();
    }
    
}