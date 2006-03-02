package org.eclipse.emf.codegen.ecore.templates.model.tests;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class PluginXML
{
  protected static String nl;
  public static synchronized PluginXML create(String lineSeparator)
  {
    nl = lineSeparator;
    PluginXML result = new PluginXML();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<?eclipse version=\"3.0\"?>" + NL;
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "<!--" + NL + " <copyright>" + NL + " </copyright>" + NL;
  protected final String TEXT_4 = NL + " ";
  protected final String TEXT_5 = "Id";
  protected final String TEXT_6 = NL + "-->" + NL + "" + NL + "<plugin";
  protected final String TEXT_7 = ">";
  protected final String TEXT_8 = NL + "    name = \"%pluginName\"" + NL + "    id = \"";
  protected final String TEXT_9 = "\"" + NL + "    version = \"1.0.0\"" + NL + "    provider-name = \"%providerName\">" + NL + "" + NL + "  <requires>";
  protected final String TEXT_10 = NL + "    <import plugin=\"";
  protected final String TEXT_11 = "\" ";
  protected final String TEXT_12 = "export=\"true\"";
  protected final String TEXT_13 = "/>";
  protected final String TEXT_14 = NL + "  </requires>" + NL + "" + NL + "  <runtime>";
  protected final String TEXT_15 = NL + "    <library name=\"";
  protected final String TEXT_16 = ".jar\">";
  protected final String TEXT_17 = NL + "    <library name=\".\">";
  protected final String TEXT_18 = NL + "      <export name=\"*\"/>" + NL + "    </library>" + NL + "  </runtime>";
  protected final String TEXT_19 = NL + "</plugin>" + NL;
  protected final String TEXT_20 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 */

    GenModel genModel = (GenModel)argument;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_5);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_6);
    if (genModel.isBundleManifest()) {
    stringBuffer.append(TEXT_7);
    } else {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genModel.getTestsPluginID());
    stringBuffer.append(TEXT_9);
    for (Iterator j=genModel.getTestsRequiredPlugins().iterator(); j.hasNext();) { String pluginID = (String)j.next();
    stringBuffer.append(TEXT_10);
    stringBuffer.append(pluginID);
    stringBuffer.append(TEXT_11);
    if (!pluginID.startsWith("org.eclipse.core.runtime")) {
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    }
    stringBuffer.append(TEXT_14);
    if (genModel.isRuntimeJar()) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getTestsPluginID());
    stringBuffer.append(TEXT_16);
    } else {
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    stringBuffer.append(TEXT_20);
    return stringBuffer.toString();
  }
}
