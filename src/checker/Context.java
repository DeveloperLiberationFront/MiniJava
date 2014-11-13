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

import java.util.ArrayList;
import java.util.Hashtable;

import notifications.MainMethodVoidError;
import syntax.Args;
import syntax.ArrayLiteral;
import syntax.Block;
import syntax.ClassType;
import syntax.ConstructorInvocation;
import syntax.Decls;
import syntax.ExprStmt;
import syntax.Id;
import syntax.IntLiteral;
import syntax.InterfaceType;
import syntax.LocalVarDecl;
import syntax.MethDecl;
import syntax.Modifiers;
import syntax.Name;
import syntax.NameInvocation;
import syntax.NameType;
import syntax.Return;
import syntax.Statement;
import syntax.StringLiteral;
import syntax.Type;
import syntax.VarDecls;

import compiler.Diagnostic;
import compiler.Failure;
import compiler.Handler;
import compiler.NameClashDiagnostic;
import compiler.Phase;
import compiler.Position;

/** Provides a representation for contexts used during type checking.
 */
public final class Context extends Phase {
    private ClassType[] classes;
    private ClassType   currClass;
    private MethEnv     currMethod;
    private int         localBytes;
    private Position pos;
    private ClassType staticClass;
    private Hashtable<String, StringLiteral> uniqueStrings;
    private int staticStringCount;
    private Id static_class_id;

    public Context(Position pos, Handler handler, ClassType[] classes) {
        super(handler);
        this.classes = classes;
        this.pos = pos;
        this.uniqueStrings = new Hashtable<String, StringLiteral>();
        this.staticStringCount = 0;
        this.static_class_id = new Id(pos, "MJCStatic");
    }
    public StringLiteral [] getUniqueStrings() {
        return uniqueStrings.values().toArray(new StringLiteral[0]);
    }
    public StringLiteral addStringLiteral(StringLiteral s) {
        StringLiteral result = null;
        if ((result = uniqueStrings.get(s.getString())) == null) {
            staticStringCount++;
            uniqueStrings.put(s.getString(), s);
            result = s;
        }
        return result;
    }

    public ClassType [] getClasses() {
        return classes;
    }
    /** Look for the definition of a class by its name.
     */
    public ClassType findClass(String name) {
        for (int i = 0; i < classes.length; i++) {
            if (name.equals(classes[i].getId().getName())) {
                return classes[i];
            }
        }
        return null;
    }

    /** Set the current class for this context.
     */
    public void setCurrClass(ClassType currClass) {
        this.currClass = currClass;
    }

    /** Return the current class for this context.
     */
    public ClassType getCurrClass() {
        return currClass;
    }

    /** Set the current method for this context.
     */
    public void setCurrMethod(MethEnv currMethod) {
        this.currMethod = currMethod;
        this.localBytes = 0;
    }

    /** Get the current method for this context.
     */
    public MethEnv getCurrMethod() {
        return currMethod;
    }

    /** Return boolean to indicate if this is a static context or an
     *  instance context (in which case, a this object will be present).
     */
    public boolean isStatic() {
        return currMethod == null || currMethod.isStatic();
    }

    /** Indicate whether any failures have been reported to this context.
     */
    public boolean noFailures() {
        // failure processor, not thrower
        return getHandler().getNumFailures() == 0;
    }

    /** Run static analysis checks on this context, returning a pointer
     *  to the entry point if all of the checks pass.
     */
    public MethEnv check() {
        return classes != null
               && checkClasses()
               && checkMembers() ? checkMain() : null;
    }

