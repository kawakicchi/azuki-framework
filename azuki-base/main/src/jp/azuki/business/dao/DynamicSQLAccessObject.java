package jp.azuki.business.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.azuki.business.dsql.DynamicSQL;
import jp.azuki.business.paging.Paging;
import jp.azuki.core.util.StringUtility;

/**
 * このクラスは、ダイナミックSQLでのデータアクセス機能を実装したDAOクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public class DynamicSQLAccessObject extends AbstractDatabaseAccessObject {

	private DynamicSQL dsql;

	public DynamicSQLAccessObject(final DynamicSQL aDynamicSQL) {
		super(DynamicSQLAccessObject.class);
		dsql = aDynamicSQL;
	}

	@Override
	protected boolean doExecute() throws DataAccessServiceException {
		boolean result = false;

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement(dsql.getSQL());
			List<Object> parameters = dsql.getParameters();
			if (null != parameters) {
				for (int i = 0; i < parameters.size(); i++) {
					stat.setObject(i + 1, parameters.get(i));
				}
			}

			result = stat.execute();
		} catch (SQLException ex) {
			fatal(dsql.getName(), ex);
			throw new DataAccessServiceException(ex);
		} finally {
			if (null != stat) {
				try {
					stat.close();
				} catch (Exception ex) {
					warn(ex);
				}
			}
		}

		return result;
	}

	@Override
	protected int doUpdate() throws DataAccessServiceException {
		int result = -1;

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement(dsql.getSQL());
			List<Object> parameters = dsql.getParameters();
			if (null != parameters) {
				for (int i = 0; i < parameters.size(); i++) {
					stat.setObject(i + 1, parameters.get(i));
				}
			}

			result = stat.executeUpdate();
		} catch (SQLException ex) {
			fatal(dsql.getName(), ex);
			throw new DataAccessServiceException(ex);
		} finally {
			if (null != stat) {
				try {
					stat.close();
				} catch (Exception ex) {
					warn(ex);
				}
			}
		}

		return result;
	}

	@Override
	protected long doCount() throws DataAccessServiceException {
		long result = -1;

		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = getConnection().prepareStatement(dsql.getSQL());
			List<Object> parameters = dsql.getParameters();
			if (null != parameters) {
				for (int i = 0; i < parameters.size(); i++) {
					stat.setObject(i + 1, parameters.get(i));
				}
			}

			rs = stat.executeQuery();

			if (rs.next()) {
				result = rs.getLong(1);
			}
		} catch (SQLException ex) {
			fatal(dsql.getName(), ex);
			throw new DataAccessServiceException(ex);
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (Exception ex) {
					warn(ex);
				}
			}
			if (null != stat) {
				try {
					stat.close();
				} catch (Exception ex) {
					warn(ex);
				}
			}
		}

		return result;
	}

	@Override
	protected Map<String, Object> doGet() throws DataAccessServiceException {
		Map<String, Object> result = new HashMap<String, Object>();

		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = getConnection().prepareStatement(dsql.getSQL());
			List<Object> parameters = dsql.getParameters();
			if (null != parameters) {
				for (int i = 0; i < parameters.size(); i++) {
					stat.setObject(i + 1, parameters.get(i));
				}
			}

			rs = stat.executeQuery();

			ResultSetMetaData md = rs.getMetaData();
			List<String> columns = new ArrayList<String>();
			List<String> keys = new ArrayList<String>();
			for (int i = 0; i < md.getColumnCount(); i++) {
				String column = md.getColumnName(i + 1);
				columns.add(column);
				keys.add(StringUtility.toCamel(column));
			}

			if (rs.next()) {
				for (int i = 0; i < columns.size(); i++) {
					result.put(keys.get(i), rs.getObject(columns.get(i)));
				}
			}
		} catch (SQLException ex) {
			fatal(dsql.getName(), ex);
			throw new DataAccessServiceException(ex);
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (Exception ex) {
					warn(ex);
				}
			}
			if (null != stat) {
				try {
					stat.close();
				} catch (Exception ex) {
					warn(ex);
				}
			}
		}

		return result;
	}

	@Override
	protected List<Map<String, Object>> doQuery() throws DataAccessServiceException {
		return doQuery(null);
	}

	@Override
	protected List<Map<String, Object>> doQuery(final Paging aPaging) throws DataAccessServiceException {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = getConnection().prepareStatement(dsql.getSQL());
			List<Object> parameters = dsql.getParameters();
			if (null != parameters) {
				for (int i = 0; i < parameters.size(); i++) {
					stat.setObject(i + 1, parameters.get(i));
				}
			}

			rs = stat.executeQuery();

			ResultSetMetaData md = rs.getMetaData();
			List<String> columns = new ArrayList<String>();
			List<String> keys = new ArrayList<String>();
			for (int i = 0; i < md.getColumnCount(); i++) {
				String column = md.getColumnName(i + 1);
				columns.add(column);
				keys.add(StringUtility.toCamel(column));
			}

			// TODO ページング対応
			if (null != aPaging) {
				if (StringUtility.isNotEmpty(aPaging.getKey())) {
					if (StringUtility.isNotEmpty(aPaging.getSinceId())) {
						throw new DataAccessServiceException("Unsupported since pageing.");
					} else if (StringUtility.isNotEmpty(aPaging.getMaxId())) {
						throw new DataAccessServiceException("Unsupported max pageing.");
					} else {
						throw new DataAccessServiceException("Unsupported pageing setting.");
					}
				} else {
					long start = aPaging.getPage() * aPaging.getSize();
					long end = (aPaging.getPage() + 1) * aPaging.getSize();
					long count = 0;
					while (rs.next()) {
						if (count >= end) {
							break;
						} else if (count >= start) {
							Map<String, Object> data = new HashMap<String, Object>();
							for (int i = 0; i < columns.size(); i++) {
								data.put(keys.get(i), rs.getObject(columns.get(i)));
							}
							result.add(data);
						}
						count++;
					}
				}
			} else {
				while (rs.next()) {
					Map<String, Object> data = new HashMap<String, Object>();
					for (int i = 0; i < columns.size(); i++) {
						data.put(keys.get(i), rs.getObject(columns.get(i)));
					}
					result.add(data);
				}
			}
		} catch (SQLException ex) {
			fatal(dsql.getName(), ex);
			throw new DataAccessServiceException(ex);
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (Exception ex) {
					warn(ex);
				}
			}
			if (null != stat) {
				try {
					stat.close();
				} catch (Exception ex) {
					warn(ex);
				}
			}
		}

		return result;
	}

}
