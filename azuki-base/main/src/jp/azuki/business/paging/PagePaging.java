package jp.azuki.business.paging;

/**
 * このクラスは、ページ機能を実装するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/03
 * @author Kawakicchi
 */
public class PagePaging implements Paging {

	private long size;

	private long page;

	/**
	 * コンストラクタ
	 * 
	 * @param aSize ページサイズ
	 */
	public PagePaging(final long aSize) {
		size = aSize;
		page = 0;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aPage ページ数(0始まり)
	 * @param aSize ページサイズ
	 */
	public PagePaging(final long aPage, final long aSize) {
		size = aSize;
		page = aPage;
	}

	@Override
	public long getSize() {
		return size;
	}

	@Override
	public long getPage() {
		return page;
	}

	@Override
	public String getSinceId() {
		return null;
	}

	@Override
	public String getMaxId() {
		return null;
	}

	@Override
	public String getKey() {
		return null;
	}

}
