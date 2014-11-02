package com.iconmaster.srcbox;

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
		
		this.registerLibrary(new LibraryCore());
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
	public String assemble(SourcePackage pkg) {
		return null;
	}

	@Override
	public void run(SourcePackage pkg) {
		Executor exc = new Executor(pkg);
		exc.execute(exc.GetMainFunction());
	}

}
