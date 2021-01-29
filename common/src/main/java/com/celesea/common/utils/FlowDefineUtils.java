package com.celesea.common.utils;

import com.hankcs.hanlp.HanLP;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * com.celesea.common.utils.DeploymentUtils
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/27
 */
public class FlowDefineUtils {

  public static String randomKey(String name) {
    String prefix = "WF_";
    String eName = cnNameToPinyinString(name);
    String random = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmssS"));
    return prefix + eName + random;
  }

  public static String cnNameToPinyinString(String chinese) {
    // 申请单 -> SQD
    String eName = HanLP.convertToPinyinFirstCharString(chinese,
        "", false);
    return eName.toUpperCase(Locale.ROOT);
  }

}