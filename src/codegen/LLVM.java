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

package codegen;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Stack;

import org.llvm.Builder;
import org.llvm.Context;
import org.llvm.LLVMException;
import org.llvm.Module;
import org.llvm.TypeRef;
import org.llvm.Value;

import syntax.ClassType;
import syntax.StringLiteral;
import syntax.Type;
import checker.FieldEnv;

public class LLVM {
    static public enum GlobalFn {
        NEW_OBJECT,
        NEW_ARRAY,
        PUTC,
        GCROOT,
        GCGBLROOT,
        GCASSIGN,
        DIE,
    };
    class LocalStackVar {
        private Value v;
        private String name;
        private int depth;
        private TypeRef t;
        private boolean needsCleanup;
        public LocalStackVar(int depth, boolean needsCleanup, TypeRef t, String name,
                             Value v) {
            this.t = t;
            this.v = v;
            this.name = name;
            this.depth = depth;
            this.needsCleanup = needsCleanup;
        }
        public void cleanUp(LLVM l) {
            if (needsCleanup) {
                getBuilder().buildStore(t.constNull(), v);
            }
        }
        public Value getValue() {
            return v;
        }
        public int getDepth() {
            return depth;
        }
        public String getName() {
            return name;
        }
        public TypeRef getType() {
            return t;
        }
    }

    private Builder builder;
    private Module module;
    private Value function;

    private Stack<Hashtable<String, LocalStackVar>> localVars;
    private Value [] globalFns;

    public LLVM() {
        localVars = new Stack<Hashtable<String, LocalStackVar>>();
        globalFns = new Value[GlobalFn.values().length];
    }

    public Value getGlobalFn(GlobalFn g) {
        return globalFns[g.ordinal()];
    }

    public void gcAssign(org.llvm.Value v) {
        org.llvm.Value res = getBuilder().buildBitCast(v,
                             TypeRef.int8Type().pointerType(), "gcassign");
        getBuilder().buildCall(getGlobalFn(LLVM.GlobalFn.GCASSIGN), "",
                               new org.llvm.Value[] {res});
    }

    public Value getNamedValue(String s) {
        Hashtable<String, LocalStackVar> locals = localVars.peek();
        LocalStackVar local = locals.get(s);
        return local.getValue();
    }
    public void setNamedValue(boolean needsCleanup, TypeRef t, String s, Value v) {
        Hashtable<String, LocalStackVar> locals = localVars.peek();
        v.setValueName(s);
        locals.put(s, new LocalStackVar(localVars.size(), needsCleanup, t, s, v));
    }
    public void enterScope() {
        Hashtable<String, LocalStackVar> previous = null;
        Hashtable<String, LocalStackVar> current = new
        Hashtable<String, LocalStackVar>();
        if (localVars.size() > 0) {
            previous = localVars.peek();
        }
        localVars.push(current);
        if (previous != null) {
            for (LocalStackVar p : previous.values()) {
                current.put(p.getName(), p);
            }
        }
    }
    public void leaveScope() {
        for (LocalStackVar p : localVars.peek().values()) {
            if (p.getDepth() >= localVars.size()) {
                p.cleanUp(this);
            }
        }
        localVars.pop();
    }
    public void setBuilder(Builder b) {
        builder = b;
    }
    public Builder getBuilder() {
        return builder;
    }

    public void setFunction(Value f) {
        this.function = f;
    }

    public Value getFunction() {
        return function;
    }

    public Module getModule() {
        return module;
    }
    public void setModule(Module module) {
        this.module = module;
    }


