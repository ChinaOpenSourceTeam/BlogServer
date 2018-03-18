package com.chinaopensource.soft.blogserver.common.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.chinaopensource.soft.blogserver.common.Config;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

public class MyFreeMarkerView extends FreeMarkerView {

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
		model.put("dynamicUrl",Config.DYNAMIC_WEB_URL);
		model.put("staticUrl", Config.WEB_URL);
		super.exposeHelpers(model, request);
	}

}
