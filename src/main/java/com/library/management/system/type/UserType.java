/**
 * 
 */
package com.library.management.system.type;

/**
 * @author osman
 *
 */
public enum UserType {
	
	STUDENT("ROLE_USER"),
	LIBRARIAN("ROLE_ADMIN");
	
	private final String name;       

    private UserType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
