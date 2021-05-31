/*
 * Licensed to Elasticsearch B.V. under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch B.V. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

plugins {
    java
    checkstyle
    `maven-publish`
}

group = "co.elastic.clients"
version = "8.0.0-SNAPSHOT"

java {
    targetCompatibility = JavaVersion.VERSION_1_8
    sourceCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    repositories {
        maven {
            // See https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/elastic/elasticsearch-java")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }

        maven {
            name = "BuildRepo"
            url = uri("${buildDir}/publishing-repository")
        }
    }

    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
            pom {
                name.set("Elasticsearch Java Client")
                artifactId = "elasticsearch-java"
                description.set("Next-gen Elasticsearch Java Client")
                url.set("https://github.com/elastic/elasticsearch-java/")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        name.set("Elastic")
                        url.set("https://www.elastic.co")
                        inceptionYear.set("2020")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/elastic/elasticsearch-java.git")
                    developerConnection.set("scm:git:ssh://git@github.com:elastic/elasticsearch-java.git")
                    url.set("https://github.com/elastic/elasticsearch-java/")
                }
            }
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    val elasticsearchVersion = "7.12.0"
    val jacksonVersion = "2.12.0"

    // Apache 2.0
    implementation("org.elasticsearch.client", "elasticsearch-rest-client", elasticsearchVersion)

    // Apache 2.0
    implementation("com.google.code.findbugs:jsr305:3.0.2")

    // EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
    // https://eclipse-ee4j.github.io/jsonp/
    implementation("jakarta.json", "jakarta.json-api", "2.0.1")

    // Needed even if using Jackson to have an implementation of the Jsonp object model
    // EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
    // https://github.com/eclipse-ee4j/jsonp
    implementation("org.glassfish", "jakarta.json", "2.0.1")

    // EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
    // http://json-b.net/
    compileOnly("jakarta.json.bind", "jakarta.json.bind-api", "2.0.0")
    testImplementation("jakarta.json.bind", "jakarta.json.bind-api", "2.0.0")

    // Apache 2.0
    compileOnly("com.fasterxml.jackson.core", "jackson-core", jacksonVersion)
    compileOnly("com.fasterxml.jackson.core", "jackson-databind", jacksonVersion)
    testImplementation("com.fasterxml.jackson.core", "jackson-core", jacksonVersion)
    testImplementation("com.fasterxml.jackson.core", "jackson-databind", jacksonVersion)

    // EPL-2.0 OR BSD-3-Clause
    // https://eclipse-ee4j.github.io/yasson/
    testImplementation("org.eclipse", "yasson", "2.0.2")

    // Eclipse 1.0
    testImplementation("junit", "junit" , "4.12")

    // MIT
    testImplementation("org.testcontainers", "testcontainers", "1.15.3")
    testImplementation("org.testcontainers", "elasticsearch", "1.15.3")
}
