// Generated code from Butter Knife. Do not modify!
package com.leo.test;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MyActivity$$ViewInjector {
  public static void inject(Finder finder, final com.leo.test.MyActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034112, "field 'button'");
    target.button = (android.widget.Button) view;
  }

  public static void reset(com.leo.test.MyActivity target) {
    target.button = null;
  }
}
