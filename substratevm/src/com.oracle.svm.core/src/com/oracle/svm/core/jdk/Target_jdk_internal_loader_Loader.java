/*
 * Copyright (c) 2018, 2020, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.svm.core.jdk;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

@TargetClass(className = "jdk.internal.loader.Loader", onlyWith = JDK11OrLater.class)
@SuppressWarnings({"unused", "static-method"})
final class Target_jdk_internal_loader_Loader {

    @Substitute
    protected URL findResource(String mn, String name) {
        return ResourcesHelper.nameToResourceURL(name);
    }

    @Substitute
    public URL findResource(String name) {
        return ResourcesHelper.nameToResourceURL(name);
    }

    @Substitute
    public Enumeration<URL> findResources(String name) {
        return ResourcesHelper.nameToResourceEnumerationURLs(name);
    }

    @Substitute
    public URL getResource(String name) {
        return ResourcesHelper.nameToResourceURL(name);
    }

    @Substitute
    public Enumeration<URL> getResources(String name) throws IOException {
        return ResourcesHelper.nameToResourceEnumerationURLs(name);
    }

    @Substitute
    private List<URL> findResourcesAsList(String name) {
        return ResourcesHelper.nameToResourceListURLs(name);
    }
}
