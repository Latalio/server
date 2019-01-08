package com.la.server.protocol.request;


import com.la.server.protocol.Packet;

import lombok.Data;

import static com.la.server.protocol.command.Command.HEARTBEAT_REQUEST;


@Data
public class HeartBeatRequestPacket extends Packet {
    private int constant = 1;

    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }

    public int getConstant() {
        return constant;
    }

    public void setConstant(int constant) {
        this.constant = constant;
    }
}
