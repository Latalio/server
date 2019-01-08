package com.la.server.protocol.request;


import com.la.server.protocol.Packet;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.la.server.protocol.command.Command.MESSAGE_REQUEST;


@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private String message;

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
