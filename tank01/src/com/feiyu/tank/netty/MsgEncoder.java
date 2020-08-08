package com.feiyu.tank.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
/**
 * 单元测试TankJoinMsgCodecTest.java
 * @author feiyu
 *
 */
public class MsgEncoder extends MessageToByteEncoder<Msg>{

	@Override
	protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf buf) throws Exception {
		buf.writeInt(msg.getMsgType().ordinal());
		byte[] bytes = msg.toBytes();
		buf.writeInt(bytes.length);
		buf.writeBytes(bytes);
	}
	

}
