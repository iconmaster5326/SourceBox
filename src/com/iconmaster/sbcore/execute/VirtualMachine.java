package com.iconmaster.sbcore.execute;

import com.iconmaster.sbcore.exception.SBError;
import com.iconmaster.source.compile.Expression;
import com.iconmaster.source.prototype.Field;
import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.SourcePackage;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author iconmaster
 */
public class VirtualMachine {
	public HashMap<String,SourceObject> fields = new HashMap<>();
	public SourcePackage pkg;
	public Stack<Executor> execs = new Stack<>();
	public Executor last;
	public boolean done = true;
	
	public OutputStream outputStream = System.out;
	public InputStream inputStream = System.in;
	public OutputStream errorStream = System.err;
	
	public SBError error;

	public VirtualMachine(SourcePackage pkg) {
		this.pkg = pkg;
		
		for (Field f : pkg.getFields()) {
			if (f.getValue()!=null) {
				Expression expr = f.getValue();
				Function fn = new Function("%field", new ArrayList<>(), null);
				fn.setCompiled(expr);
				
				loadFunction(fn);
				run();
			}
		}
	}
	
	public Executor exec() {
		return execs.peek();
	}
	
	public void loadFunction(Function fn, SourceObject... args) {
		execs.push(new FunctionExecutor(this, fn, args));
		done = false;
	}
	
	public void loadExecutor(Executor e) {
		execs.push(e);
		done = false;
	}
	
	public void step() {
		if (!done) {
			exec().step();
			if (exec().done) {
				ret();
			}
		}
		if (error!=null) {
			PrintWriter pw = new PrintWriter(errorStream);
			pw.println("ERROR: ");
			pw.println((error.range==null?"0~0":error.range)+": "+error.message);
			pw.flush();
			done = true;
		}
	}
	
	public void ret() {
		last = execs.pop();
		if (execs.isEmpty()) {
			done = true;
		}
	}
	
	public void run() {
		while (!done) {
			step();
		}
	}
}
