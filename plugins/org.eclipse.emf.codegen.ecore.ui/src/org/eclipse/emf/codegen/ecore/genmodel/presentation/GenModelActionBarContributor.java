/**
 * <copyright> 
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GenModelActionBarContributor.java,v 1.19 2006/05/04 12:23:34 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.presentation;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.ui.action.ViewerFilterAction;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
//
//import org.eclipse.emf.edit.ui.action.LoadResourceAction;
//import org.eclipse.emf.edit.ui.action.ValidateAction;
// import java.util.LinkedList;
// import org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind;
// import org.eclipse.jface.action.ActionContributionItem;
// import org.eclipse.jface.action.IAction;
// import org.eclipse.jface.action.IContributionManager;
// import org.eclipse.jface.action.SubContributionItem;


/**
 * This is the action bar contributor for the GenModel model editor.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated NOT
 */
public class GenModelActionBarContributor
  extends EditingDomainActionBarContributor
  implements ISelectionChangedListener
{
  /**
   * This keeps track of the active editor.
   */
  protected IEditorPart activeEditorPart;

  /**
   * This gets the selection from the active editor.
   */
  protected ISelection getActiveEditorSelection()
  {
    return activeEditorPart == null ? null :
      ((GenModelEditor)activeEditorPart).getSelection();
  }

  /**
   * This keeps track of the current selection provider.
   */
  protected ISelectionProvider selectionProvider;

  /**
   * This action opens the Properties view.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IAction showPropertiesViewAction =
    new Action(GenModelEditPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item"))
    {
      public void run()
      {
        try
        {
          getPage().showView("org.eclipse.ui.views.PropertySheet");
        }
        catch (PartInitException exception)
        {
          GenModelEditPlugin.INSTANCE.log(exception);
        }
      }
    };
      
  /**
   * This action refreshes the viewer of the current editor if the editor
   * implements {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IAction refreshViewerAction =
    new Action(GenModelEditPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item"))
    {
      public boolean isEnabled()
      {
        return activeEditorPart instanceof IViewerProvider;
      }

      public void run()
      {
        if (activeEditorPart instanceof IViewerProvider)
        {
          Viewer viewer = ((IViewerProvider)activeEditorPart).getViewer();
          if (viewer != null)
          {
            viewer.refresh();
          }
        }
      }
    };

  /**
   * This is the menu manager for the "Generate" menu.
   */
  protected IMenuManager generateMenuManager;

  protected IAction generateModelAction = new GenerateAction
    (GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE,
     CodeGenEcorePlugin.INSTANCE.getString("_UI_ModelProject_name"),
     GenModelEditPlugin.INSTANCE.getString("_UI_GenerateModel_menu_item"));

  protected IAction generateEditAction = new GenerateAction
    (GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE,
     CodeGenEcorePlugin.INSTANCE.getString("_UI_EditProject_name"),    
     GenModelEditPlugin.INSTANCE.getString("_UI_GenerateEdit_menu_item"));

  protected IAction generateEditorAction = new GenerateAction
    (GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE,
     CodeGenEcorePlugin.INSTANCE.getString("_UI_EditorProject_name"),
     GenModelEditPlugin.INSTANCE.getString("_UI_GenerateEditor_menu_item"));

  protected IAction generateTestsAction = new GenerateAction
    (GenBaseGeneratorAdapter.TESTS_PROJECT_TYPE ,
     CodeGenEcorePlugin.INSTANCE.getString("_UI_TestsProject_name"),
     GenModelEditPlugin.INSTANCE.getString("_UI_GenerateTests_menu_item"));

  protected IAction generateAllAction = new GenerateAction
  (new ProjectType[]
   {
     new ProjectType(GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, CodeGenEcorePlugin.INSTANCE.getString("_UI_ModelProject_name")),
     new ProjectType(GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE, CodeGenEcorePlugin.INSTANCE.getString("_UI_EditProject_name")),
     new ProjectType(GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE, CodeGenEcorePlugin.INSTANCE.getString("_UI_EditorProject_name")),
     new ProjectType(GenBaseGeneratorAdapter.TESTS_PROJECT_TYPE, CodeGenEcorePlugin.INSTANCE.getString("_UI_TestsProject_name"))
   },
   GenModelEditPlugin.INSTANCE.getString("_UI_GenerateAll_menu_item"));

/*
  protected IAction generateAction = new GenerateAction(GenModelEditPlugin.INSTANCE.getString("_UI_GenerateModel_menu_item"))
  {
    protected boolean canGenerate(GenBase genObject)
    {
      return genObject.canGenerate();
    }

    protected void generate(GenBase genObject, IProgressMonitor progressMonitor)
    {
      genObject.generate(progressMonitor);
    }
  };

  protected IAction generateEditAction = new GenerateAction(GenModelEditPlugin.INSTANCE.getString("_UI_GenerateEdit_menu_item"))
  {
    protected boolean canGenerate(GenBase genObject)
    {
      return genObject.canGenerateEdit();
    }

    protected void generate(GenBase genObject, IProgressMonitor progressMonitor)
    {
      genObject.generateEdit(progressMonitor);
    }  
  };

  protected IAction generateEditorAction = new GenerateAction(GenModelEditPlugin.INSTANCE.getString("_UI_GenerateEditor_menu_item"))
  {
    protected boolean canGenerate(GenBase genObject)
    {
      return genObject.canGenerateEditor();
    }

    protected void generate(GenBase genObject, IProgressMonitor progressMonitor)
    {
      genObject.generateEditor(progressMonitor);
    }  
  };
  
  protected IAction generateTestsAction = new GenerateAction(GenModelEditPlugin.INSTANCE.getString("_UI_GenerateTests_menu_item"))
  {
    protected boolean canGenerate(GenBase genObject)
    {
      return genObject.canGenerateTests();
    }
    
    protected void generate(GenBase genObject, IProgressMonitor progressMonitor)
    {
      genObject.generateTests(progressMonitor);
    }
  };
  
  protected IAction generateSchemaAction = new GenerateAction(GenModelEditPlugin.INSTANCE.getString("_UI_GenerateSchema_menu_item"))
  {
    protected boolean canGenerate(GenBase genObject)
    {
      return genObject.canGenerateSchema();
    }

    protected void generate(GenBase genObject, IProgressMonitor progressMonitor)
    {
      progressMonitor.beginTask("", 1);
      genObject.generateSchema(new SubProgressMonitor(progressMonitor, 1));
    }
  };  

  protected IAction generateAllAction = new GenerateAction(GenModelEditPlugin.INSTANCE.getString("_UI_GenerateAll_menu_item"))
  {
    protected boolean canGenerate(GenBase genObject)
    {
      return genObject.canGenerate() || genObject.canGenerateEdit() ||
        genObject.canGenerateEditor();
    }

    protected void generate(GenBase genObject, IProgressMonitor progressMonitor)
    {
      progressMonitor.beginTask("", 4);
      genObject.generate(new SubProgressMonitor(progressMonitor, 1));
      genObject.generateEdit(new SubProgressMonitor(progressMonitor, 1));
      genObject.generateEditor(new SubProgressMonitor(progressMonitor, 1));
      genObject.generateTests(new SubProgressMonitor(progressMonitor, 1));
    }  
  };
*/

  /**
   * This implements the "Generate..." actions.
   */
  protected class GenerateAction extends Action
  {
    ProjectType[] projectTypes;
    Generator generator;

    public GenerateAction(Object projectType, String projectTypeName, String text)
    {
      super(text);
      this.projectTypes = new ProjectType[] { new ProjectType(projectType, projectTypeName) };
    }

    public GenerateAction(ProjectType[] projectTypes, String text)
    {
      super(text);
      this.projectTypes = projectTypes;
    }

    public boolean isEnabled()
    {
      if (activeEditorPart instanceof GenModelEditor)
      {
        generator = ((GenModelEditor)activeEditorPart).getGenerator();
      }

      if (generator == null)
      {
        return false;
      }

      ISelection s = getActiveEditorSelection();
      if (!(s instanceof IStructuredSelection))
      {
        return false;
      }

      IStructuredSelection ss = (IStructuredSelection)s;
      if (ss.size() == 0)
      {
        return false;
      }

      for (Iterator iter = ss.iterator(); iter.hasNext(); )
      {
        Object object = iter.next();
        boolean canGenerateObject = false;

        for (int i = 0; i < projectTypes.length; i++)
        {
          if (generator.canGenerate(object, projectTypes[i].getType()))
          {
            canGenerateObject = true;
          }
        }

        if (!canGenerateObject)
        {
          return false;
        }
      }
      return true;
    }

    public void run()
    {
      // Do the work within an operation because this is a long running activity that modifies the workbench.
      //
      WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
      {
        // This is the method that gets invoked when the operation runs.
        //
        protected void execute(IProgressMonitor progressMonitor) throws CoreException
        {
          Collection selection = ((IStructuredSelection)getActiveEditorSelection()).toList();
          progressMonitor.beginTask("", selection.size() * projectTypes.length);
          try
          {
            BasicDiagnostic diagnostic = new BasicDiagnostic(GenModelEditPlugin.ID, 0, getText(), null);

            LOOP:
            for (Iterator iter = selection.iterator(); iter.hasNext(); )
            {
              Object object = iter.next();
              for (int i = 0; i < projectTypes.length; i++)
              {
                diagnostic.add
                  (generator.generate
                     (object,
                      projectTypes[i].getType(),
                      projectTypes[i].getName(),
                      BasicMonitor.toMonitor(new SubProgressMonitor(progressMonitor, 1))));

                if (!canContinue(diagnostic))
                {
                  break LOOP;
                }
              }
            }

            if (diagnostic.getSeverity() != Diagnostic.OK)
            {
              final IStatus status = BasicDiagnostic.toIStatus(diagnostic);

              activeEditorPart.getSite().getShell().getDisplay().asyncExec
                (new Runnable()
                 {
                   public void run()
                   {
                     ErrorDialog.openError
                       (activeEditorPart.getSite().getShell(), 
                        GenModelEditPlugin.INSTANCE.getString("_UI_GenerationProblems_title"),
                        GenModelEditPlugin.INSTANCE.getString("_UI_GenerationProblems_message"),
                        status);              
                   }
                 });
              
            }
          }
          catch (Exception exception)
          {
            GenModelEditPlugin.INSTANCE.log(exception);
          }
          progressMonitor.done();
        }

        // Stop only on cancel.
        //
        protected boolean canContinue(Diagnostic diagnostic)
        {
          return diagnostic.getSeverity() != Diagnostic.CANCEL;
        }
      };
    
      // This runs the options, and shows progress.
      // (It appears to be a bad thing to fork this onto another thread.)
      //
      try
      {
        new ProgressMonitorDialog(activeEditorPart.getSite().getShell()).run(true, true, operation);
      }
      catch (Exception exception)
      {
        // Something went wrong that shouldn't.
        //
        GenModelEditPlugin.INSTANCE.log(exception);
      }
    } 
  }

  protected static class ProjectType
  {
    protected Object type;
    protected String name;

    public ProjectType(Object type, String name)
    {
      this.type = type;
      this.name = name;
    }

    public Object getType()
    {
      return type;
    }

    public String getName()
    {
      return name;
    }
  }

/*
  protected abstract class GenerateAction extends Action
  {
    public GenerateAction(String text)
    {
      super(text);
    }

    protected abstract boolean canGenerate(GenBase genObject);
    protected abstract void generate(GenBase genObject, IProgressMonitor progressMonitor);

    public boolean isEnabled()
    {
      ISelection s = getActiveEditorSelection();
      if (!(s instanceof IStructuredSelection))
      {
        return false;
      }

      IStructuredSelection ss = (IStructuredSelection) s;
      if (ss.size() == 0)
      {
        return false;
      }

      for (Iterator iter = ss.iterator(); iter.hasNext(); )
      {
        Object selected = iter.next();
        if (!(selected instanceof GenBase) || !canGenerate((GenBase)selected))
        {
          return false;
        }
      }
      return true;
    }
  
    public void run()
    {
      // Do the work within an operation because this is a long running activity that modifies the workbench.
      WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
      {
        // This is the method that gets invoked when the operation runs.
        //
        protected void execute(IProgressMonitor progressMonitor) throws CoreException
        {
          Collection selection = ((IStructuredSelection)getActiveEditorSelection()).toList();
          progressMonitor.beginTask("", selection.size());
          try
          {
            for (Iterator iter = selection.iterator(); iter.hasNext(); )
            {
              generate((GenBase)iter.next(), new SubProgressMonitor(progressMonitor, 1));
            }          
          }
          catch (Exception exception)
          {
            GenModelEditPlugin.INSTANCE.log(exception);
          }
          progressMonitor.done();
        }
      };
    
      // This runs the options, and shows progress.
      // (It appears to be a bad thing to fork this onto another thread.)
      //
      try
      {
        new ProgressMonitorDialog(activeEditorPart.getSite().getShell()).run(true, false, operation);
      }
      catch (Exception exception)
      {
        // Something went wrong that shouldn't.
        //
        GenModelEditPlugin.INSTANCE.log(exception);
      }
    } 
  }
*/

  protected ViewerFilterAction showGenAnnotationsAction = new ViewerFilterAction(GenModelEditPlugin.INSTANCE.getString("_UI_ShowGenAnnotation_menu_item"), IAction.AS_CHECK_BOX)
  {
    public boolean select(Viewer viewer, Object parentElement, Object element)
    {
      return !(element instanceof GenAnnotation) || isChecked();
    }    
  };
  
  protected abstract class CreateAction extends CommandActionHandler
  {
    protected String label;
    
    public CreateAction(String text, String label)
    {
      super(null, text);
      this.label = label;
    }
    
    public void dispose()
    {
      setEditingDomain(null);
    }
    
    public Command createCommand(Collection selection)
    {
      if (activeEditorPart instanceof IEditingDomainProvider)
      {
        setEditingDomain(((IEditingDomainProvider)activeEditorPart).getEditingDomain());
      }
      
      if (getEditingDomain() != null && selection.size() == 1)
      {
        Object selectedObject = selection.iterator().next();
        if (selectedObject instanceof GenBase)
        {
          Command command = doCreateCommand((GenBase)selectedObject);
          if (command != null)
          {
            command = new CommandWrapper(label, null, command);
          }
          return command;
        }
      }
      return UnexecutableCommand.INSTANCE;
    }
    
    protected abstract Command doCreateCommand(GenBase selectedObject);
  }
  
  protected CreateAction annotateAction = new CreateAction(
    GenModelEditPlugin.INSTANCE.getString("_UI_Annotate_menu_item"),
    GenModelEditPlugin.INSTANCE.getString("_UI_Annotate_text"))
  {
    protected Command doCreateCommand(GenBase selectedObject)
    {
      return AddCommand.create(getEditingDomain(), selectedObject, GenModelPackage.Literals.GEN_BASE__GEN_ANNOTATIONS, selectedObject.getGenModel().createGenAnnotation());
    }
  };

  protected CreateAction addDetailAction = new CreateAction(
    GenModelEditPlugin.INSTANCE.getString("_UI_AddDetail_menu_item"),
    GenModelEditPlugin.INSTANCE.getString("_UI_AddDetail_text"))
  {
    protected Command doCreateCommand(GenBase selectedObject)
    {
      return AddCommand.create(getEditingDomain(), selectedObject, GenModelPackage.Literals.GEN_ANNOTATION__DETAILS, EcoreUtil.create(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY));
    }
  };
    
  protected static abstract class OpenEObjectEditorAction extends BaseSelectionListenerAction
  {
    protected EObject eObject;

    public OpenEObjectEditorAction(String text)
    {
      super(text);
    }
    
    public void dispose()
    {
      eObject = null;
      clearCache();
    }
    
    public void run()
    {
      if (eObject != null)
      {
        try
        {
          EditUIUtil.openEditor(eObject);
        }
        catch (PartInitException e)
        {
          e.printStackTrace();
        }
      }
    }
    
    protected boolean updateSelection(IStructuredSelection selection)
    {
      if (selection.size() == 1)
      {
        Object element = selection.getFirstElement();
        eObject = getEObject(element);
        return eObject != null;
      }
      return false;
    }
    
    protected abstract EObject getEObject(Object element);
  }
  
  protected OpenEObjectEditorAction openEcoreAction = new OpenEObjectEditorAction(GenModelEditPlugin.INSTANCE.getString("_UI_OpenEcore_menu_item"))
  {
    protected EObject getEObject(Object element)
    {
      return element instanceof GenBase ? ((GenBase)element).getEcoreModelElement() : null;
    }
  };

  protected OpenEObjectEditorAction openGenModelAction = new OpenEObjectEditorAction(GenModelEditPlugin.INSTANCE.getString("_UI_OpenGenModel_menu_item"))
  {
    protected EObject getEObject(Object element)
    {
      if (activeEditorPart instanceof IEditingDomainProvider && element instanceof EObject)
      {
        EObject eObject = (EObject)element;
        EditingDomain editingDomain = ((IEditingDomainProvider)activeEditorPart).getEditingDomain(); 
        if (editingDomain.getResourceSet().getResources().indexOf(eObject.eResource()) != 0)
        {
          return eObject;
        }
      }
      return null;
    }
  };

  
  /**
   * This creates an instance of the contributor.
   */
  public GenModelActionBarContributor()
  {
    showGenAnnotationsAction.setChecked(
      Boolean.valueOf(GenModelEditPlugin.getPlugin().getDialogSettings().get("showGenAnnotationsAction")).booleanValue());
  }
  
  public void dispose()
  {
    GenModelEditPlugin.getPlugin().getDialogSettings().put(
      "showGenAnnotationsAction", Boolean.toString(showGenAnnotationsAction.isChecked()));
    
    showGenAnnotationsAction.dispose();   
    annotateAction.dispose();
    addDetailAction.dispose();
    openEcoreAction.dispose();
    openGenModelAction.dispose();
       
    super.dispose();
  }

  /**
   * This adds menu contributions for the generate actions.
   */
  public void contributeToMenu(IMenuManager menuManager)
  {
    super.contributeToMenu(menuManager);

    generateMenuManager = 
      new MenuManager(GenModelEditPlugin.INSTANCE.getString("_UI_Generate_menu"), "org.eclipse.emf.codegen.ecore.genmodelMenuID");
    menuManager.insertAfter("additions", generateMenuManager);
    generateMenuManager.add(generateModelAction);
    generateMenuManager.add(generateEditAction);
    generateMenuManager.add(generateEditorAction);
    generateMenuManager.add(generateTestsAction);
    generateMenuManager.add(generateAllAction);

    // generateMenuManager.add(new Separator("schema-actions"));
    // generateMenuManager.add(generateSchemaAction);    

    generateMenuManager.add(new Separator("annotation-actions"));
    generateMenuManager.add(showGenAnnotationsAction);    
    
    generateMenuManager.add(new Separator("annotation-actions"));
    generateMenuManager.add(new Separator("global-actions"));
  }

  /**
   * This adds Separators for editor additions to the tool bar.
   */
  public void contributeToToolBar(IToolBarManager toolBarManager)
  {
    toolBarManager.add(new Separator("genmodel-settings"));
    toolBarManager.add(new Separator("genmodel-additions"));
  }

  /**
   * When the active editor changes, this remembers the change,
   */
  public void setActiveEditor(IEditorPart part)
  {
    super.setActiveEditor(part);

    if (part instanceof GenModelEditor)
    {
      showGenAnnotationsAction.addViewer(((GenModelEditor)part).getViewer());
      showGenAnnotationsAction.setEnabled(true);
    }
    else
    {
      showGenAnnotationsAction.setEnabled(false);
    }
    
    activeEditorPart = part;

    // Switch to the new selection provider.
    //
    if (selectionProvider != null)
    {
      selectionProvider.removeSelectionChangedListener(this);
    }
    if (part == null)
    {
      selectionProvider = null;
    }
    else
    {
      selectionProvider = part.getSite().getSelectionProvider();
      selectionProvider.addSelectionChangedListener(this);

      // Fake a selection changed event to update the menus.
      //
      if (selectionProvider.getSelection() != null)
      {
        selectionChanged(new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection()));
      }
    }    
  }

  /**
   * This implements {@link ISelectionChangedListener}, refreshing the
   * "Generate..." action contribution managers in the pull-down menu.
   */
  public void selectionChanged(SelectionChangedEvent event)
  {
    IContributionItem[] items = generateMenuManager.getItems();
    for (int i = 0, len = items.length; i < len; i++) items[i].update();
    
    annotateAction.selectionChanged(event);
    addDetailAction.selectionChanged(event);
    openEcoreAction.selectionChanged(event);
    openGenModelAction.selectionChanged(event);
  }

  /**
   * This populates the pop-up menu before it appears.
   */
  public void menuAboutToShow(IMenuManager menuManager)
  {
    generateAllAction.setEnabled(generateAllAction.isEnabled());
    // generateSchemaAction.setEnabled(generateSchemaAction.isEnabled());
    generateTestsAction.setEnabled(generateTestsAction.isEnabled());
    generateEditorAction.setEnabled(generateEditorAction.isEnabled());
    generateEditAction.setEnabled(generateEditAction.isEnabled());
    generateModelAction.setEnabled(generateModelAction.isEnabled());
    refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());
    
    super.menuAboutToShow(menuManager);

    menuManager.insertBefore("additions", new Separator("generate-actions"));
    menuManager.insertAfter("generate-actions", generateAllAction);
    menuManager.insertAfter("generate-actions", generateTestsAction);
    menuManager.insertAfter("generate-actions", generateEditorAction);
    menuManager.insertAfter("generate-actions", generateEditAction);
    menuManager.insertAfter("generate-actions", generateModelAction);

    // menuManager.insertBefore("additions", new Separator("schema-actions"));
    // menuManager.insertAfter("schema-actions", generateSchemaAction);

    menuManager.insertBefore("additions", new Separator("open-actions"));
    menuManager.insertAfter("open-actions", openGenModelAction);
    menuManager.insertAfter("open-actions", openEcoreAction);
    
    if (showGenAnnotationsAction.isChecked())
    {
      menuManager.insertBefore("additions", new Separator("annotation-actions"));
      if (addDetailAction.isEnabled()) menuManager.insertAfter("annotation-actions", addDetailAction);
      if (annotateAction.isEnabled()) menuManager.insertAfter("annotation-actions", annotateAction);
    }
  }

  /**
   * This inserts global actions before the "additions-end" separator.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addGlobalActions(IMenuManager menuManager)
  {
    menuManager.insertAfter("additions-end", new Separator("ui-actions"));
    menuManager.insertAfter("ui-actions", showPropertiesViewAction);

    refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());		
    menuManager.insertAfter("ui-actions", refreshViewerAction);

    super.addGlobalActions(menuManager);
  }

}
