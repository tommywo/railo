package railo.runtime.type;

import java.util.Map;

import railo.commons.lang.CFTypes;
import railo.commons.lang.StringUtil;
import railo.runtime.ComponentImpl;
import railo.runtime.PageContext;
import railo.runtime.component.Property;
import railo.runtime.exp.PageException;
import railo.runtime.type.Collection.Key;

public class UDFGetterProperty extends UDFGSProperty {

	private Property prop;
	//private ComponentScope scope;
	private Key propName;

	public UDFGetterProperty(ComponentImpl component,Property prop)  {
		super(component,"get"+StringUtil.ucFirst(prop.getName()),new FunctionArgument[0],CFTypes.TYPE_STRING,"wddx");
		this.prop=prop;
		this.propName=KeyImpl.init(prop.getName());
	} 

	/**
	 * @see railo.runtime.type.UDF#duplicate()
	 */
	public UDF duplicate(ComponentImpl c,Map<Object, Object> done) {
		return new UDFGetterProperty(c,prop);
	}
	

	public UDF duplicate(Map<Object, Object> done) {
		return duplicate(component,done);
	}
	
	/**
	 * @see railo.runtime.type.UDF#call(railo.runtime.PageContext, java.lang.Object[], boolean)
	 */
	public Object call(PageContext pageContext, Object[] args,boolean doIncludePath) throws PageException {
		return component.getComponentScope().get(pageContext, propName,null);
	}

	/**
	 * @see railo.runtime.type.UDF#callWithNamedValues(railo.runtime.PageContext, railo.runtime.type.Struct, boolean)
	 */
	public Object callWithNamedValues(PageContext pageContext, Struct values,boolean doIncludePath) throws PageException {
		return component.getComponentScope().get(pageContext,propName,null);
	}

	/**
	 * @see railo.runtime.type.UDF#implementation(railo.runtime.PageContext)
	 */
	public Object implementation(PageContext pageContext) throws Throwable {
		return component.getComponentScope().get(pageContext,propName,null);
	}
	
	/**
	 * @see railo.runtime.type.UDF#getDefaultValue(railo.runtime.PageContext, int)
	 */
	public Object getDefaultValue(PageContext pc, int index) throws PageException {
		return null;
	}

	/**
	 * @see railo.runtime.type.UDF#getReturnTypeAsString()
	 */
	public String getReturnTypeAsString() {
		return prop.getType();
	}


}
