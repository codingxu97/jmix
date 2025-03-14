/*
 * Copyright 2021 Haulmont.
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

package io.jmix.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A service for uploading and downloading files to/from {@link FileStorage}s.
 */
public interface FileTransferService {

    void downloadAndWriteResponse(FileRef fileReference,
                                  String fileStorageName,
                                  Boolean attachment,
                                  HttpServletResponse response) throws FileTransferException;

    ResponseEntity<FileInfoResponse> multipartFileUpload(MultipartFile file,
                                                         String name,
                                                         String fileStorageName,
                                                         HttpServletRequest request) throws FileTransferException;

    ResponseEntity<FileInfoResponse> fileUpload(String name,
                                                String fileStorageName,
                                                HttpServletRequest request) throws FileTransferException;
}
