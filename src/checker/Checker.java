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


package checker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import lexer.MjcLexer;
import syntax.ClassType;
import syntax.Parser;

import compiler.Failure;
import compiler.Handler;
import compiler.JavaSource;
import compiler.Position;
import compiler.SimpleHandler;
import compiler.Source;
import compiler.SourcePosition;

/** A top-level driver for the mini Java checker.
 */
public class Checker {

    /** A command line entry point to the mini Java checker.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: java mjc.Compiler inputFile");
            return;
        }
        String  inputFile = args[0];
        Handler handler   = new SimpleHandler();
        try {
            Reader reader = new FileReader(inputFile);
            check(handler, reader, inputFile);
        } catch (FileNotFoundException e) {
            // not an AST error
            handler.report(new Failure("Cannot open input file " +
                                       inputFile));
        }
    }

    /** A method for invoking the compiler without going through any
     *  particular user interface.
     */
    static void check(Handler handler, Reader reader, String inputFile) {
        Source fake = new JavaSource(null, "<MJCInternal>", null);
        Position fake_pos = new SourcePosition(fake, 0, 0);
        Source      source  = new JavaSource(handler, inputFile, reader);
        MjcLexer    lexer   = new MjcLexer(handler, source);
        Parser      parser  = new Parser(handler, lexer);
        ClassType[] classes = parser.getClasses();
        if (new Context(fake_pos, handler, classes).check() != null) {
            System.out.println(
                "No static errors found in \"" + inputFile + "\"");
        }
    }
}
