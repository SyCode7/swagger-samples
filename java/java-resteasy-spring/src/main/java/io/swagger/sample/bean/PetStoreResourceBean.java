/**
 *  Copyright 2015 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.swagger.sample.bean;

import io.swagger.annotations.*;
import io.swagger.sample.data.StoreData;
import io.swagger.sample.model.Order;
import io.swagger.sample.exception.NotFoundException;
import io.swagger.sample.resource.PetStoreResource;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

public class PetStoreResourceBean implements PetStoreResource {
  static StoreData storeData = new StoreData();
  static JavaRestResourceUtil ru = new JavaRestResourceUtil();

  @Override
  public Response getOrderById(Long orderId) throws NotFoundException {
    Order order = storeData.findOrderById(orderId);
    if (null != order) {
      return Response.ok().entity(order).build();
    } else {
      throw new NotFoundException(404, "Order not found");
    }
  }

  @Override
  public Response placeOrder(Order order) {
    storeData.placeOrder(order);
    return Response.ok().entity("").build();
  }

  @Override
  public Response deleteOrder(String orderId) {
    storeData.deleteOrder(ru.getLong(0, 10000, 0, orderId));
    return Response.ok().entity("").build();
  }
}
