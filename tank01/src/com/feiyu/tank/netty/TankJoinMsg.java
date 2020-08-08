package com.feiyu.tank.netty;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import com.feiyu.tank.Tank;
import com.feiyu.tank.Dir;
import com.feiyu.tank.Group;
import com.feiyu.tank.netty.TankJoinMsg;
/**
 * 消息传输内容
 * @author feiyu
 *
 */
public class TankJoinMsg {
	
	public int x, y;//位置
	public Dir dir;//方向
	public boolean moving;//移动
	public Group group;//类型：好坏
	public UUID id;//id标识
	
	
	
	public TankJoinMsg(Tank t) {
		this.x = t.getX();
		this.y = t.getY();
		this.dir = t.getDir();
		this.group = t.getGroup();
		this.id = t.getId();
		this.moving = t.isMoving();
	}
	
	public TankJoinMsg(int x, int y, Dir dir, boolean moving, Group group, UUID id) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.moving = moving;
		this.group = group;
		this.id = id; 
	}
	
	public TankJoinMsg() {
	}

	public void parse(byte[] bytes) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
		try {
			//dis.readInt();
			
			this.x = dis.readInt();
			this.y = dis.readInt();
			this.dir = Dir.values()[dis.readInt()];
			this.moving = dis.readBoolean();
			this.group = Group.values()[dis.readInt()];
			this.id = new UUID(dis.readLong(), dis.readLong());
			//this.name = dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 自定义消息
	 * 
	 * 把传递的消息转成字节数组
	 * 这里没有用netty的bybuffer，是因为之后不用netty了，还能继续使用
	 * @return
	 */
	public byte[] toBytes() {
		ByteArrayOutputStream baos = null;
		DataOutputStream dos = null; 
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			dos = new DataOutputStream(baos);//对baos进行包装方便使用
			
			//网络传输不建议用字符串传输，因为字符串不稳定会随着传输内容的多少变化，而基本类型传输是因为字节固定
			
			//dos.writeInt(TYPE.ordinal());
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(dir.ordinal());
			dos.writeBoolean(moving);
			dos.writeInt(group.ordinal());
			//一个uuid是128位，这里分成了两个64位的Long类型去写
			dos.writeLong(id.getMostSignificantBits());
			dos.writeLong(id.getLeastSignificantBits());
			//dos.writeUTF(name);
			dos.flush();//写入dos
			bytes = baos.toByteArray();//内存字节数组（dos）转成保存的字节数组（bytes）
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(dos != null) {
					dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return bytes;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getName())
			   .append("[")
			   .append("uuid=" + id + " | ")
			   //.append("name=" + name + " | ")
			   .append("x=" + x + " | ")
			   .append("y=" + y + " | ")
			   .append("moving=" + moving + " | ")
			   .append("dir=" + dir + " | ")
			   .append("group=" + group + " | ")
			   .append("]");
		return builder.toString();
	}
	
/*	@Override
	public void handle() {
		if(this.id.equals(TankFrame.INSTANCE.getMainTank().getId()) ||
				TankFrame.INSTANCE.findTankByUUID(this.id) != null) return;
//		System.out.println(this);
		Tank t = new Tank(this);
		TankFrame.INSTANCE.addTank(t);
		
		//send a new TankJoinMsg to the new joined tank
		Client.INSTANCE.send(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
	}

	@Override
	public MsgType getMsgType() {
		// TODO Auto-generated method stub
		return MsgType.TankJoin;
	}*/


}
