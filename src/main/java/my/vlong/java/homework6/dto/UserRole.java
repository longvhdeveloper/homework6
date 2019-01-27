package my.vlong.java.homework6.dto;

public enum UserRole {
    ADMIN(1, "Admin"), STAFF(2, "Staff");
    private int code;
    private String name;

    private UserRole(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public static UserRole map(int code) {
        switch (code) {
            case 1:
                return ADMIN;
            case 2:
                return STAFF;
        }
        return null;
    }

    public static String valueOf(UserRole userRole) {
        switch (userRole) {
            case ADMIN:
                return ADMIN.name;
            case STAFF:
                return STAFF.name;
        }

        return null;
    }

}
