package com.example.demo.enums;

public enum RoleEnum {
    ADMIN("Admin", 3),
    MODERATOR("Moderator", 2),
    DEFAULT("Default", 1);

    private final String roleName;
    private final int privilegesNumber;

    RoleEnum(String roleName, int privilegesNumber) {
        this.roleName = roleName;
        this.privilegesNumber = privilegesNumber;
    }

    public static RoleEnum fromRoleName(String roleName) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.getRoleName().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant with role name: " + roleName);
    }

    public String getRoleName() {
        return roleName;
    }

    public int getPrivilegesNumber() {
        return privilegesNumber;
    }

}
