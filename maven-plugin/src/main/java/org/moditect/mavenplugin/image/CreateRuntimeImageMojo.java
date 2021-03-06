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
package org.moditect.mavenplugin.image;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.artifact.handler.manager.ArtifactHandlerManager;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.shared.artifact.resolve.ArtifactResolver;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.repository.RemoteRepository;
import org.moditect.commands.CreateRuntimeImage;
import org.moditect.mavenplugin.image.model.Launcher;
import org.moditect.mavenplugin.util.MojoLog;

/**
 * @author Gunnar Morling
 */
@Mojo(name = "create-runtime-image", defaultPhase = LifecyclePhase.PACKAGE)
public class CreateRuntimeImageMojo extends AbstractMojo {

    @Component
    private ArtifactResolver artifactResolver;

    @Component
    private ArtifactHandlerManager artifactHandlerManager;

    @Parameter( defaultValue = "${session}", readonly = true, required = true )
    private MavenSession session;

    @Component
    private RepositorySystem repoSystem;

    @Parameter( defaultValue = "${repositorySystemSession}", readonly = true, required = true )
    private RepositorySystemSession repoSession;

    @Parameter( defaultValue = "${project.remoteProjectRepositories}", readonly = true, required = true )
    private List<RemoteRepository> remoteRepos;

    @Parameter
    private List<File> modulePath;

    @Parameter(property = "outputDirectory", defaultValue = "${project.build.directory}/image")
    private File outputDirectory;

    @Parameter
    private List<String> modules;

    @Parameter
    private Launcher launcher;

    @Parameter
    private Integer compression;

//    @Parameter(property = "moditect.artifact")
//    private String artifactOverride;
//
//    @Parameter(property = "moditect.additionalDependencies")
//    private String additionalDependenciesOverride;
//
//    @Parameter(property = "moditect.moduleName")
//    private String moduleNameOverride;
//
//    @Parameter(property = "moditect.exportExcludes")
//    private String exportExcludesOverride;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        new CreateRuntimeImage(
                modulePath.stream()
                    .map( File::toPath )
                    .collect( Collectors.toSet() ),
                modules,
                launcher != null ? launcher.getName() : null,
                launcher != null ? launcher.getModule() : null,
                outputDirectory.toPath(),
                compression,
                new MojoLog( getLog() )
        )
        .run();
    }
}
