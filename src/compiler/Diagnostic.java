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

/** A base class for objects that represent compiler diagnostics.
 *  Errors should be implemented as subclasses of Failure, while
 *  warnings are normally implemented as as subclasses of Warning.
 */
public abstract class Diagnostic extends Exception {
    public abstract String getText();

    public abstract Position getPos();

    /** Return a cross reference string for this diagnostic.  The
     *  format and interpretation of this string has not yet
     *  determined, but might, for example, be used to construct
     *  a URL or file name that contains additional information
     *  about problems of this kind.
     */
    protected String crossRef;
    public String getCrossRef() {
        return null;
    }

}
