package org.codehaus.groovy.grails.plugins.web.filters;

import groovy.lang.MetaMethod;

import org.codehaus.groovy.reflection.CachedClass;

/**
 * MetaMethod implementation that delegates to real MetaMethod implementation.
 *
 * This can be used to efficiently proxy a metamethod from another metaClass in methodMissing.
 * An example can be found in FilterConfig's methodMissing .
 *
 * Without this class it's hard to implement efficient methodMissing "caching" supporting methods with multiple signatures (same method name, different set of arguments).
 *
 * This class could be moved to org.codehaus.groovy.grails.commons.metaclass for reuse.
 *
 * @author Lari Hotari
 */
@SuppressWarnings("rawtypes")
class DelegateMetaMethod extends MetaMethod {
    static interface DelegateMetaMethodTargetStrategy {
        public Object getTargetInstance(Object instance);
    }

    private MetaMethod delegateMethod;
    private DelegateMetaMethodTargetStrategy targetStrategy;

    public DelegateMetaMethod(MetaMethod delegateMethod, DelegateMetaMethodTargetStrategy targetStrategy) {
        this.delegateMethod = delegateMethod;
        this.targetStrategy = targetStrategy;
    }

    @Override
    public int getModifiers() {
        return delegateMethod.getModifiers();
    }

    @Override
    public String getName() {
        return delegateMethod.getName();
    }

    @Override
    public Class getReturnType() {
        return delegateMethod.getReturnType();
    }

    @Override
    public Object invoke(Object object, Object[] arguments) {
        return delegateMethod.invoke(targetStrategy.getTargetInstance(object), arguments);
    }

    @Override
    public void checkParameters(Class[] arguments) {
        delegateMethod.checkParameters(arguments);
    }

    @Override
    public CachedClass[] getParameterTypes() {
        return delegateMethod.getParameterTypes();
    }

    @Override
    public boolean isMethod(MetaMethod method) {
        return delegateMethod.isMethod(method);
    }

    @Override
    public Class[] getNativeParameterTypes() {
        return delegateMethod.getNativeParameterTypes();
    }

    @Override
    public boolean isVargsMethod(Object[] arguments) {
        return delegateMethod.isVargsMethod(arguments);
    }

    @Override
    public String toString() {
        return delegateMethod.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return delegateMethod.equals(obj);
    }

    @Override
    public Object clone() {
        return delegateMethod.clone();
    }

    @Override
    public boolean isStatic() {
        return delegateMethod.isStatic();
    }

    @Override
    public boolean isAbstract() {
        return delegateMethod.isAbstract();
    }

    @Override
    public Object[] correctArguments(Object[] argumentArray) {
        return delegateMethod.correctArguments(argumentArray);
    }

    @Override
    public boolean isCacheable() {
        return delegateMethod.isCacheable();
    }

    @Override
    public String getDescriptor() {
        return delegateMethod.getDescriptor();
    }

    @Override
    public String getSignature() {
        return delegateMethod.getSignature();
    }

    @Override
    public String getMopName() {
        return delegateMethod.getMopName();
    }

    @Override
    public Object doMethodInvoke(Object object, Object[] argumentArray) {
        return delegateMethod.doMethodInvoke(targetStrategy.getTargetInstance(object), argumentArray);
    }

    @Override
    public boolean isValidMethod(Class[] arguments) {
        return delegateMethod.isValidMethod(arguments);
    }

    @Override
    public boolean isValidExactMethod(Object[] args) {
        return delegateMethod.isValidExactMethod(args);
    }

    @Override
    public boolean isValidExactMethod(Class[] args) {
        return delegateMethod.isValidExactMethod(args);
    }

    @Override
    public boolean isValidMethod(Object[] arguments) {
        return delegateMethod.isValidMethod(arguments);
    }

    @Override
    public CachedClass getDeclaringClass() {
        return delegateMethod.getDeclaringClass();
    }
}
