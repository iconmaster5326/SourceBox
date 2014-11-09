package com.iconmaster.srcbox.execute;

import com.iconmaster.source.compile.Operation;
import com.iconmaster.source.prototype.Field;
import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.util.Directives;
import com.iconmaster.srcbox.execute.FunctionExecutor.ExecResult;
import com.iconmaster.srcbox.gui.BoxOutput;
import com.iconmaster.srcbox.gui.PrimeDrawOutput;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.Timer;

/**
 *
 * @author iconmaster
 */
public class Executor {
	public SourcePackage pkg;
	
	public ArrayList<BoxField> fields = new ArrayList<>();
	
	public BoxOutput output;
	public PrimeDrawOutput pdo;
	
	public Stack<FunctionExecutor> excStack = new Stack<>();

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
	
	public void call(Function fn, Object... fargs) {
		FunctionExecutor fexc = new FunctionExecutor(this, fn, fargs);
		excStack.add(fexc);
	}
	
	public Object execute(Function fn, Object... fargs) {
		call(fn, fargs);
		while (true) {
			ExecResult res = step();
			if (res.done && excStack.isEmpty()) {
				return res.value;
			}
		}
	}
	
	public void executeConcurently(Function fn, Object... fargs) {
		call(fn, fargs);
		final Executor thisExc = this;
		final Timer timer = new Timer(1, (e) -> {
			ExecResult res = thisExc.step();
			if (res.done && excStack.isEmpty()) {
				((Timer)e.getSource()).stop();
			}
		});
		timer.start();
	}

	public ExecResult step() {
		ExecResult res = excStack.peek().execute();
		if (res.done) {
			excStack.pop();
			if (!excStack.isEmpty()) {
				excStack.peek().returnResult = res.value;
			}
		}
		return res;
	}
	
	public void setIndex(Object lv, Object rv, ArrayList indices) {
		Object index = indices.get(0);
		((ArrayList)lv).add(((Number)index).intValue(), rv);
	}
	
	public Object getIndex(Object lv, ArrayList indices) {
		Object index = indices.get(0);
		return ((ArrayList)lv).get(((Number)index).intValue());
	}
	
	public boolean toBool(Object o) {
		return (o instanceof Number && ((Number)o).doubleValue()!=0d) || o.equals(true);
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
