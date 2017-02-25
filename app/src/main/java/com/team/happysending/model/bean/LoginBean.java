package com.team.happysending.model.bean;

/**
 * 登陆
 * Created by zhaoshihao on 2017/2/24.
 */

public class LoginBean extends BaseBean<LoginBean> {


    /**
     * nick_name : ssssss
     * userId : ceacda140d744f9e829796d67b245fef
     * phone_num : 18335697441
     * attestation_type : 1
     * addressId : 5479a4ad94d64926a33b75da1a595c65
     * token : 000F43308B9AA23C50427EF20C12A0CA85D461DCEE19E5DF92B85A9B546C9F8D5C0F7FB757AD350A9930B20F72BA9847
     */

    private String nick_name;
    private String userId;
    private String phone_num;
    private String attestation_type;
    private String addressId;
    private String token;

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getAttestation_type() {
        return attestation_type;
    }

    public void setAttestation_type(String attestation_type) {
        this.attestation_type = attestation_type;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
