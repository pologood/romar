/**
 * Copyright 2012 Anjuke Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anjuke.romar.core.handlers;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;

import com.anjuke.romar.core.RomarResponse;
import com.anjuke.romar.core.impl.request.PreferenceRomarRequest;
import com.anjuke.romar.core.impl.response.MultiValueResponse;
import com.anjuke.romar.mahout.MahoutService;

public class SimilarUserHandler extends PreferenceBaseHandler {

    public SimilarUserHandler(MahoutService service) {
        super(service);
    }

    @Override
    public RomarResponse process(PreferenceRomarRequest request) throws Exception {
        int howMany = request.getLimit();
        if (howMany <= 0) {
            howMany = DEFAULT_HOW_MANY;
        }
        long[] ids = _service.mostSimilarUserIDs(request.getUserId(), howMany);
        return new MultiValueResponse(Arrays.asList(ArrayUtils.toObject(ids)));
    }

}