    public void buildGlobalFns(Module mod) {
        TypeRef [] args = {Type.CHAR.llvmType()};
        TypeRef printf_type = TypeRef.functionType(TypeRef.voidType(), false,
                              Arrays.asList(args));

        globalFns[GlobalFn.PUTC.ordinal()] = mod.addFunction("MJC_putc", printf_type);

        TypeRef [] malloc_args = {TypeRef.int32Type()};
        TypeRef malloc_type = TypeRef.functionType(TypeRef.int8Type().pointerType(),
                              malloc_args);

        globalFns[GlobalFn.NEW_OBJECT.ordinal()] = mod.addFunction("MJC_allocObject",
                malloc_type);

        TypeRef [] malloc_array_args = {TypeRef.int32Type(), TypeRef.int32Type()};
        TypeRef malloc_array_type = TypeRef.functionType(
                                        TypeRef.int8Type().pointerType(),
                                        malloc_array_args);
        globalFns[GlobalFn.NEW_ARRAY.ordinal()] = mod.addFunction("MJC_allocArray",
                malloc_array_type);


        TypeRef [] gcglobalroot_args = {Type.PTR.llvmType()};
        TypeRef gcglobalroot_type = TypeRef.functionType(TypeRef.voidType(),
                                    gcglobalroot_args);
        globalFns[GlobalFn.GCGBLROOT.ordinal()] = mod.addFunction("MJC_globalRoot",
                gcglobalroot_type);

        globalFns[GlobalFn.GCASSIGN.ordinal()] = mod.addFunction("MJC_assign",
                gcglobalroot_type);

        TypeRef [] die_args = {};
        TypeRef die_type = TypeRef.functionType(Type.VOID.llvmType(), die_args);
        globalFns[GlobalFn.DIE.ordinal()] = mod.addFunction("MJC_die", die_type);

        TypeRef [] gcroot_args = {Type.PTR.llvmType().pointerType(), Type.PTR.llvmType()};
        TypeRef gcroot_type = TypeRef.functionType(TypeRef.voidType(), gcroot_args);
        globalFns[GlobalFn.GCROOT.ordinal()] = mod.addFunction("llvm.gcroot",
                                               gcroot_type);
    }

    public void markGCRoot(Value v, Type type) {
        Builder b = getBuilder();
        if (type.isClass() != null || type.isArray() != null) {
            org.llvm.Value res = b.buildBitCast(v,
                                                TypeRef.int8Type().pointerType().pointerType(), "gctmp");
            org.llvm.Value meta =
                TypeRef.int8Type().pointerType().constNull();  // TODO: replace with type data
            org.llvm.Value [] args = {res, meta};
            b.buildCall(getGlobalFn(LLVM.GlobalFn.GCROOT), "", args);
        }
    }

    public void llvmGen(ClassType [] classes, StringLiteral [] strings,
                        String output_path, Boolean dump) {
        Module mod = Module.createWithName("llvm_module");
        setModule(mod);
        buildGlobalFns(mod);
        //Entering a "GLOBAL" Scope, the home of static vars.
        enterScope();
        ClassType string = null;
        ClassType char_arr = null;
        for (ClassType c : classes) {
            c.llvmGenTypes(this);
        }

        Builder builder = Builder.createBuilderInContext(Context.getModuleContext(mod));
        setBuilder(builder);

        for (StringLiteral s : strings) {
            s.emitStaticString(this);
        }

        TypeRef gbl_ptr = Type.PTR.llvmType().pointerType();
        ArrayList<Value> global_roots_entries = new ArrayList<Value>();
        for (ClassType c : classes) {
            if (c.getFields() != null) {
                for (FieldEnv f : c.getFields()) {
                    if (f.isStatic()) {
                        org.llvm.Value v = f.getStaticField();
                        global_roots_entries.add(getBuilder().buildBitCast(v,
                                                 gbl_ptr, "gcgbltmp"));
                    }
                }
            }
        }

        Value global_roots = getModule().addGlobal(gbl_ptr.arrayType(
                                 global_roots_entries.size()), "MJCglobal_roots");
        global_roots.setInitializer(Value.constArray(gbl_ptr, global_roots_entries));
        Value global_roots_size = getModule().addGlobal(Type.INT.llvmType(),
                                  "MJCglobal_roots_size");
        global_roots_size.setInitializer(Type.INT.llvmType().constInt(
                                             global_roots_entries.size(), true));

        for (ClassType c : classes) {
            c.llvmGen(this);
        }

        /* Do NOT leave the global scope */
        //leaveScope();
        try {
            if (dump) {
                mod.dumpModule();
            }
            mod.verify();
            if (output_path != null) {
                System.out.println("Writing LLVM Bitcode to " + output_path);
                mod.writeBitcodeToFile(output_path);
            }
        } catch (LLVMException e) {
            System.out.println(e.getMessage());
        }
    }
}
