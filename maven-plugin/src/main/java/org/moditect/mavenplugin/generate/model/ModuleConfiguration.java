/**
 *  Copyright 2017 Gunnar Morling (http://www.gunnarmorling.de/)
 *  and/or other contributors as indicated by the @authors tag. See the
 *  copyright.txt file in the distribution for a full listing of all
 *  contributors.
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
package org.moditect.mavenplugin.generate.model;

import java.util.ArrayList;
import java.util.List;

import org.moditect.mavenplugin.common.model.ArtifactConfiguration;

/**
 * @author Gunnar Morling
 */
public class ModuleConfiguration {

    private ArtifactConfiguration artifact;
    private List<ArtifactConfiguration> additionalDependencies = new ArrayList<>();
    private List<String> exportExcludes = new ArrayList<>();
    private String moduleName;
    private boolean addServiceUses;

    public ArtifactConfiguration getArtifact() {
        return artifact;
    }

    public void setArtifact(ArtifactConfiguration artifact) {
        this.artifact = artifact;
    }

    public List<ArtifactConfiguration> getAdditionalDependencies() {
        return additionalDependencies;
    }

    public void setAdditionalDependencies(List<ArtifactConfiguration> additionalDependencies) {
        this.additionalDependencies = additionalDependencies;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<String> getExportExcludes() {
        return exportExcludes;
    }

    public void setExportExcludes(List<String> exportExcludes) {
        this.exportExcludes = exportExcludes;
    }

    public void setAddServiceUses(boolean addServiceUses) {
        this.addServiceUses = addServiceUses;
    }

    public boolean isAddServiceUses() {
        return addServiceUses;
    }
}
