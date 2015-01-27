package com.iconmaster.srcbox;

import com.iconmaster.sbcore.SourceBoxCore;
import com.iconmaster.sbcore.execute.VirtualMachine;
import com.iconmaster.source.assemble.AssembledOutput;
import com.iconmaster.source.link.Platform;
import com.iconmaster.source.link.platform.PlatformLoader.LoadedPlatform;
import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.srcbox.gui.BoxOutputStream;

/**
 *
 * @author iconmaster
 */
@LoadedPlatform
public class SourceBox extends Platform {
	
	public SourceBox() {
		this.name = "SourceBox";
		
		SourceBoxCore.registerLibs(this);
	}

	@Override
	public boolean canAssemble() {
		return false;
	}

	@Override
	public boolean canRun() {
		return true;
	}

	@Override
	public AssembledOutput assemble(SourcePackage pkg) {
		return null;
	}

	@Override
	public Object run(SourcePackage pkg) {
		final VirtualMachine vm = new VirtualMachine(pkg);
		final Function main = SourceBoxCore.getMainFunction(pkg);
		if (main!=null) {
			new Thread(() -> {
				vm.outputStream = new BoxOutputStream(false);
				vm.errorStream = new BoxOutputStream(true);
			}).start();
			new Thread(() -> {
				vm.loadFunction(main);
				vm.run();
			}).start();
		}
		return null;
	}
}
