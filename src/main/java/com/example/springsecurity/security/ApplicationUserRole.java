package com.example.springsecurity.security;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import static com.example.springsecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(new HashSet<>()),
    ADMIN(new HashSet<>(Arrays.asList(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE))),
    ADMINTRAINEE(new HashSet<>(Arrays.asList(COURSE_READ, STUDENT_READ)));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}