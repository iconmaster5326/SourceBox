package com.iconmaster.sbcore.execute;

import com.iconmaster.sbcore.library.CoreFunctions.CustomIterator;

/**
 *
 * @author iconmaster
 */
public class CustomIteratorExecutor extends Executor {
	FunctionExecutor creator;
	CustomIterator ci;
	SourceObject[] args;
	SourceObject[][] pairs;
	int pair = 0;

	public CustomIteratorExecutor(VirtualMachine vm, FunctionExecutor creator, CustomIterator ci, SourceObject... args) {
		this.vm = vm;
		this.creator = creator;
		this.ci = ci;
		this.args = args;
		
		pairs = ci.execute(vm, args);
	}

	@Override
	public void step() {
		if (pair>=pairs.length) {
			creator.pc = creator.blockStack.peek().endOp;
			creator.iterStack.pop();
			creator.done = false;
			done = true;
			return;
		}
		
		int i=0;
		for (String var : creator.iterStack.peek()) {
			creator.setVar(var, pairs[pair][i]);
			i++;
		}
		
		vm.loadExecutor(creator);
		creator.done = false;
		
		pair++;
	}
}
