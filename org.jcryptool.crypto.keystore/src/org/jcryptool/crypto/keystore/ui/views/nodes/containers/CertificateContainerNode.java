// -----BEGIN DISCLAIMER-----
/*******************************************************************************
 * Copyright (c) 2017 JCrypTool Team and Contributors
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// -----END DISCLAIMER-----
package org.jcryptool.crypto.keystore.ui.views.nodes.containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.jcryptool.crypto.keystore.KeyStorePlugin;
import org.jcryptool.crypto.keystore.keys.IKeyStoreAlias;
import org.jcryptool.crypto.keystore.ui.views.interfaces.IKeyStoreListener;
import org.jcryptool.crypto.keystore.ui.views.nodes.ContactManager;
import org.jcryptool.crypto.keystore.ui.views.nodes.keys.AbstractKeyNode;
import org.jcryptool.crypto.keystore.ui.views.nodes.keys.CertificateNode;

public class CertificateContainerNode extends AbstractContainerNode {

    private List<AbstractKeyNode> nodes = new ArrayList<AbstractKeyNode>();

    public CertificateContainerNode() {
        super(Messages.getString("Label.Certificates")); //$NON-NLS-1$
    }

    /**
     * @see org.jcryptool.crypto.keystore.ui.views.nodes.TreeNode#getImageDescriptor()
     */
    public ImageDescriptor getImageDescriptor() {
        return KeyStorePlugin.getImageDescriptor("icons/16x16/kgpg_identity.png"); //$NON-NLS-1$
    }

    public void add(IKeyStoreAlias alias) {
        AbstractKeyNode child = new CertificateNode(alias);
        nodes.add(child);
        addChild(child);
        Iterator<IKeyStoreListener> it = ContactManager.getInstance().getKeyStoreListeners();
        while (it.hasNext()) {
            it.next().fireKeyStoreModified(this);
        }
    }

    public void remove(IKeyStoreAlias alias) {
        AbstractKeyNode child = new CertificateNode(alias);
        nodes.remove(child);
        removeChild(child);
        Iterator<IKeyStoreListener> it = ContactManager.getInstance().getKeyStoreListeners();
        while (it.hasNext()) {
            it.next().fireKeyStoreModified(this);
        }
    }

}
