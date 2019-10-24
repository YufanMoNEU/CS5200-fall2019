package edu.northeastern.cs5200.models;
import java.util.*;

public enum Privilege {
	CREATE("create"),
    DELETE("delete"),
    READ("read"),
    UPDATE("update");

    Privilege(String privilege) {
        this.privilege = privilege;
    }

    private String privilege;

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public static Privilege fromString(String privilege) {
        switch (privilege) {

            case "create":
                return Privilege.CREATE;
            case "read":
                return Privilege.READ;
            case "update":
                return Privilege.UPDATE;
            case "delete":
                return Privilege.DELETE;
            default:
                return null;
        }
    }
}