/*******************************************************************************
 * Copyright [2015] [Onboard team of SERC, Peking University]
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
 *******************************************************************************/
package com.onboard.service.security.interceptors.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.domain.model.User;
import com.onboard.service.security.RoleService;
import com.onboard.service.security.exception.NoPermissionException;
import com.onboard.service.security.interceptors.BasicIdentifiableInterceptor;
import com.onboard.service.security.interceptors.CompanyAdminInSpecificProjectRequired;

@Service("companyAdminInSpecificProjectRequiredBean")
public class CompanyAdminInSpecificProjectRequiredImpl extends BasicIdentifiableInterceptor implements
        CompanyAdminInSpecificProjectRequired {

    @Autowired
    private RoleService roleService;

    @Override
    public boolean roleCheck(Integer companyId, Integer projectId, User user) {

        if (companyId == null || projectId == null) {
            return false;
        }

        if (!roleService.companyAdminInSpecificProject(user.getId(), companyId, projectId)) {
            throw new NoPermissionException(companyId);
        }

        return true;
    }

}
