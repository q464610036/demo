package com.example.shardingjdbc.module.shardingAlgorithm;

import lombok.Data;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MyHintDataBaseShardingAlgorithm implements HintShardingAlgorithm<Integer> {


    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<Integer> hintShardingValue) {
        //获取设置的数据库下标
        List<Integer> indexList = (List<Integer>)hintShardingValue.getValues();
        //获得库列表
        List<String> dataBaseList = indexList.stream().map(e->
                "db_order"+e.toString()).collect(Collectors.toList());
        if (collection.containsAll(dataBaseList)){
            return dataBaseList;
        }
        throw new UnsupportedOperationException("未找到数据库");
    }

    @Override
    public void init() {

    }

    /**
     * Get type.
     *
     * @return type
     */
    @Override
    public String getType() {
        return "MY_DATABASE_HINT";
    }


}
