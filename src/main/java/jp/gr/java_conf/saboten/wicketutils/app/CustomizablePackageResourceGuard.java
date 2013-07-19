package jp.gr.java_conf.saboten.wicketutils.app;

import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.markup.html.PackageResourceGuard;

public class CustomizablePackageResourceGuard extends PackageResourceGuard {

	/** Set of extensions that are denied access. */
	private Set<String> blockedExtensions = new HashSet<String>();

	/** Set of filenames that are denied access. */
	private Set<String> blockedFiles = new HashSet<String>();


	public CustomizablePackageResourceGuard addBlockExtension(String extName) {
		blockedExtensions.add(extName);
		return this;
	}

	public CustomizablePackageResourceGuard addBlockFile(String filename) {
		blockedFiles.add(filename);
		return this;
	}

	protected boolean acceptExtension(String extension) {
		return (!blockedExtensions.contains(extension));
	}

	protected boolean acceptFile(String file) {
		return (!blockedFiles.contains(file));
	}
}
