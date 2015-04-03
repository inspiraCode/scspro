/**
 * THIS IS A COMMERCIAL PROGRAM PROVIDED FOR INSPIRACODE AND IT'S ASSOCIATES
 * BUILT BY EXTERNAL SOFTWARE PROVIDERS.
 * THE SOFTWARE COMPRISING THIS SYSTEM IS THE PROPERTY OF INSPIRACODE OR ITS
 * LICENSORS.
 *
 * ALL COPYRIGHT, PATENT, TRADE SECRET, AND OTHER INTELLECTUAL PROPERTY RIGHTS
 * IN THE SOFTWARE COMPRISING THIS SYSTEM ARE, AND SHALL REMAIN, THE VALUABLE
 * PROPERTY OF INSPIRACODE OR ITS LICENSORS.
 *
 * USE, DISCLOSURE, OR REPRODUCTION OF THIS SOFTWARE IS STRICTLY PROHIBITED,
 * EXCEPT UNDER WRITTEN LICENSE FROM INSPIRACODE OR ITS LICENSORS.
 *
 * &copy; COPYRIGHT 2015 INSPIRACODE. ALL RIGHTS RESERVED.
 */
package com.inspiracode.nowgroup.scspro.authentication.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.DocumentId;

import com.inspiracode.nowgroup.scspro.domain.common.Dominable;

/**
 * Role domain object. Defines the sys_role database table. ManyToMany
 * relationship defines sys_role_permission database table.
 * 
 * <B>Revision History:</B>
 * 
 * <PRE>
 * ====================================================================================
 * Date-------- By---------------- Description
 * ------------ --------------------------- -------------------------------------------
 * 03/04/2015 - torredie - Initial Version.
 * ====================================================================================
 * </PRE>
 * 
 * 
 * @author torredie
 * 
 */
@Entity
@Table(name = "sys_role", uniqueConstraints = @UniqueConstraint(columnNames = { "roleName" }))
public class Role implements Dominable {
    private static final long serialVersionUID = 4371029778141610971L;

    @Id
    @Column(name = "role_id")
    @SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
    @DocumentId
    private Long roleId;
    private String roleName;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Permission.class, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_permission", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "permission_id") })
    private Set<Permission> permissions = new HashSet<Permission>();

    public Long getRoleId() {
	return roleId;
    }

    public void setRoleId(Long roleId) {
	this.roleId = roleId;
    }

    public String getRoleName() {
	return roleName;
    }

    public void setRoleName(String roleName) {
	this.roleName = roleName;
    }

    public Set<Permission> getPermissions() {
	return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
	this.permissions = permissions;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Role other = (Role) obj;
	if (roleId == null) {
	    if (other.roleId != null)
		return false;
	} else if (!roleId.equals(other.roleId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Role [roleId=" + roleId + ", roleName=" + roleName + ", permissions=" + permissions + "]";
    }

}
