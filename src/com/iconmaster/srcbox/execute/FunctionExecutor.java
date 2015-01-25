package com.iconmaster.srcbox.execute;

import com.iconmaster.source.compile.Operation;
import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.util.StringUtils;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class FunctionExecutor {
	public static class ExecResult {
		public boolean done;
		public Object value;

		public ExecResult() {
			this.done = false;
			this.value = null;
		}
		
		public ExecResult(Object value) {
			this.done = true;
			this.value = value;
		}
	}
	
	public Executor exc;
	public SourcePackage pkg;
	
	public Function fn;
	public ArrayList<Operation> code;
	public int opn = 0;
	public BoxFrame f;
	
	public Runnable wait;
	public Object returnResult;
	public String returning = null;

	public FunctionExecutor(Executor exc, Function fn, Object... fargs) {
		this.exc = exc;
		this.fn = fn;
		this.pkg = exc.pkg;
		this.code = fn.getCode();
		this.f = new BoxFrame(exc);
		
		int argi = 0;
		for (Object arg : fargs) {
			f.putVar(fn.getArguments().get(argi).getName(), arg);
			argi++;
		}
	}
	
	public ExecResult execute() {
		if (wait!=null) {
			wait.run();
			return new ExecResult();
		}
		if (returning!=null) {
			f.putVar(returning, returnResult);
			returning = null;
		}
		if (opn>code.size()-1) {
			return new ExecResult(null);
		}
		Operation op = code.get(opn);
		System.out.println("STEP: function "+fn.getName()+" I "+opn+" OP "+op);
		System.out.println("\t"+f.vars);
		switch (op.op) {
			case MOV:
				f.putVar(op.args[0], f.getVar(op.args[1]));
				break;
			case MOVN:
				Number n;
				if (StringUtils.isReal(op.args[1])) {
					if (op.args[1].endsWith(".")) {
						op.args[1]+="0";
					}
					n = Double.parseDouble(op.args[1]);
				} else {
					n = Integer.parseInt(op.args[1]);
				}
				f.putVar(op.args[0], n);
				break;
			case MOVS:
				f.putVar(op.args[0], op.args[1]);
				break;
			case MOVL:
				ArrayList a = new ArrayList();
				for (int i=1;i<op.args.length;i++) {
					a.add(f.getVar(op.args[i]));
				}
				f.putVar(op.args[0], a);
				break;
			case CALL:
				String name = op.args[1];
				Function fn2 = pkg.getFunction(name);
//				if (fn2.onRun!=null) {
//					ArrayList args = new ArrayList();
//					for (int i=2;i<op.args.length;i++) {
//						args.add(f.getVar(op.args[i]));
//					}
//					args.add(0,exc);
//					Object res = fn2.onRun.run(pkg, args.toArray());
//					f.putVar(op.args[0], res);
//				} else {
					ArrayList<Object> argList = new ArrayList<>();
					for (int i=2;i<op.args.length;i++) {
						argList.add(f.getVar(op.args[i]));
					}
					exc.call(fn2, argList.toArray());
					returning = op.args[0];
//				}
				break;
			case BEGIN:
				f = new BoxFrame(exc,f);
				break;
			case END:
				f = f.parent;
				break;
			case PROP:
				break;
			case NOP:
				break;
			case GOTO:
				opn = jumpTo(code, op.args[0]);
				break;
			case GOTOT:
				if (exc.toBool(f.getVar(op.args[0]))) {
					opn = jumpTo(code, op.args[1]);
				}
				break;
			case GOTOF:
				if (!exc.toBool(f.getVar(op.args[0]))) {
					opn = jumpTo(code, op.args[1]);
				}
				break;
			case TRUE:
				f.putVar(op.args[0], true);
				break;
			case FALSE:
				f.putVar(op.args[0], false);
				break;
			case RET:
				if (op.args.length>0) {
					return new ExecResult(f.getVar(op.args[0]));
				} else {
					return new ExecResult(null);
				}
		}
		opn++;
		return new ExecResult();
	}
	
	public int jumpTo(ArrayList<Operation> code, String label) {
		for (int i=0;i<code.size();i++) {
			Operation op = code.get(i);
			if (op.op == Operation.OpType.LABEL && op.args[0].equals(label)) {
				return i;
			}
		}
		return -1;
	}
}
