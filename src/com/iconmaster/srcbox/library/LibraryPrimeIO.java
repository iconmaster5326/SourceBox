package com.iconmaster.srcbox.library;

import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.prototype.TypeDef;
import com.iconmaster.srcbox.execute.Executor;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author iconmaster
 */
public class LibraryPrimeIO extends SourcePackage {

	public LibraryPrimeIO() {
		this.name = "prime.io";
		
		Function fn = Function.libraryFunction("wait", new String[] {"time"}, new TypeDef[] {TypeDef.REAL}, null);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			Double dmillis = ((Double)args[1])*1000;
			long millis = dmillis.longValue();
			exc.excStack.peek().wait = new Runnable() {
				public long milli = millis;
				
				@Override
				public void run() {
					milli--;
					if (milli==0) {
						exc.excStack.peek().wait = null;
					}
				}
			};
			return null;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("waitForInput", new String[] {}, new TypeDef[] {}, TypeDef.UNKNOWN);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];

			return null;
		};
		this.addFunction(fn);
		
		final Random r = new Random();
		fn = Function.libraryFunction("isKeyDown", new String[] {"key"}, new TypeDef[] {TypeDef.REAL}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			return r.nextDouble()<.2;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("msgbox", new String[] {"msg"}, new TypeDef[] {TypeDef.STRING}, null);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			JOptionPane.showMessageDialog(null, (String) args[1], "SourceBox Message", JOptionPane.INFORMATION_MESSAGE);
			return null;
		};
		this.addFunction(fn);
	}
}
