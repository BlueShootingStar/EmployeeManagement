package com.thongddps08464.assignmentjava5.helper;

import java.text.MessageFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Component {
	private String css;
	private String js;
	private String html;
	
	public static Component build(String child) {
		String root = "components/content";
		
		String fragmentCss = "/index-css";
		String fragmentJs = "/index-js";
		String fragmentHtml = "/index";
		
		String css = MessageFormat.format("{0}{1}{2}", root, child, fragmentCss);
		String js = MessageFormat.format("{0}{1}{2}", root, child, fragmentJs);
		String html = MessageFormat.format("{0}{1}{2}", root, child, fragmentHtml);
		
		return new Component(css, js, html);
	}
}
