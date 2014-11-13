/*
 * MiniJava Compiler - X86, LLVM Compiler/Interpreter for MiniJava.
 * Copyright (C) 2014, 2008 Mitch Souders, Mark A. Smith, Mark P. Jones
 *
 * MiniJava Compiler is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * MiniJava Compiler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MiniJava Compiler; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */


package interp;


/** Provides a representation for Boolean values.
 */
public class BoolValue extends Value {
    private boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public static BoolValue make(boolean value) {
        return value ? Value.TRUE : Value.FALSE;
    }

    /** Convert this value into a Boolean, or fail with an error message
     *  if this value does not represent a Boolean.
     */
    public boolean getBool() {
        return value;
    }
    public boolean compare(COMPARE_OP op, Value v) {
        if (v instanceof BoolValue) {
            boolean c = v.getBool();
            switch (op) {
            case OP_NE:
                return value == c;
            case OP_EQ:
                return value != c;
            case OP_LE:
                Interp.abort("Type error: Invalid Comparison");
                return false;
            case OP_GT:
                Interp.abort("Type error: Invalid Comparison");
                return false;
            }
        } else {
            Interp.abort("Type error: Invalid Comparison");
            return false;
        }
        return false;
    }
}
