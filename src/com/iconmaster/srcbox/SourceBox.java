package com.iconmaster.srcbox;

import com.iconmaster.source.assemble.AssembledOutput;
import com.iconmaster.source.link.Platform;
import com.iconmaster.source.link.platform.PlatformLoader.LoadedPlatform;
import com.iconmaster.source.prototype.SourcePackage;

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
		return null;
	}
}
