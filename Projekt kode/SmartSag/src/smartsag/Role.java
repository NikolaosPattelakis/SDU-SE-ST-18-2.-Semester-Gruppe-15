/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartsag;

/**
 * Final Class Role, contains the important information and methods every role of the system can have.
 * extends @InformationData <br>
 * implements @Tags
 */
public final class Role extends InformationData implements Tags{

    private final String roleTag = Role.TAG_ROLE;

    /**
     * @return the roleTag
     */
    public String getRoleTag() {
        return roleTag;
    }
}
