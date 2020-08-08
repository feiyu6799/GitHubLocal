package com.feiyu.tank.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
/**
 * 编码器：负责编码
 * 把一个消息转换成字节
 * @author feiyu
 *
 */
public class TankJoinMsgEncoder extends MessageToByteEncoder<TankJoinMsg>{

	@Override
	protected void encode(ChannelHandlerContext ctx, TankJoinMsg msg, ByteBuf buf) throws Exception {
		buf.writeBytes(msg.toBytes());
	}
	

}
