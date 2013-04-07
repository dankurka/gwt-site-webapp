/*
 * Copyright 2013 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.site.webapp.server.guice;

import com.google.gwt.site.webapp.server.resources.DocumentationResource;
import com.google.gwt.site.webapp.server.resources.StandardResource;
import com.google.inject.servlet.ServletModule;

import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;
import java.util.Map;

public class MyServletModule extends ServletModule {
  @Override
  protected void configureServlets() {
    // providers
    // bind(VelocityProvider.class);

    // web resources
    bind(StandardResource.class);
    bind(DocumentationResource.class);
    Map<String, String> params = new HashMap<String, String>();

    params.put("com.sun.jersey.config.property.JSPTemplatesBasePath", "/WEB-INF/jsp");

    // params.put("com.sun.jersey.config.feature.ImplicitViewables", "true");
    // params.put("com.sun.jersey.config.feature.Redirect", "true");
    // params.put("com.sun.jersey.config.property.packages", "net.cknudsen.jerseyexample.web");
    // serveRegex("/(images|css|jsp)/.*").with(DefaultWrapperServlet.class);
    serve("/*").with(GuiceContainer.class, params);
  }
}
