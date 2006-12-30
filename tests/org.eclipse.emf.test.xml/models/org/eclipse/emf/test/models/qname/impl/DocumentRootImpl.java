/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id$
 */
package org.eclipse.emf.test.models.qname.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.qname.DocumentRoot;
import org.eclipse.emf.test.models.qname.QnamePackage;
import org.eclipse.emf.test.models.qname.ResourceType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAnyE <em>Any E</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAnyEU <em>Any EU</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAInt <em>AInt</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAQname <em>AQname</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAUnion <em>AUnion</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot
{
  /**
   * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMixed()
   * @generated
   * @ordered
   */
  protected FeatureMap mixed = null;

  /**
   * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXMLNSPrefixMap()
   * @generated
   * @ordered
   */
  protected EMap<String, String> xMLNSPrefixMap = null;

  /**
   * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXSISchemaLocation()
   * @generated
   * @ordered
   */
  protected EMap<String, String> xSISchemaLocation = null;

  /**
   * The default value of the '{@link #getAnyE() <em>Any E</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnyE()
   * @generated
   * @ordered
   */
  protected static final Object ANY_E_EDEFAULT = null;

  /**
   * The default value of the '{@link #getAnyEU() <em>Any EU</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnyEU()
   * @generated
   * @ordered
   */
  protected static final List<Object> ANY_EU_EDEFAULT = null;

  /**
   * The default value of the '{@link #getAInt() <em>AInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAInt()
   * @generated
   * @ordered
   */
  protected static final int AINT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getAInt() <em>AInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAInt()
   * @generated
   * @ordered
   */
  protected int aInt = AINT_EDEFAULT;

  /**
   * This is true if the AInt attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean aIntESet = false;

  /**
   * The default value of the '{@link #getAQname() <em>AQname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAQname()
   * @generated
   * @ordered
   */
  protected static final Object AQNAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAQname() <em>AQname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAQname()
   * @generated
   * @ordered
   */
  protected Object aQname = AQNAME_EDEFAULT;

  /**
   * The default value of the '{@link #getAUnion() <em>AUnion</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAUnion()
   * @generated
   * @ordered
   */
  protected static final List<Object> AUNION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAUnion() <em>AUnion</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAUnion()
   * @generated
   * @ordered
   */
  protected List<Object> aUnion = AUNION_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DocumentRootImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return QnamePackage.Literals.DOCUMENT_ROOT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureMap getMixed()
  {
    if (mixed == null)
    {
      mixed = new BasicFeatureMap(this, QnamePackage.DOCUMENT_ROOT__MIXED);
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<String, String> getXMLNSPrefixMap()
  {
    if (xMLNSPrefixMap == null)
    {
      xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, QnamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    }
    return xMLNSPrefixMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<String, String> getXSISchemaLocation()
  {
    if (xSISchemaLocation == null)
    {
      xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, QnamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    }
    return xSISchemaLocation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getAnyE()
  {
    return (Object)getMixed().get(QnamePackage.Literals.DOCUMENT_ROOT__ANY_E, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnyE(Object newAnyE)
  {
    ((FeatureMap.Internal)getMixed()).set(QnamePackage.Literals.DOCUMENT_ROOT__ANY_E, newAnyE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<Object> getAnyEU()
  {
    return (List<Object>)getMixed().get(QnamePackage.Literals.DOCUMENT_ROOT__ANY_EU, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnyEU(List<Object> newAnyEU)
  {
    ((FeatureMap.Internal)getMixed()).set(QnamePackage.Literals.DOCUMENT_ROOT__ANY_EU, newAnyEU);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceType getResource()
  {
    return (ResourceType)getMixed().get(QnamePackage.Literals.DOCUMENT_ROOT__RESOURCE, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetResource(ResourceType newResource, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)getMixed()).basicAdd(QnamePackage.Literals.DOCUMENT_ROOT__RESOURCE, newResource, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResource(ResourceType newResource)
  {
    ((FeatureMap.Internal)getMixed()).set(QnamePackage.Literals.DOCUMENT_ROOT__RESOURCE, newResource);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getAInt()
  {
    return aInt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAInt(int newAInt)
  {
    int oldAInt = aInt;
    aInt = newAInt;
    boolean oldAIntESet = aIntESet;
    aIntESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QnamePackage.DOCUMENT_ROOT__AINT, oldAInt, aInt, !oldAIntESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetAInt()
  {
    int oldAInt = aInt;
    boolean oldAIntESet = aIntESet;
    aInt = AINT_EDEFAULT;
    aIntESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, QnamePackage.DOCUMENT_ROOT__AINT, oldAInt, AINT_EDEFAULT, oldAIntESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetAInt()
  {
    return aIntESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getAQname()
  {
    return aQname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAQname(Object newAQname)
  {
    Object oldAQname = aQname;
    aQname = newAQname;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QnamePackage.DOCUMENT_ROOT__AQNAME, oldAQname, aQname));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<Object> getAUnion()
  {
    return aUnion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAUnion(List<Object> newAUnion)
  {
    List<Object> oldAUnion = aUnion;
    aUnion = newAUnion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QnamePackage.DOCUMENT_ROOT__AUNION, oldAUnion, aUnion));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case QnamePackage.DOCUMENT_ROOT__MIXED:
        return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
      case QnamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
      case QnamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
      case QnamePackage.DOCUMENT_ROOT__RESOURCE:
        return basicSetResource(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case QnamePackage.DOCUMENT_ROOT__MIXED:
        if (coreType) return getMixed();
        return ((FeatureMap.Internal)getMixed()).getWrapper();
      case QnamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        if (coreType) return getXMLNSPrefixMap();
        else return getXMLNSPrefixMap().map();
      case QnamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        if (coreType) return getXSISchemaLocation();
        else return getXSISchemaLocation().map();
      case QnamePackage.DOCUMENT_ROOT__ANY_E:
        return getAnyE();
      case QnamePackage.DOCUMENT_ROOT__ANY_EU:
        return getAnyEU();
      case QnamePackage.DOCUMENT_ROOT__RESOURCE:
        return getResource();
      case QnamePackage.DOCUMENT_ROOT__AINT:
        return new Integer(getAInt());
      case QnamePackage.DOCUMENT_ROOT__AQNAME:
        return getAQname();
      case QnamePackage.DOCUMENT_ROOT__AUNION:
        return getAUnion();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case QnamePackage.DOCUMENT_ROOT__MIXED:
        ((FeatureMap.Internal)getMixed()).set(newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        ((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        ((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__ANY_E:
        setAnyE(newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__ANY_EU:
        setAnyEU((List<Object>)newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__RESOURCE:
        setResource((ResourceType)newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__AINT:
        setAInt(((Integer)newValue).intValue());
        return;
      case QnamePackage.DOCUMENT_ROOT__AQNAME:
        setAQname(newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__AUNION:
        setAUnion((List<Object>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case QnamePackage.DOCUMENT_ROOT__MIXED:
        getMixed().clear();
        return;
      case QnamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        getXMLNSPrefixMap().clear();
        return;
      case QnamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        getXSISchemaLocation().clear();
        return;
      case QnamePackage.DOCUMENT_ROOT__ANY_E:
        setAnyE(ANY_E_EDEFAULT);
        return;
      case QnamePackage.DOCUMENT_ROOT__ANY_EU:
        setAnyEU(ANY_EU_EDEFAULT);
        return;
      case QnamePackage.DOCUMENT_ROOT__RESOURCE:
        setResource((ResourceType)null);
        return;
      case QnamePackage.DOCUMENT_ROOT__AINT:
        unsetAInt();
        return;
      case QnamePackage.DOCUMENT_ROOT__AQNAME:
        setAQname(AQNAME_EDEFAULT);
        return;
      case QnamePackage.DOCUMENT_ROOT__AUNION:
        setAUnion(AUNION_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case QnamePackage.DOCUMENT_ROOT__MIXED:
        return mixed != null && !mixed.isEmpty();
      case QnamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
      case QnamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
      case QnamePackage.DOCUMENT_ROOT__ANY_E:
        return ANY_E_EDEFAULT == null ? getAnyE() != null : !ANY_E_EDEFAULT.equals(getAnyE());
      case QnamePackage.DOCUMENT_ROOT__ANY_EU:
        return ANY_EU_EDEFAULT == null ? getAnyEU() != null : !ANY_EU_EDEFAULT.equals(getAnyEU());
      case QnamePackage.DOCUMENT_ROOT__RESOURCE:
        return getResource() != null;
      case QnamePackage.DOCUMENT_ROOT__AINT:
        return isSetAInt();
      case QnamePackage.DOCUMENT_ROOT__AQNAME:
        return AQNAME_EDEFAULT == null ? aQname != null : !AQNAME_EDEFAULT.equals(aQname);
      case QnamePackage.DOCUMENT_ROOT__AUNION:
        return AUNION_EDEFAULT == null ? aUnion != null : !AUNION_EDEFAULT.equals(aUnion);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (mixed: ");
    result.append(mixed);
    result.append(", aInt: ");
    if (aIntESet) result.append(aInt); else result.append("<unset>");
    result.append(", aQname: ");
    result.append(aQname);
    result.append(", aUnion: ");
    result.append(aUnion);
    result.append(')');
    return result.toString();
  }

} //DocumentRootImpl
