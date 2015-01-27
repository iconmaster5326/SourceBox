package com.iconmaster.sbcore.execute;

import com.iconmaster.source.compile.Operation;
import com.iconmaster.source.compile.Operation.OpType;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author iconmaster
 */
public class BlockHelper {
	public static class Block {
		public Operation op;
		
		public int doOp = -1;
		public int blockOp = -1;
		public int endOp = -1;
		public int elseOp = -1;

		@Override
		public String toString() {
			return "<"+doOp+" "+blockOp+" "+endOp+" "+elseOp+">";
		}
	}
	
	public static ArrayList<Block> getBlocks(ArrayList<Operation> ops) {
		if (ops==null) {
			return new ArrayList<>();
		}
		
		ArrayList<Block> a = new ArrayList<>();
		
		Stack<Block> s = new Stack<>();
		
		for (int i = 0;i<ops.size();i++) {
			Operation op = ops.get(i);
			
			if (op.op==OpType.DO) {
				s.push(new Block());
				s.peek().doOp = i;
			} else if (op.op.isBlockStarter()) {
				s.peek().blockOp = i;
				s.peek().op = op;
			} else if (op.op==OpType.ELSE) {
				s.peek().elseOp = i;
			} else if (op.op==OpType.ENDB) {
				s.peek().endOp = i;
				a.add(s.pop());
			}
		}
		
		ArrayList<Block> a2 = new ArrayList<>();
		for (int i = 0;i<ops.size();i++) {
			boolean found = false;
			for (Block b : a) {
				if (b.doOp==i || b.blockOp==i || b.endOp == i || b.elseOp == i) {
					a2.add(b);
					found = true;
					break;
				}
			}
			if (!found) {
				a2.add(null);
			}
		}
		
		return a2;
	}
}
