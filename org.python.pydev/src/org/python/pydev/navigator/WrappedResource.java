package org.python.pydev.navigator;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IContributorResourceAdapter;


public class WrappedResource<X> implements IWrappedResource, IContributorResourceAdapter, IAdaptable{

	protected Object parentElement;
	protected X actualObject;
	protected PythonSourceFolder pythonSourceFolder;
    protected int rank;

	public WrappedResource(Object parentElement, X actualObject, PythonSourceFolder pythonSourceFolder, int rank) {
		this.parentElement = parentElement;
		this.actualObject = actualObject;
		this.pythonSourceFolder = pythonSourceFolder;
		this.pythonSourceFolder.addChild(actualObject, this);
        this.rank = rank;
	}
	
	public X getActualObject() {
		return actualObject;
	}

	public Object getParent() {
		return parentElement;
	}

	public PythonSourceFolder getSourceFolder() {
		return pythonSourceFolder;
	}
    
    public int getRank() {
        return rank;
    }

    public IResource getAdaptedResource(IAdaptable adaptable) {
        return (IResource) getActualObject();
    }

    public Object getAdapter(Class adapter) {
        if(adapter == IContributorResourceAdapter.class){
            return this;
        }
        Object ret = ((IResource)this.getActualObject()).getAdapter(adapter);
        return ret;
    }


}