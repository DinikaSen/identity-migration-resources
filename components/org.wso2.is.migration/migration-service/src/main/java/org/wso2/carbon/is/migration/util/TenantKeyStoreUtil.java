/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.carbon.is.migration.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.identity.core.migrate.MigrationClientException;
import org.wso2.carbon.is.migration.service.Migrator;

/**
 * Util class for migrating tenant keystores credentials,
 */
public class TenantKeyStoreUtil {

    private static final Logger log = LoggerFactory.getLogger(TenantKeyStoreUtil.class);

    public static void migrateKeystorePasswords(Migrator migrator) throws MigrationClientException {

        RegistryDataManager registryDataManager = RegistryDataManager.getInstance();
        try {
            registryDataManager.migrateKeyStorePassword(migrator.isIgnoreForInactiveTenants(),
                    migrator.isContinueOnError());
        } catch (Exception e) {
            String errorMsg = "Error while migrating tenant key store passwords";
            if (!migrator.isContinueOnError()) {
                throw new MigrationClientException(errorMsg, e);
            }
            log.error(errorMsg);
        }
    }

}
