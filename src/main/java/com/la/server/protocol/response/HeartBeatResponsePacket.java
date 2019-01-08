package com.la.server.protocol.response;


import com.la.server.protocol.Packet;

import lombok.Data;

import static com.la.server.protocol.command.Command.HEARTBEAT_RESPONSE;


@Data
public class HeartBeatResponsePacket extends Packet {
    private int constant = 1;

    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }

    public int getConstant() {
        return constant;
    }

    public void setConstant(int constant) {
        this.constant = constant;
    }
}
