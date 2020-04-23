package com.example.base.email;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MailContentBuilder {

	private TemplateEngine templateEngine;

	public MailContentBuilder(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public String build(String templateName, Map<String, Object> mapVariables) {
		Context context = new Context();
		context.setVariables(mapVariables);
		return templateEngine.process(templateName, context);
	}

}
