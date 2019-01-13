package com.la.server.protocol.response;


import com.la.server.protocol.Packet;

import lombok.Data;

import static com.la.server.protocol.command.Command.LOGIN_RESPONSE;


@Data
public class LoginResponsePacket extends Packet {
    private String userId;
    private String userName;
    private String reason;
    private String type;

    private boolean success;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }

    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getReason() { return reason; }
    public String getType() { return type; }

    public boolean isSuccess() { return success; }


    public void setUserId(String userId) { this.userId = userId; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setReason(String reason) { this.reason = reason; }
    public void setType(String type) { this.type = type; }

    public void setSuccess(boolean success) { this.success = success; }


}
