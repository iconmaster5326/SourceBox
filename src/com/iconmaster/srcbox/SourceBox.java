package com.iconmaster.srcbox;

import com.iconmaster.source.compile.CompileUtils;
import com.iconmaster.source.link.Platform;
import com.iconmaster.source.link.platform.PlatformLoader.LoadedPlatform;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.srcbox.execute.Executor;
import com.iconmaster.srcbox.library.LibraryCore;
import com.iconmaster.srcbox.library.LibraryMath;
import com.iconmaster.srcbox.library.LibraryPrimeDraw;
import com.iconmaster.srcbox.library.LibraryPrimeIO;

/**
 *
 * @author iconmaster
 */
@LoadedPlatform
public class SourceBox extends Platform {
	
	public SourceBox() {
		this.name = "SourceBox";
		
		this.registerLibrary(new LibraryCore());
		this.registerLibrary(new LibraryMath());
		
		this.registerLibrary(new LibraryPrimeDraw());
		this.registerLibrary(new LibraryPrimeIO());
		
		transforms.add(CompileUtils.gotoReplacer);
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
		exc.initFields();
		exc.executeConcurently(exc.GetMainFunction());
	}
}
