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

import compiler.Diagnostic;
import syntax.Id;
import checker.Env;

/** Represents an error diagnostic.  To avoid a clash with java.lang.Error,
 *  we resisted the temptation to call this class Error.
 */
public class NameClashFailure extends CompoundDiagnostic {
    /** Construct a simple failure report with a fixed description.
     */
    public NameClashFailure(Id newId, Id prevId, Env env) {
        this.diagnostics = new Diagnostic[3];
        this.diagnostics[0] = new Failure(
            "Name clash in " + env.getId().toString()
            );
        this.diagnostics[0] = new Warning(
            newId.getName() + " clashes with " + prevId.getName()
            );
    }
}
