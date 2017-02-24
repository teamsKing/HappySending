package com.team.happysending.model.bean;

/**
 * 注册
 * Created by zhaoshihao on 2017/2/24.
 */

public class RegistBean extends BaseBean<RegistBean> {


    /**
     * id_card :
     * real_name :
     * nick_name : zzz
     * userId : 67245dac42444da899860725ae0c94ef
     * phone_num : 18212123122
     * attestation_type : 1
     * user_head :
     * addressId : a8a8b902f9f940febdc29d48aaa5acbf
     * token : F4B400E7C2E293AAEBB9F84BDBCA9B83C0904A33DD917522D60DB4A5A160DE241C9311B97FA47E3951BB01BFDDBC74D0
     */

    private String id_card;
    private String real_name;
    private String nick_name;
    private String userId;
    private String phone_num;
    private String attestation_type;
    private String user_head;
    private String addressId;
    private String token;

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

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

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
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
