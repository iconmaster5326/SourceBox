package com.iconmaster.srcbox;

import com.iconmaster.source.compile.Operation;
import com.iconmaster.source.compile.Operation.OpType;
import com.iconmaster.source.prototype.Field;
import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.util.Directives;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author iconmaster
 */
public class Executor {
	public SourcePackage pkg;
	
	public ArrayList<BoxField> fields = new ArrayList<>();
	
	public Stack<Operation> loopBegins = new Stack<>();
	
	public BoxOutput output;
	public PrimeDrawOutput pdo;

	public Executor(SourcePackage pkg) {
		this.pkg = pkg;
	}
	
	public Function GetMainFunction() {
		for (Function fn : pkg.getFunctions()) {
			if (Directives.has(fn, "main")) {
				return fn;
			}
		}
		for (Function fn : pkg.getFunctions()) {
			if (Directives.has(fn, "export")) {
				return fn;
			}
		}
		return null;
	}
	
	public void initFields() {
		for (Field f : pkg.getFields()) {
			if (f.getValue()!=null) {
				execute(exprToFunction(f.getValue()));
			}
		}
	}
	
	public static Function exprToFunction(ArrayList<Operation> code) {
		Function fn = new Function("", new ArrayList<>(), null);
		fn.setCompiled(code);
		return fn;
	}
	
	public Object execute(Function fn, Object... fargs) {
		BoxFrame f = new BoxFrame(this);
		int argi = 0;
		for (Object arg : fargs) {
			f.putVar(fn.getArguments().get(argi).getName(), arg);
			argi++;
		}
		ArrayList<Operation> code = fn.getCode();
		
		for (int opn=0;opn<code.size();opn++) {
			Operation op = code.get(opn);
			switch (op.op) {
				case MOV:
					f.putVar(op.args[0], f.getVar(op.args[1]));
					break;
				case MOVN:
					f.putVar(op.args[0], Double.parseDouble(op.args[1]));
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
				case MOVI:
					Object lv = f.getVar(op.args[0]);
					Object rv = f.getVar(op.args[1]);
					ArrayList indices = new ArrayList();
					for (int i=2;i<op.args.length;i++) {
						indices.add(f.getVar(op.args[i]));
					}
					setIndex(lv, rv, indices);
					break;
				case CALL:
					String name = op.args[1];
					Function fn2 = pkg.getFunction(name);
					if (fn2.onRun!=null) {
						ArrayList args = new ArrayList();
						for (int i=2;i<op.args.length;i++) {
							args.add(f.getVar(op.args[i]));
						}
						args.add(0,this);
						Object res = fn2.onRun.run(pkg, args.toArray());
						f.putVar(op.args[0], res);
					} else {
						ArrayList<Object> argList = new ArrayList<>();
						for (int i=2;i<op.args.length;i++) {
							argList.add(f.getVar(op.args[i]));
						}
						Object res = execute(fn2, argList.toArray());
						f.putVar(op.args[0], res);
					}
					break;
				case INDEX:
					lv = f.getVar(op.args[1]);
					indices = new ArrayList();
					for (int i=2;i<op.args.length;i++) {
						indices.add(f.getVar(op.args[i]));
					}
					f.putVar(op.args[0], getIndex(lv, indices));
					break;
				case DEF:
					break;
				case BEGIN:
					f = new BoxFrame(this,f);
					break;
				case END:
					f = f.parent;
					break;
				case PROP:
					break;
				case NOP:
					break;
				case TYPE:
					break;
				case ADD:
					f.putVar(op.args[0], ((Double)f.getVar(op.args[1]))+((Double)f.getVar(op.args[2])));
					break;
				case SUB:
					f.putVar(op.args[0], ((Double)f.getVar(op.args[1]))-((Double)f.getVar(op.args[2])));
					break;
				case MUL:
					f.putVar(op.args[0], ((Double)f.getVar(op.args[1]))*((Double)f.getVar(op.args[2])));
					break;
				case DIV:
					f.putVar(op.args[0], ((Double)f.getVar(op.args[1]))/((Double)f.getVar(op.args[2])));
					break;
				case MOD:
					f.putVar(op.args[0], ((Double)f.getVar(op.args[1]))%((Double)f.getVar(op.args[2])));
					break;
				case POW:
					f.putVar(op.args[0], Math.pow(((Double)f.getVar(op.args[1])),((Double)f.getVar(op.args[2]))));
					break;
				case AND:
					f.putVar(op.args[0], toBool(f.getVar(op.args[1])) && toBool(f.getVar(op.args[2])));
					break;
				case OR:
					f.putVar(op.args[0], toBool(f.getVar(op.args[1])) || toBool(f.getVar(op.args[2])));
					break;
				case NOT:
					f.putVar(op.args[0], !toBool(f.getVar(op.args[1])));
					break;
				case NEG:
					f.putVar(op.args[0], -((Double)f.getVar(op.args[1])));
					break;
				case BAND:
					break;
				case BOR:
					break;
				case BNOT:
					break;
				case CONCAT:
					StringBuilder sb = new StringBuilder();
					sb.append(f.getVar(op.args[1]));
					sb.append(f.getVar(op.args[2]));
					f.putVar(op.args[0], sb.toString());
					break;
				case EQ:
					f.putVar(op.args[0], (f.getVar(op.args[1])).equals(f.getVar(op.args[2])));
					break;
				case NEQ:
					f.putVar(op.args[0], !(f.getVar(op.args[1])).equals(f.getVar(op.args[2])));
					break;
				case LT:
					f.putVar(op.args[0], ((Double)f.getVar(op.args[1])) < ((Double)f.getVar(op.args[2])));
					break;
				case GT:
					f.putVar(op.args[0], ((Double)f.getVar(op.args[1])) > ((Double)f.getVar(op.args[2])));
					break;
				case LE:
					f.putVar(op.args[0], ((Double)f.getVar(op.args[1])) <= ((Double)f.getVar(op.args[2])));
					break;
				case GE:
					f.putVar(op.args[0], ((Double)f.getVar(op.args[1])) >= ((Double)f.getVar(op.args[2])));
					break;
				case GOTO:
					opn = jumpTo(code, op.args[0]);
					break;
				case GOTOT:
					if (toBool(f.getVar(op.args[0]))) {
						opn = jumpTo(code, op.args[1]);
					}
					break;
				case GOTOF:
					if (!toBool(f.getVar(op.args[0]))) {
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
						return f.getVar(op.args[0]);
					} else {
						return null;
					}
			}

			System.out.println(opn);
		}
		return null;
	}
	
