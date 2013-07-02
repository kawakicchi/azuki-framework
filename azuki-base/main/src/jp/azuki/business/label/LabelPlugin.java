package jp.azuki.business.label;

import java.io.IOException;
import java.io.InputStream;

import jp.azuki.business.BusinessServiceException;
import jp.azuki.persistence.ConfigurationFormatException;
import jp.azuki.plugin.AbstractPlugin;
import jp.azuki.plugin.PluginServiceException;

/**
 * このクラスは、ラベル機能をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/02
 * @author Kawakicchi
 */
public final class LabelPlugin extends AbstractPlugin {

	/**
	 * コンストラクタ
	 */
	public LabelPlugin() {
		super(LabelPlugin.class);
	}

	@Override
	protected void doInitialize() throws PluginServiceException {
		LabelManager.initialize();
	}

	@Override
	protected void doDestroy() throws PluginServiceException {
		LabelManager.destroy();
	}

	@Override
	protected void doLoad(final InputStream aStream) throws PluginServiceException, ConfigurationFormatException, IOException {
		try {
			LabelManager.load(aStream, getContext());
		} catch (BusinessServiceException ex) {
			throw new PluginServiceException(ex);
		}
	}

}
