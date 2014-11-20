package com.iconmaster.srcbox.library;

import com.iconmaster.source.compile.DataType;
import com.iconmaster.source.compile.Operation;
import com.iconmaster.source.prototype.Field;
import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.Iterator;
import com.iconmaster.source.prototype.ParamTypeDef;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.prototype.TypeDef;
import com.iconmaster.srcbox.execute.Executor;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class LibraryCore extends SourcePackage {

	public LibraryCore() {
		this.name = "core";
		
		this.addType(TypeDef.UNKNOWN);
		this.addType(TypeDef.REAL);
		this.addType(TypeDef.STRING);
		this.addType(TypeDef.LIST);
		this.addType(TypeDef.INT);
		this.addType(TypeDef.BOOLEAN);
		
		Function fn = Function.libraryFunction("print", new String[] {"item"}, new TypeDef[] {TypeDef.UNKNOWN}, null);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			exc.println(args[1]);
			return null;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("print", new String[] {"item"}, new TypeDef[] {TypeDef.UNKNOWN}, null);
		fn.getDirectives().add("append");
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			exc.print(args[1]);
			return null;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("range", new String[] {"begin","end"}, new TypeDef[] {TypeDef.REAL,TypeDef.REAL}, TypeDef.LIST);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			ArrayList a = new ArrayList();
			for (double i=(Double)args[1];i<=(Double)args[2];i++) {
				a.add(i);
			}
			return a;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("range", new String[] {"begin","end","step"}, new TypeDef[] {TypeDef.REAL,TypeDef.REAL,TypeDef.REAL}, TypeDef.LIST);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			ArrayList a = new ArrayList();
			for (double i=(Double)args[1];i<=(Double)args[2];i+=(Double)args[3]) {
				a.add(i);
			}
			return a;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("list.size", new String[] {"list"}, new TypeDef[] {TypeDef.LIST}, TypeDef.INT);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return ((ArrayList)args[1]).size();
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("list.append", new String[] {"list","item"}, new TypeDef[] {TypeDef.LIST,TypeDef.UNKNOWN}, TypeDef.LIST);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			ArrayList a = (ArrayList) args[1];
			a.add(args[2]);
			return a;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("list.join", new String[] {"list1","list2"}, new TypeDef[] {TypeDef.LIST,TypeDef.LIST}, TypeDef.LIST);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			ArrayList a1 = (ArrayList) args[1];
			ArrayList a2 = (ArrayList) args[2];
			a1.addAll(a2);
			return a1;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("list.first", new String[] {"list"}, new TypeDef[] {TypeDef.LIST}, TypeDef.UNKNOWN);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			ArrayList a = (ArrayList) args[1];
			return a.get(0);
		};
		this.addFunction(fn);
		fn = Function.libraryFunction("list.last", new String[] {"list"}, new TypeDef[] {TypeDef.LIST}, TypeDef.UNKNOWN);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			ArrayList a = (ArrayList) args[1];
			return a.get(a.size()-1);
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("string._cast", new String[] {"item"}, new TypeDef[] {TypeDef.UNKNOWN}, TypeDef.STRING);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return String.valueOf(args[1]);
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("real._cast", new String[] {"item"}, new TypeDef[] {TypeDef.STRING}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return Double.parseDouble((String) args[1]);
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("real._cast", new String[] {"item"}, new TypeDef[] {TypeDef.INT}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return ((Integer) args[1]).doubleValue();
		};
		this.addFunction(fn);

		fn = Function.libraryFunction("int._cast", new String[] {"item"}, new TypeDef[] {TypeDef.STRING}, TypeDef.INT);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return Integer.parseInt((String) args[1]);
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("int._cast", new String[] {"item"}, new TypeDef[] {TypeDef.REAL}, TypeDef.INT);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return ((Double) args[1]).intValue();
		};
		this.addFunction(fn);
		
		Field f = Field.libraryField("list.start", TypeDef.INT);
		f.onRun = (pkg,isGet,args)->{
			return 0;
		};
		this.addField(f);
		
		DataType ltdt = new DataType(TypeDef.LIST);
		TypeDef ltt = new ParamTypeDef("T", 0);
		ltdt.params = new DataType[] {new DataType(ltt)};
		
		Iterator iter = Iterator.libraryIterator("list.pairs", new String[] {"lst"}, new Object[] {ltdt}, new Object[] {TypeDef.INT, ltt});
		iter.rawParams = new ArrayList<>();
		iter.rawParams.add(new Field("T"));
		ArrayList<Operation> pairsOps = new ArrayList<>();
		pairsOps.add(new Operation(Operation.OpType.BEGIN));
		pairsOps.add(new Operation(Operation.OpType.DEF, TypeDef.INT, null, "R1"));
		pairsOps.add(new Operation(Operation.OpType.DEF, TypeDef.INT, null, "R2"));
		pairsOps.add(new Operation(Operation.OpType.DEF, ltt, null, "R3"));
		pairsOps.add(new Operation(Operation.OpType.DO));
		pairsOps.add(new Operation(Operation.OpType.CALL, TypeDef.INT, null, "R2","list.size","lst"));
		pairsOps.add(new Operation(Operation.OpType.MOVN, TypeDef.INT, null, "R1", "1"));
		pairsOps.add(new Operation(Operation.OpType.FORR, TypeDef.INT, null, "R0", "1", "R1", "R2"));
		pairsOps.add(new Operation(Operation.OpType.INDEX, ltt, null, "R3", "lst", "R0"));
		pairsOps.add(new Operation(Operation.OpType.RET, ltt, null, "R0", "R3"));
		pairsOps.add(new Operation(Operation.OpType.ENDB));
		pairsOps.add(new Operation(Operation.OpType.END));
		iter.setCompiled(pairsOps);
		this.addIterator(iter);
	}
	
}
