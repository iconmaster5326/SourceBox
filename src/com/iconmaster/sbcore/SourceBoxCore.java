package com.iconmaster.sbcore;

import com.iconmaster.source.link.Platform;
import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.util.Directives;
import com.iconmaster.sbcore.library.CoreFunctions;
import com.iconmaster.sbcore.library.LibraryCore;

/**
 *
 * @author iconmaster
 */
public class SourceBoxCore {
	public static void registerLibs(Platform plat) {
		SourcePackage lib;
		
		lib = new LibraryCore();
		plat.registerLibrary(lib);
		CoreFunctions.registerFunctions(lib);
	}
	
	public static Function getMainFunction(SourcePackage pkg) {
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
		for (Function fn : pkg.getFunctions()) {
			return fn;
		}
		return null;
	}
}
