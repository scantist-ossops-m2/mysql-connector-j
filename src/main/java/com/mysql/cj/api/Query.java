/*
  Copyright (c) 2017, Oracle and/or its affiliates. All rights reserved.

  The MySQL Connector/J is licensed under the terms of the GPLv2
  <http://www.gnu.org/licenses/old-licenses/gpl-2.0.html>, like most MySQL Connectors.
  There are special exceptions to the terms and conditions of the GPLv2 as it is applied to
  this software, see the FOSS License Exception
  <http://www.mysql.com/about/legal/licensing/foss-exception.html>.

  This program is free software; you can redistribute it and/or modify it under the terms
  of the GNU General Public License as published by the Free Software Foundation; version 2
  of the License.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this
  program; if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth
  Floor, Boston, MA 02110-1301  USA

 */

package com.mysql.cj.api;

import java.util.List;

import com.mysql.cj.api.mysqla.io.ProtocolEntityFactory;
import com.mysql.cj.api.mysqla.result.Resultset;

public interface Query {

    public enum CancelStatus {
        NOT_CANCELED, CANCELED_BY_USER, CANCELED_BY_TIMEOUT;
    }

    /**
     * Returns the query id used when profiling
     */
    int getId();

    void setId(int id);

    void setCancelStatus(CancelStatus cs);

    void checkCancelTimeout();

    <T extends Resultset> ProtocolEntityFactory<T> getResultSetFactory();

    Session getSession();

    Object getCancelTimeoutMutex();

    void resetCancelledState();

    void closeQuery();

    void addBatch(Object batch);

    /**
     * Get the batched args as added by the addBatch method(s).
     * The list is unmodifiable and might contain any combination of String,
     * ClientPreparedQueryBindings, or ServerPreparedQueryBindings depending on how the parameters were
     * batched.
     * 
     * @return an unmodifiable List of batched args
     */
    List<Object> getBatchedArgs();

    void clearBatchedArgs();
}