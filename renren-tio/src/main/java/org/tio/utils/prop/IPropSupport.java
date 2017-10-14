package org.tio.utils.prop;

public interface IPropSupport {
    public void clearAttribute();
    public Object getAttribute(String key);
    public void removeAttribute(String key);
    public void setAttribute(String key,Object value);
}
