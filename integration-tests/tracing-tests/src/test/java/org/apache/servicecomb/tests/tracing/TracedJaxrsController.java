/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.tests.tracing;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestSchema(schemaId = "someTracedJaxrsRestEndpoint")
@Path("/jaxrs")
public class TracedJaxrsController {
  private static final Logger logger = LoggerFactory.getLogger(TracedJaxrsController.class);

  private final Random random = new Random();

  //@Autowired
  //private RestTemplate template;

  private TracedPojo tracedPojo;

  @Autowired
  public TracedJaxrsController(TracedPojo tracedPojo) {
    this.tracedPojo = tracedPojo;
  }

  @GET
  @Path("/bonjour")
  @Produces(TEXT_PLAIN)
  public String bonjour(HttpServletRequest request) throws InterruptedException {
    logger.info("in /bonjour");
    Thread.sleep(random.nextInt(100));

    return "bonjour le monde, " + tracedPojo.pojo();
  }
}
