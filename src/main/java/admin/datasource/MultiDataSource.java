package admin.datasource;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import admin.common.baseenum.DataSourceEnum;

public class MultiDataSource extends AbstractRoutingDataSource {

    private final static ThreadLocal<String> DATA_SOURCE_KEY = new ThreadLocal<>(); //保存当前线程的数据源对应的key

    private  javax.sql.DataSource dataSoruce;
    /**
     * 所有数据源的key集合
     */
    private Map<Object, javax.sql.DataSource> sourceMap ;

    public static void switchSource(DataSourceEnum key) {
        DATA_SOURCE_KEY.set(key.getValue()); 
    }

    public static void switchSource(String key) {
        DATA_SOURCE_KEY.set(key); 
    }
    public static void clear() {
        DATA_SOURCE_KEY.remove(); 
    }
    
    @Override
    protected Object determineCurrentLookupKey() {
        String key = DATA_SOURCE_KEY.get();
        return key;
    }
    
    public javax.sql.DataSource getDataSource() {
    	if(sourceMap.get(DATA_SOURCE_KEY.get())==null) {
    		return dataSoruce;
    	}
    	return sourceMap.get(DATA_SOURCE_KEY.get());
    }

    /**
     * 在获取key的集合，目的只是为了添加一些告警日志
     */
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        try {
            Field sourceMapField = AbstractRoutingDataSource.class.getDeclaredField("resolvedDataSources");
            sourceMapField.setAccessible(true);
            Map<Object, javax.sql.DataSource> sourceMap = (Map<Object, javax.sql.DataSource>) sourceMapField.get(this);
            this.sourceMap = sourceMap;
            sourceMapField.setAccessible(false);
            Field defaultTargetDataSourceField = AbstractRoutingDataSource.class.getDeclaredField("defaultTargetDataSource");
            defaultTargetDataSourceField.setAccessible(true);
            dataSoruce = (DataSource) defaultTargetDataSourceField.get(this);
            sourceMapField.setAccessible(false);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}