package util;

import java.util.LinkedList;
import java.util.List;

public abstract class ObjectPlus4 extends ObjectPlusPlus {

    /**
     * Stores a list of roles constrained by the xor
     */
    private List<String> rolesXOR = new LinkedList<>();

    public ObjectPlus4() {
        super();
    }

    /**
     * Creates a new link to a given object (as an ordinary binary link - NOT a qualified one) taking into account the subset constraint.
     */
    public void addLink_subset(String roleName, String reverseRoleName, String superRoleName, ObjectPlusPlus targetObject) throws Exception {
        if(isLink(superRoleName, targetObject)) {
            // There is a (super) link to the added object in the super role
            // Create the link
            addLink(roleName, reverseRoleName, targetObject);
        }
        else {
            // No super link ==> exception
            throw new Exception("No link to the '" + targetObject + "' object in the '" + superRoleName + "' super role!");
        }
    }

    /**
     * Adds a role name to the list of roles restricted by the XOR.
     */
    public void addXorRole(String xorRoleName) {
        rolesXOR.add(xorRoleName);
    }

    /**
     * Creates a new link to a given object (as an ordinary binary link - NOT a qualified one) taking into account the XOR constraint.
     * A list of XOR constrained roles is defined using the addXorRole(String xorRoleName) method.
     */
    public void addLinkXor(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) throws Exception {
        if(rolesXOR.contains(roleName)) {
            // The currently adding role is XOR'ed

            // Check if there is a link for XOR'ed roles
            if(isXorLink()) {
                throw new Exception("There is a link for a XOR role!");
            }

            // There is no link ==> add a link using an already existing method from a super class
        }

        // Add the link
        super.addLink(roleName, reverseRoleName, targetObject);
    }

    /**
     * Indicates if there is a link for a XOR'ed roles.
     */
    private boolean isXorLink() {
        for(String role : rolesXOR) {
            if(this.anyLink(role)) {
                return true;
            }
        }

        return false;
    }
}
