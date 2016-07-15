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

/** A public class that allows us to access the definitions of the
 *  Mjc tokens from outside the syntax package; this works around a
 *  design decision in jacc that restricts the generated token file
 *  to package level access only.
 */
public interface Tokens extends MjcTokens {
}

interface MjcTokens{
	static int BOOLEAN=1;
	static int CLASS=2;
	static int ELSE=3;
	static int EXTENDS=4;
	static int IF=5;
	static int INT=6;
	static int CHAR=7;
	static int NEW=8;
	static int RETURN=9;
	static int STATIC=10;
	static int PUBLIC=11;
	static int PRIVATE=12;
	static int PROTECTED=13;
	static int ABSTRACT=14;
	static int SUPER=15;
	static int THIS=16;
	static int VOID=17;
	static int WHILE=18;
	static int DO=19;
	static int NULL=20;
	static int TRUE=21;
	static int FALSE=22;
	static int PTR=23;
	static int INTERFACE=24;
	static int IMPLEMENTS=25;
	static int COR=26;
	static int ARRAY=27;
	static int CAND=28;
	static int CHARLIT=29;
	static int ENDINPUT=30;
	static int EQEQ=31;
	static int IDENT=32;
	static int NEQ=34;
	static int STRING=35;
	static int INTLIT=0;
}