package net.alexander.backdata.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.alexander.backdata.BackData;
import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.service.ServiceManager;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class NetworkHandler extends SimpleChannelInboundHandler<String> {

    private static LoggerManager logger = ServiceManager.getService(LoggerManager.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        if(BackData.getInstance().getNetworkManager().isRegistered(ctx.channel().id().asLongText())) {
            new Client(ctx.channel()).login(message);
        } else {
            BackData.getInstance().getNetworkManager().getClient(ctx.channel().id().asLongText()).handleMessage(message);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.log("The client " + ctx.channel().remoteAddress().toString() + " trying to connect.");

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if((!BackData.getInstance().getNetworkManager().isRegistered(ctx.channel().id().asLongText())) && (!ctx.channel().isOpen())) {
                    ctx.channel().closeFuture();
                }
            }
        }, TimeUnit.MINUTES.toMillis(5));
    }
}
