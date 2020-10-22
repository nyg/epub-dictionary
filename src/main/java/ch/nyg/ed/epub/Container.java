package ch.nyg.ed.epub;

import ch.nyg.ed.model.container.RootFile;

public class Container {

    private final ch.nyg.ed.model.container.Container container;

    public Container() {
        container = new ch.nyg.ed.model.container.Container();
        container.setVersion(1);

        RootFile rootFile = new RootFile();
        rootFile.setFullPath("EPUB/content.opf"); // TODO dict name
        rootFile.setMediaType("application/oebps-package+xml");
        container.addRootFile(rootFile);
    }

    public ch.nyg.ed.model.container.Container getContainer() {
        return container;
    }
}
