package com.celesea.engine;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * com.celesea.engine.EngineApplicationTest
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/20
 */
class EngineApplicationTest {


  @Test
  public void test() {

    //前台
    // 变量名字前加#
    String expStr = " {1,2}.contains(#name) and (2==1 or 3==3)";
    ExpressionParser parser = new SpelExpressionParser();
    //创建上下文变量
    EvaluationContext ctx = new StandardEvaluationContext();
    Map<String, Object> map = Map.of("name", 1);
    map.forEach(ctx::setVariable);
    Boolean o = parser.parseExpression(expStr)
        .getValue(ctx, Boolean.class);
    System.out.println(o);
  }
}