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


package compiler;

/** Represents a warning diagnostic.
 */
public class Warning extends SimpleDiagnostic {
    /** Construct a simple warning with a fixed description.
     */
    public Warning(String text) {
        super(text);
    }

    /** Construct a warning object for a particular source position.
     */
    public Warning(Position position) {
        super(position);
    }

    /** Construct a simple warning with a fixed description
     *  and a source position.
     */
    public Warning(Position position, String text) {
        super(position, text);
    }
}
