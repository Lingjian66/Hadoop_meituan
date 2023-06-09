package org.batch2.meituan.topFive;


import org.batch2.meituan.bean.MeituanBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MeituanTopFiveMapper extends Mapper<LongWritable, Text, MeituanBean, LongWritable> {


    MeituanBean outKey = new MeituanBean();

    LongWritable outValue = new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        // 封装数据：市  外卖店  月销量
        //通过索引值获取市的名字
        String city = fields[1];
        //通过索引值获得外卖店的名字
        String name = fields[2];
        //通过索引值获得月销售量
        String month_sales = fields[3];

        outKey.set(city, name, Long.parseLong(month_sales));

        outValue.set(Long.parseLong(month_sales));

        //输出
        context.write(outKey, outValue);
    }
}
