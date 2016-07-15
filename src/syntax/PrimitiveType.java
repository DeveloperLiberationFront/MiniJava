// Copyright (c) Mark P Jones, Portland State University
// Subject to conditions of distribution and use; see LICENSE for details
// February 3 2008 11:12 AM

package syntax;

/** Provides a representation for primitive types.
 */
public final class PrimitiveType extends Type {
    private String name;
    public PrimitiveType(String name) {
        this.name = name;
    }

    /** Return a printable representation of this type.
     */
    public String toString() {
        return name;
    }

    /** Test for equality with another type.
     */
    public boolean equal(Type type) {
        if (type instanceof PrimitiveType) {
            PrimitiveType that = (PrimitiveType)type;
            return that.name.equals(this.name);
        }
        return false;
    }
}
