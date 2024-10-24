package com.example.demo.valueobject;

import com.example.demo.enums.RoleEnum;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@Getter
@Setter
public class UserPrivileges {
    @Size(min = 1, max = 3)
    private Integer privilegesNumber;
    private String role;

    public UserPrivileges(RoleEnum roleEnum) {
        this.privilegesNumber = roleEnum.getPrivilegesNumber();
        this.role = roleEnum.getRoleName();
    }

    public UserPrivileges() {
        this.privilegesNumber = RoleEnum.DEFAULT.getPrivilegesNumber();
        this.role = RoleEnum.DEFAULT.getRoleName();
    }

}
