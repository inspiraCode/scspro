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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.DocumentId;

import com.inspiracode.nowgroup.scspro.domain.common.Dominable;

/**
 * Permission domain object. Defines the sys_permission database table.
 * hasRole('ROLE_NAME') searches for the permissionName stored in this entity.
 * ManyToMany relationship with roles has been removed to avoid
 * "Session is closed exception"
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
@Table(name = "sys_permission", uniqueConstraints = @UniqueConstraint(columnNames = { "permissionName" }))
public class Permission implements Dominable {
    private static final long serialVersionUID = 4905053531249254909L;

    @Id
    @Column(name = "permission_id")
    @SequenceGenerator(name = "permission_id_seq", sequenceName = "permission_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_id_seq")
    @DocumentId
    private Long permissionId;

    private String permissionName;

    public Long getPermissionId() {
	return permissionId;
    }

    public void setPermissionId(Long permissionId) {
	this.permissionId = permissionId;
    }

    public String getPermissionName() {
	return permissionName;
    }

    public void setPermissionName(String permissionName) {
	this.permissionName = permissionName;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((permissionId == null) ? 0 : permissionId.hashCode());
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
	Permission other = (Permission) obj;
	if (permissionId == null) {
	    if (other.permissionId != null)
		return false;
	} else if (!permissionId.equals(other.permissionId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Permission [permissionId=" + permissionId + ", permissionName=" + permissionName + "]";
    }

}
