package jp.azuki.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;

import jp.azuki.core.util.StringUtility;
import jp.azuki.plugin.PluginManager;
import jp.azuki.plugin.exception.PluginServiceException;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * このクラスは、プラグイン機能をロードするサーブレットクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/08/29
 * @author Kawakicchi
 */
public final class PluginLoadServlet extends AbstractServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5612928065309901778L;

	/**
	 * プラグインコンフィグ
	 */
	private String plugin;

	/**
	 * コンストラクタ
	 */
	public PluginLoadServlet() {
		super(PluginLoadServlet.class);
	}

	@Override
	protected void doInitialize(final ServletConfig aConfit) {
		plugin = aConfit.getInitParameter("plugin-config");
		PluginManager.initialize();
		doLoad();
	}

	@Override
	protected void doDestroy() {
		PluginManager.destroy();
	}

	/**
	 * プラグインのロードを行います。
	 */
	private void doLoad() {
		if (StringUtility.isNotEmpty(plugin)) {
			DOMConfigurator.configure(getContext().getAbstractPath("config/log4j.xml"));

			info("Load plugin config file.[" + plugin + "]");
			try {
				PluginManager.load(plugin, getContext());
			} catch (PluginServiceException ex) {
				error(ex);
			} catch (IOException ex) {
				error(ex);
			}
		} else {
			warn("Not setting plugin-config.[web.xml]");
		}
	}

}