    /** Top-level checking of the class declarations that are included
     *  in the input program.
     */
    private boolean checkClasses() {
        ClassType[] classes = this.classes;
        for (int i = classes.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (classes[i].getId().sameId(classes[j].getId())) {
                    report(new NameClashDiagnostic(classes[i].getId(), classes[j].getId()));
                    break;
                }
            }
            try {
                classes[i].checkClass(this);
            } catch (Diagnostic d) {
                report(d);
                return noFailures();
            }
        }
        for (ClassType outer_c : classes) {
            for (ClassType c : outer_c.possibleInstances()) {
                if (c.isInterface() != null) {
                    InterfaceType iface = c.isInterface();
                    iface.registerImplement(outer_c);
                }
            }
        }
        for (ClassType c : classes) {
            if (c.isInterface() != null) {
                InterfaceType iface = c.isInterface();
                iface.concreteMethods(this);
            }
        }
        if (staticClass == null) {
            Id method_id = new Id(pos, "init");
            Modifiers m = new Modifiers(pos);
            m.set(Modifiers.PUBLIC | Modifiers.STATIC);

            ArrayList<Statement> static_body = new ArrayList<Statement>();
            Decls decls = null;

            /* all static initialization of fields */
            for (ClassType c : classes) {
                if (c.getFields() != null) {
                    for (FieldEnv f : c.getFields()) {
                        Statement s = f.staticInit();
                        if (s != null) {
                            static_body.add(s);
                        }
                    }
                }
            }

            /* creating "Class" classes for all the classes */
            static_body.add(new LocalVarDecl(pos, new NameType(new Name(new Id(pos,
                                             "ClassPool"))),
                                             new VarDecls(new Id(pos, "class_pool"),
                                                     new NameInvocation(new Name(new Name(new Id(pos, "ClassPool")), new Id(pos,
                                                             "getInstance")), null))));

            for (ClassType c : classes) {
                static_body.add(
                    new ExprStmt(pos,
                                 new NameInvocation(new Name(new Name(new Id(pos, "class_pool")), new Id(pos,
                                                    "addClass")),
                                                    new Args(
                                                        new ConstructorInvocation(new Name(new Id(pos, "Class")),
                                                                new Args(new StringLiteral(pos, c.getId().getName()),
                                                                        new Args(new IntLiteral(pos, c.getClassId()),
                                                                                new Args(new ArrayLiteral(pos, new NameType(new Name(new Id(pos, "int[]"))),
                                                                                        c.instancesExpr()), null)))), null))));
            }

            static_body.add(new Return(pos));

            decls = new MethDecl(false, m, Type.VOID, method_id,
                                 null, new Block(pos, static_body.toArray(new Statement[0]))).link(decls);

            staticClass = new ClassType(m, static_class_id, null, new Type[0], decls);
            ClassType [] new_classes = new ClassType[classes.length + 1];
            int x = 0;
            for (ClassType c : classes) {
                new_classes[x] = c;
                x++;
            }
            new_classes[classes.length] = staticClass;
            this.classes = new_classes;
            try {
                staticClass.checkClass(this);
            } catch (Diagnostic d) {
                report(d);
            }
        } else {
            System.out.println("Static Initialization Class Already Exists");
        }
        return noFailures();
    }

    /** Detailed static analysis for each of the members (fields and
     *  methods) of the classes in this program.
     */
    private boolean checkMembers() {
        ClassType[] classes = this.classes;
        for (int i = 0; i < classes.length; i++) {
            setCurrClass(classes[i]);
            classes[i].checkMembers(this);
        }
        setCurrClass(null);
        return noFailures();
    }

    /** Checks to ensure that the program has a class method "main"
     *  in a class "Main" that takes no parameters and returns no
     *  result.
     */
    private MethEnv checkMain() {
        ClassType mainClass = findClass("Main");
        if (mainClass == null) {
        	report(new MissingRequiredNameDiagnostic(new ClassType(null, new Id(null, "Main"), null, null, null), null)); // needs representation of scope
//            report(new Failure(
//                       "Program does not contain a definition for class Main"));
        } else {
            MethEnv mainMeth = mainClass.findMethod("main");
            if (mainMeth == null) {
                report(new Failure("No method main in class Main"));
            } else if (!mainMeth.isStatic()) {
            	report(new MissingPermissionModifierDiagnostic(mainMeth, Modifiers.STATIC));
                report(new Failure(mainMeth.getPos(),
                                   "Main.main is not static"));
            } else if (!mainMeth.eqSig(Type.VOID, null)) {
            	report(new MainMethodVoidError(mainMeth, mainMeth.getType()));
//            	report(new TypeError(mainMeth.getDeclaration(), Type.VOID));
            } else {
                return mainMeth;
            }
        }
        return null;
    }

    /** Return the number of bytes that are needed for local variables.
     */
    public int getLocalBytes() {
        return localBytes;
    }

    /** Reserve space in the current frame for a local variable.
     */
    public void reserveSpace(int frameOffset) {
        if (localBytes + frameOffset < 0) {
            localBytes = (-frameOffset);
        }
    }
}
