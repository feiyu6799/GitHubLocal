package com.feiyu.tank.netty;

import java.util.List;
import java.util.UUID;

import com.feiyu.tank.Dir;
import com.feiyu.tank.Group;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
/**
 * 解码
 * @author feiyu
 *
 */
public class TankJoinMsgDecoder extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		//这里in.readableBytes()的比较数值是根据TankJoinMsg里的属性有多长决定的
		//针对不固定长度的，一般传递过来的消息是有协议的，分成四部分，1.消息类型2.消息需要处理的字节3.字节内容4.校验码，判断消息是否被中途纂改过。
		if(in.readableBytes() < 33) return; //TCP 拆包 沾包
		
		//in.markReaderIndex();
		TankJoinMsg msg = new TankJoinMsg();
		
		msg.x = in.readInt();
		msg.y = in.readInt();
		msg.dir = Dir.values()[in.readInt()];
		msg.moving = in.readBoolean();
		msg.group = Group.values()[in.readInt()];
		msg.id = new UUID(in.readLong(), in.readLong());
		
		out.add(msg);
	}

}
