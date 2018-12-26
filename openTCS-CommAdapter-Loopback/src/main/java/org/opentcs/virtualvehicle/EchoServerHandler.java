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
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    public static int LINE_MAX = 1024;

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client disconnected.");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object linebuf) throws Exception {
        byte[] bytes = new byte[((ByteBuf) linebuf).readableBytes()];

        String line = ((ByteBuf) linebuf).toString(Charset.defaultCharset());
        line = line.trim();
        if (line.length() == 0) {
            ((ByteBuf) linebuf).release();
            ctx.channel().close();
        } else {
            System.out.println("Got msg: " + line);
            // reuse the ByteBuf, no need to release as it is released on writing to wire
            ctx.write(linebuf);
            // force write
            ctx.flush();
        }
    }
}