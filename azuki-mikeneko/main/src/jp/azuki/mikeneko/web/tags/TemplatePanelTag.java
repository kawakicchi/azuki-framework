package jp.azuki.mikeneko.web.tags;import javax.servlet.jsp.JspException;import javax.servlet.jsp.tagext.Tag;import jp.azuki.web.tags.AbstractTag;public class TemplatePanelTag extends AbstractTag {	private String name;	private String jsp;	public TemplatePanelTag() {		super(TemplatePanelTag.class);	}	public void setName(final String aName) {		name = aName;	}	protected String getName() {		return name;	}	public void setJsp(final String aJsp) {		jsp = aJsp;	}	protected String getJsp() {		return jsp;	}	@Override	public int doStartTag() throws JspException {		return (Tag.SKIP_BODY);	}	@Override	public int doEndTag() throws JspException {		return (Tag.EVAL_PAGE);	}}