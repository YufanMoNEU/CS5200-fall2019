package edu.northeastern.cs5200.models;

import java.util.HashMap;
import java.util.Map;

public enum Role {

    OWNER(1), ADMIN(2), WRITER(3), EDITOR(4), REVIEWER(5);

    private int roleId;

    Role(int roleId) {
        this.roleId = roleId;

    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public static Role fromInt(int roleId) {
        switch (roleId) {
            case 1:
                return Role.OWNER;
            case 2:
                return Role.ADMIN;
            case 3:
                return Role.WRITER;
            case 4:
                return Role.EDITOR;
            case 5:
                return Role.REVIEWER;
            default:
                return null;
        }
    }

}