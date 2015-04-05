-- INITIAL ADMINISTRATIVE RIGHTS AND USER
INSERT INTO sys_permission(permission_id, permissionname)
VALUES(1,'USERS_ADMIN');
INSERT INTO sys_permission(permission_id, permissionname)
VALUES(2,'ACCESS_RESOURCES');
INSERT INTO sys_permission(permission_id, permissionname)
VALUES(3,'ACCESS_WEBSERVICES');
INSERT INTO sys_role(role_id, rolename)
VALUES(1,'ADMIN');
INSERT INTO sys_role_permission(role_id, permission_id)
VALUES(1,1);
INSERT INTO sys_role_permission(role_id, permission_id)
VALUES(1,2);
INSERT INTO sys_role_permission(role_id, permission_id)
VALUES(1,3);
INSERT INTO sys_user(user_id, password, status, username)
VALUES(1,'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'AC', 'admin');
INSERT INTO sys_user_role(user_id, role_id)
VALUES(1,1);