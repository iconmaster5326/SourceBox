package com.iconmaster.sbcore.execute;

import com.iconmaster.source.compile.Operation;
import com.iconmaster.source.compile.Operation.OpType;
import com.iconmaster.source.prototype.Iterator;

/**
 *
 * @author iconmaster
 */
public class IteratorExecutor extends FunctionExecutor {
	public Iterator iter;
	public FunctionExecutor creator;

	public IteratorExecutor(VirtualMachine vm, FunctionExecutor creator, Iterator fn, SourceObject... args) {
		super(vm, fn, args);
		
		iter = fn;
		this.creator = creator;
	}

	@Override
	public void step() {
		if (pc<code.size()) {
			Operation op = code.get(pc);

			if (op.op==OpType.RET) {
				int i=0;
				for (String var : creator.iterStack.peek()) {
					creator.setVar(var, getVar(op.args[i]));
					i++;
				}
				vm.loadExecutor(creator);
				creator.done = false;

				incPC();
			} else {
				super.step();
				
				if (pc>=code.size()) {
					endIt();
				}
			}
		} else {
			endIt();
		}
	}
	
	public void endIt() {
		creator.pc = creator.blockStack.peek().endOp;
		creator.iterStack.pop();
		creator.done = false;
	}
	
}
