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
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;


public class EchoClient {
    public String host;
    public int port;
    public static ChannelFuture f;
    public static EventLoopGroup eventGroup = new NioEventLoopGroup();
    EchoClientHandler e = new EchoClientHandler();

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void send(String msg) throws InterruptedException {
      

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventGroup)
                .remoteAddress(host, port)
                .channel(NioSocketChannel.class) // TCP server socket
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(
                                // break stream into "lines"
                                new LineBasedFrameDecoder(EchoServerHandler.LINE_MAX, false, true),
                                new StringDecoder(CharsetUtil.UTF_8),
                                new StringEncoder(CharsetUtil.UTF_8)
                                //e
                                );
                    }
                });

        ChannelFuture f = bootstrap.connect().sync();
        System.out.println("Connected!");
        f.channel().writeAndFlush(msg).sync();
        //e.channelRead(ctx, msg);
        System.out.print("Sent: " + msg);
        //f.channel().closeFuture().sync();
      
        System.out.println("Done.");
        
    }
     public  void disconnect() throws InterruptedException, Exception{
       
        System.out.println("disconnecting!");
        
        eventGroup.shutdownGracefully().sync();
        f.channel().close();
       // e.disconnect();
       
       
     }
    public static void main(String[] args) throws InterruptedException, Exception {
      
        
        
        EchoClient client = new EchoClient("127.0.0.1", 11000);
        client.send("Hello world!");
        client.disconnect();
    }
}