	public void setIndex(Object lv, Object rv, ArrayList indices) {
		Object index = indices.get(0);
		((ArrayList)lv).add(((Double)index).intValue(), rv);
	}
	
	public Object getIndex(Object lv, ArrayList indices) {
		Object index = indices.get(0);
		return ((ArrayList)lv).get(((Double)index).intValue());
	}
	
	public int jumpTo(ArrayList<Operation> code, String label) {
		for (int i=0;i<code.size();i++) {
			Operation op = code.get(i);
			if (op.op == OpType.LABEL && op.args[0].equals(label)) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean toBool(Object o) {
		return (o instanceof Double && !o.equals(0d)) || o.equals(true);
	}
	
	public void setupOutput() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(BoxOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(BoxOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(BoxOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(BoxOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>
		
		output = new BoxOutput();
		
		output.setVisible(true);
	}
	
	public void setupPrimeDrawOutput() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(BoxOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(BoxOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(BoxOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(BoxOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>
		
		pdo = new PrimeDrawOutput();
		
		pdo.setVisible(true);
	}
	
	public void updatePrimeDraw() {
		if (pdo==null) {
			setupPrimeDrawOutput();
		}
		
		pdo.repaint();
	}
	
	public void println(Object o) {
		if (output==null) {
			setupOutput();
		}
		output.setVisible(true);
		
		output.println(o);
	}

	public void print(Object o) {
		if (output==null) {
			setupOutput();
		}
		output.setVisible(true);
		
		output.printNoLn(o);
	}
	
	public BoxField getField(String name) {
		Field sf = pkg.getField(name);
		if (sf!=null && sf.onRun!=null) {
			return new BoxField(name, sf.onRun.run(pkg, true, this));
		}
		for (BoxField f : fields) {
			if (f.name.equals(name)) {
				return f;
			}
		}
		BoxField f = new BoxField(name, null);
		fields.add(f);
		return f;
	}
	
	public void setField(String name, Object value) {
		Field sf = pkg.getField(name);
		if (sf!=null && sf.onRun!=null) {
			sf.onRun.run(pkg, false, this);
			return;
		}
		for (BoxField f : fields) {
			if (f.name.equals(name)) {
				f.value = value;
				return;
			}
		}
		BoxField f = new BoxField(name, value);
		fields.add(f);
	}
}
