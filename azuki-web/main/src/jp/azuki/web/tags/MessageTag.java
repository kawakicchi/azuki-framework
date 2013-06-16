package jp.azuki.web.tags;

import javax.servlet.jsp.JspException;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/16
 * @author Kawakicchi
 */
public class MessageTag extends ReaderingTag {

	private String plugin;

	public final void setPlugin(final String aPlugin) {
		plugin = aPlugin;
	}

	protected final String getPlugin() {
		return plugin;
	}

	@Override
	protected void doReadering(StringBuffer reader) throws JspException {

	}

}
