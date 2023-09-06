/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.stackdrive.report.script;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.*;
import org.eclipse.jgit.util.FS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Abstract supertype for all git cloning scripts
 */
public class AbstractGitScript extends AbstractConfigScript {

    /**
     * Name of project
     */
    protected String projectName;

    /**
     * Temp directory
     */
    private static String TEMP = null;

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractGitScript.class);

    private final TransportConfigCallback transportConfigCallback = new SshTransportConfigCallback();

    /**
     * Clones repository to temporary path
     *
     * @param sourceRepositories
     */
    protected void cloneGit(List<String> sourceRepositories) {
        try {
            final File tempFile = Files.createTempDirectory(this.getProjectName()).toFile();
            this.tempDirectory = tempFile.getAbsolutePath();

            sourceRepositories.parallelStream().forEach(sourceRepository -> {
                LOG.info("Try to clone: {}", sourceRepository);
                String directory = this.getBasePath() + "/" + createSubProjectName(sourceRepository);
                LOG.info("directory: {}", directory);
                try {
                    Git.cloneRepository()
                        .setDirectory(tempFile)
                        .setTransportConfigCallback(transportConfigCallback)
                        .setURI(sourceRepository)
                        .call();
                    LOG.info("{} {} succesfully cloned", sourceRepository, tempFile);
                } catch (GitAPIException e) {
                    LOG.error("Can't clone repositories", e);
                }
            });
        } catch (IllegalStateException | IOException e) {
            LOG.error("Can't clone repositories", e);
        }
    }

    private static class SshTransportConfigCallback implements TransportConfigCallback {

        private final SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
            @Override
            protected void configure(final OpenSshConfig.Host hc, final Session session) {
                session.setConfig("StrictHostKeyChecking", "no");
            }

            @Override
            protected JSch createDefaultJSch(FS fs) throws JSchException {
                final JSch defaultJSch = super.createDefaultJSch(fs);
                LOG.info("--- createDefaultJSch getIdentityNames {}", defaultJSch.getIdentityNames());
                LOG.info("--- createDefaultJSch getIdentityRepository {}", defaultJSch.getIdentityRepository());
                return defaultJSch;
            }
        };

        @Override
        public void configure(final Transport transport) {
            SshTransport sshTransport = (SshTransport) transport;
            sshTransport.setSshSessionFactory(sshSessionFactory);
        }
    }

    /**
     * Creates branch, makes commit and pushes
     * @param branch
     * @param commitMessage
     */
    protected void createBranchAndPush(String branch, String commitMessage) {
        LOG.info(">>> createBranchAndPush");
        LOG.info(">>> this.getBasePath() {}", this.getBasePath());

        Git.init().setGitDir(new File(this.getBasePath()));
        try (final Git git = Git.open(new File(this.tempDirectory));) {
            LOG.info(">>> try");
            CreateBranchCommand branchCommand = git.branchCreate();
            branchCommand.setName(branch);
            branchCommand.call();
            CheckoutCommand checkoutCommand = git.checkout();
            checkoutCommand.setName(branch).call();
            AddCommand addCommand = git.add();
            addCommand.addFilepattern(".").call();
            git.commit().setAuthor("stackdrive", "").setMessage(commitMessage).call();
            PushCommand pushCommand = git.push();
            pushCommand.setTransportConfigCallback(transportConfigCallback);
            pushCommand.call();
            LOG.info("<<< createBranchAndPush");
        } catch (Exception e) {
            LOG.error("Can't checkout, commit or push", e);
        }
    }

    /**
     * Clears project directory
     */
    protected void clearProjectDirectory () {
        File toDelete = new File(this.getBasePath());
        try {
            FileUtils.deleteDirectory(toDelete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getProjectName() {
        return this.projectName;
    }

    /**
     * Returns project name
     *
     * @param sourceRepositories
     * @return
     */
    protected String createProjectName(List<String> sourceRepositories) {
        String sourceRepository = sourceRepositories.get(0);
        String cut = sourceRepository.substring(0, sourceRepository.length() - 4);
        cut = cut.substring(cut.lastIndexOf('/') + 1);
        if (sourceRepositories.size() > 1) {
            cut = cut + "And" + (sourceRepositories.size() - 1) + "Others";
        }
        return cut;
    }

    /**
     * Returns subproject name
     *
     * @param sourceRepository
     * @return
     */
    protected String createSubProjectName(String sourceRepository) {
        String cut = sourceRepository.substring(0, sourceRepository.length() - 4);
        cut = cut.substring(cut.lastIndexOf('/') + 1);
        return cut;
    }
}
