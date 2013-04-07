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
package com.google.gwt.site.webapp.server.resources;

import com.google.gwt.site.webapp.server.model.DocModel;
import com.google.gwt.site.webapp.server.model.PMF;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("docs/{other: .*}")
public class DocumentationResource {

  @GET
  @Produces("text/html")

  public Response getHTML(@PathParam("other") String fullPath) {
    PersistenceManager pm = PMF.get().getPersistenceManager();

    try {
      // if (fullPath == null || "".equals(fullPath) || fullPath.endsWith("/")) {
      // fullPath = "index.html";
      // }
      DocModel docModel = pm.getObjectById(DocModel.class, fullPath);
      return Response.ok(docModel.getHtml()).build();
    } catch (JDOObjectNotFoundException e) {
      return Response.status(404).build();
    } finally {
      pm.close();
    }
  }

  @PUT

  public Response store(@PathParam("other") String fullPath, String html) {
    
    DocModel docModel = new DocModel(fullPath, html);
    PersistenceManager pm = PMF.get().getPersistenceManager();

    try {
      pm.makePersistent(docModel);
    } finally {
      pm.close();
    }
    
    return Response.status(201).build();

  }

}
