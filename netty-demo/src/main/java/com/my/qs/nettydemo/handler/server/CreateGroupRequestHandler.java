package com.my.qs.nettydemo.handler.server;

import com.my.qs.nettydemo.protocol.CreateGroupRequestPacket;
import com.my.qs.nettydemo.protocol.CreateGroupResponsePacket;
import com.my.qs.nettydemo.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE =new CreateGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> members = createGroupRequestPacket.getMembers();
        String groupName  = createGroupRequestPacket.getGroupName();
        List<String> memberNames = new ArrayList<>();

        DefaultChannelGroup channelGroup = new DefaultChannelGroup(groupName, channelHandlerContext.executor());


        for (String member : members) {
            Channel channel = SessionUtil.getChannel(member);
            if (channel != null){
                channelGroup.add(channel);
                memberNames.add(SessionUtil.getSession(channel).getUserName());
            }
        }


        String groupId = UUID.randomUUID().toString().substring(0, 7);
        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setGroupId(groupId)
                .setMembers(memberNames);

        SessionUtil.storeGroup(groupId, channelGroup);
        channelGroup.writeAndFlush(responsePacket);
    }
}
