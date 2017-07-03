# Sigfox Rest client #

**Sigfox Rest client** enables java developpers to easly  work with *Sigfox backend*, this **sdk** makes it easy to deal with sigfox backend API, you can get started in few minutes using **maven** .


----------
## Getting Started

#### Minimum requirements ####
to use the sdk,  you will need **java 1.8+** and **Lombok** plugin for your IDE and **maven**

#### Install the SDK ####

 - The sdk maven module is available on **Rtone Repository**  add the remote (artifactory) repository to your POM:

```xml
   <distributionManagement>
       <repository>
           <id>pfiot-repository</id>
           <name>Platform IoT Rtone</name>
           <url>https://redmine.rtone.fr/artifactory/pfiot-respository</url>
       </repository>
   </distributionManagement>
```

 

 - Add the dependency to your POM:

```xml
     <dependency>
         <groupId>fr.rtone</groupId>
         <artifactId>sigfox-client</artifactId>
         <version>1.0</version>
     </dependency>
```


#### How to use  the SDK ####

It's pretty simple to use the SDK, usage typically looks like this :

```java
   // Configure sigfox client
   SigfoxClient sigfoxClient = SigfoxClientFactory.create()
           .setLoggingLevel(HttpLoggingInterceptor.Level.BODY)
           .setCredentials("login-test", "password-test")
           .build();
   
   // get Sigfox group list
   Integer limit = 10;
   Integer offset = 0;
   String parentId = null;
   
   sigfoxClient.getGroupList(limit, offset, parentId)
           .subscribe(groupSigfoxData -> System.out.println(groupSigfoxData.getData()));
   
   // Create a sigfox group
   Group group = SigfoxObjectBuilder.of(Group::new)
           .with(Group::setName, "test-group")
           .with(Group::setParent, "parentId-test")
           .with(Group::setBssId, "bssid-test")
           .with(Group::setDescription, "This is a test group")
           .build();
   
   
   sigfoxClient.createGroup(group)
           .subscribe(createdGroup -> System.out.println("Created group with id " + createdGroup.getId()));
 
```

 ----------
## Contribute 

If you would like to contribute code, you can do so through GitHub by forking the repository and sending a **pull request** 

Here some guidelines to we'd like you to follow :

### Pull Requests

Before you submit your pull request consider the following guidelines:

 - Make your changes in a new git branch
 - Follow our coding style 
 - If the change closes an issue, note that at the end of the commit description ex. **"Fixes #18"**

### Code style
When submitting code, please ensure you follow the [Intellij IDEA's code style](https://www.jetbrains.com/help/idea/reformatting-source-code.html). 

### License

Copyright 2017 Rtone.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License. 
