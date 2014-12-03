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


package syntax;

import notifications.contracts.TypeContract;
import compiler.Position;

/** Provides a representation for syntactic elements, each of which is
 *  annotated with a position in the input file.
 */
public abstract class Syntax {
    protected Position pos;

    public Syntax(Position pos) {
        this.pos = pos;
    }

    /** Returns the position of this syntactic element.
     */
    public Position getPos() {
        return pos;
    }

	public TypeContract getContract() {
		// TODO Auto-generated method stub
		return null;
	}
}
