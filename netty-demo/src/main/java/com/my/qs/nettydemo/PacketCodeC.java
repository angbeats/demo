package com.my.qs.nettydemo;

import com.my.qs.nettydemo.protocol.*;
import com.my.qs.nettydemo.serialize.JsonSerializer;
import com.my.qs.nettydemo.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

public class PacketCodeC {
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;


    private PacketCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JsonSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }


    public static  final int MAGIC_NUMBER = 0x12345678;

    public ByteBuf encode(ByteBuf byteBuf, Packet packet){

        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        // 版本号
        byteBuf.writeByte(packet.getVersion());
        //序列化算法
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        //指令
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf bytebuf){
        bytebuf.skipBytes(4);
        bytebuf.skipBytes(1);
        byte algorithm = bytebuf.readByte();
        byte command = bytebuf.readByte();
        int length = bytebuf.readInt();

        byte[] bytes = new byte[length];
        bytebuf.readBytes(bytes);


        Serializer serializer = getSerialize(algorithm);
        Class<? extends Packet> packet = getRequestType(command);

        if(serializer != null && packet != null){
            return serializer.deserialize(packet, bytes);
        }

        return null;
    }

    private Class<? extends Packet> getRequestType(byte command){
        return packetTypeMap.get(command);
    }

    private Serializer getSerialize(byte serializeAlgorithm){
        return serializerMap.get(serializeAlgorithm);
    }

}
