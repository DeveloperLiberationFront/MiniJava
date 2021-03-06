// Copyright (c) Mark P Jones, Portland State University
// Subject to conditions of distribution and use; see LICENSE for details
// February 3 2008 11:12 AM

package codegen;

import syntax.ClassType;
import checker.FieldEnv;
import checker.MethEnv;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

/** Provides a simple mechanism for assembly language output
 */
public class Assembly {
    /** Set this flag to true for an mjc implementation on Linux.
     *  Some minor changes in the format of generated assembly code
     *  are needed to accomodate platform differences between Linux
     *  and Windows.  Moreover, Linux seems to object to programs
     *  that trash ebx, so we avoid using that register on Linux boxes.
     */
    public static final boolean LINUX = true;

    private PrintWriter out;

    public Assembly(PrintWriter out) {
        this.out = out;
    }

    /** A convenience method that creates an assembly object for
     *  a named output file.
     */
    public static Assembly assembleToFile(String name) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(name));
            return new Assembly(out);
        } catch (IOException e) {
            return null;
        }
    }

    /** Close this Assembly object and free up associated resources.
     */
    public void close() {
        out.close();
        out = null;
    }

    //- Items related to labels ----------------------------------------------

    private int labelCounter = 0;

    public String label(int l) {
        return "l" + l;
    }

    public String newLabel() {
        return label(labelCounter++);
    }

    public void emitLabel(String name) {
        out.print(name);
        out.println(":");
    }

    public String name(String n) {
        return LINUX ? n : ("_" + n);
    }

    public String mangle(String prefix, String suffix) {
        return name(prefix + "_" + suffix);
    }

    //- Items related to registers -------------------------------------------

    private String[] regs
      //= LINUX ? new String[] { "%eax",         "%ecx", "%esi", "%edi" }
      //        : new String[] { "%eax", "%ebx", "%ecx", "%esi", "%edi" };
        =         new String[] { "%eax",         "%ecx", "%esi", "%edi" };

    private int numRegs   = regs.length;

    public String reg(int r) {
        return regs[r % numRegs];
    }

    public boolean spillFor(int n) {
        return (n>=numRegs);
    }

    public void spill(int free) {
        if (spillFor(free)) {
            emit("pushl", reg(free));
        }
    }

    public void unspill(int free) {
        if (spillFor(free)) {
            emit("popl", reg(free));
        }
    }

    public void spillAll(int free) {
        int toSpill = Math.min(free, numRegs-1);
        for (int i=1; i<=toSpill; i++) {
            emit("pushl", reg(free-i));
        }
    }

    public void unspillAll(int free) {
        int toSpill = Math.min(free, numRegs-1);
        for (int i=toSpill; i>=1; i--) {
            emit("popl", reg(free-i));
        }
    }

    public void call(String lab, int free, int size) {
        emit("call", lab);
        if ((free%numRegs)!=0) {
            emit("movl", reg(0), reg(free));
        }
        if (size>0) {
            emit("addl", immed(size), "%esp");
        }
    }

    //- Items related to object layout ---------------------------------------

    public final static int WORDSIZE = 4;

    public void emitVTable(ClassType cls, int width, MethEnv[] vtab) {
        emitLabel(vtName(cls));
        emit(".long", number(width));
        if (vtab!=null) {
            for (int i=0; i<vtab.length; i++) {
                emit(".long", vtab[i].methName(this));
            }
        }
    }

    public int vtOffset(int slot) {
        return WORDSIZE * (slot + 1);
    }

    /** Load the "this" value into a specified register.  The size of the
     *  frame is used to allow us to compute the position of this.
     */
    public void loadThis(int size, int free) {
        emit("movl", indirect(thisOffset(size), "%ebp"), reg(free));
    }

    //- emit individual instructions, with varying numbers of arguments ------

    public void emit(String op) {
        out.println("\t" + op);
    }
    public void emit(String op, String op1) {
        out.println("\t" + op + "\t" + op1);
    }
    public void emit(String op, String op1, String op2) {
        out.println("\t" + op + "\t" + op1 + "," + op2);
    }

    public String number(int v) {
        return Integer.toString(v);
    }
    public String immed(int v) {
        return "$" + v;
    }
    public String indirect(int n, String s) {
        if (n==0) {
            return "(" + s + ")";
        } else {
            return n + "(" + s + ")";
        }
    }
    public String aindirect(String s) {
        return "*" + s;
    }
    public String vtName(ClassType cls) {
        return name(cls.getVTName());
    }
    public String vtAddr(ClassType cls) {
        return "$" + vtName(cls);
    }

    //- Items related to data layout -----------------------------------------

    public static final int FRAMEHEAD = 2*WORDSIZE;

    public int thisOffset(int sizeParams) {
        return (FRAMEHEAD + sizeParams) - WORDSIZE;
    }

    public void emitStart(String filename) {
        emit(".file",  "\"" + filename + "\"");
        emit(".globl", mangle("Main", "main"));
    }

    public void emitPrologue(String fname, int localBytes) {
        emitLabel(fname);
        emit("pushl", "%ebp");
        emit("movl",  "%esp", "%ebp");
        if (localBytes>0) {
            emit("subl", immed(localBytes), "%esp");
        }
    }

    public void emitEpilogue() {
        emit("movl", "%ebp", "%esp");
        emit("popl", "%ebp");
        emit("ret");
    }

    public void emitVar(FieldEnv env, int size) {
        emit(".comm", env.staticName(this), number(size));
    }
}
