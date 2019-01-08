package com.la.server.protocol.response;

import com.la.server.protocol.Packet;

import lombok.Data;

import static com.la.server.protocol.command.Command.MESSAGE_RESPONSE;


@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;
    private String fromUserName;
    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }


    public String getFromUserId() {
        return fromUserId;
    }
    public String getFromUserName() {
        return fromUserName;
    }
    public String getMessage() {
        return message;
    }

    public void setFromUserId(String fromUserId) { this.fromUserId = fromUserId; }
    public void setFromUserName(String fromUserName) { this.fromUserName = fromUserName; }
    public void setMessage(String message) {this.message = message; }
}
