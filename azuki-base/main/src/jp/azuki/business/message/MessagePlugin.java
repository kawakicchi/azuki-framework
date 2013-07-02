package jp.azuki.business.message;

import java.io.IOException;
import java.io.InputStream;

import jp.azuki.business.BusinessServiceException;
import jp.azuki.persistence.ConfigurationFormatException;
import jp.azuki.plugin.AbstractPlugin;
import jp.azuki.plugin.PluginServiceException;

/**
 * このクラスは、メッセージ機能をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/11
 * @author Kawakicchi
 */
public final class MessagePlugin extends AbstractPlugin {

	/**
	 * コンストラクタ
	 */
	public MessagePlugin() {
		super(MessagePlugin.class);
	}

	@Override
	protected void doInitialize() throws PluginServiceException {
		MessageManager.initialize();
	}

	@Override
	protected void doDestroy() throws PluginServiceException {
		MessageManager.destroy();
	}

	@Override
	protected void doLoad(final InputStream aStream) throws PluginServiceException, ConfigurationFormatException, IOException {
		try {
			MessageManager.load(aStream, getContext());
		} catch (BusinessServiceException ex) {
			throw new PluginServiceException(ex);
		}
	}

}
