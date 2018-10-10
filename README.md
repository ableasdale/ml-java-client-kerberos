## Introduction

This project contains sample code that demonstrates the use of a number of Java-based client libraries for Kerberos authentication and LDAP authorisation with MarkLogic Server.

All sample code has been tested with the latest release of MarkLogic 9 at the time of writing (9.0-7) and the code demonstrates Kerberos connectivity with:

* The MarkLogic Java Client API ([ConnectJavaClientApiKerberos.java](src/main/java/ConnectJavaClientApiKerberos.java))
* Spring ReST Template ([ConnectJavaClientApiKerberos.java](src/main/java/ConnectJavaClientApiKerberos.java))
* Kerb4J ([Kerb4JExample.java](src/main/java/Kerb4JExample.java))

The three approaches can also be run [within a single test](src/test/java/KerberosConnectionTest.java)

## Getting Started

Read these guides for a complete walkthrough covering Active Directory configuration all the way to configuring MarkLogic for testing:

* [Configuring a Single User for Kerberos authentication with MarkLogic Server (Windows Server 2012)](SETUP.md)
* [Configuring an Active Directory Group for Kerberos authentication and LDAP authorization with MarkLogic Server (Windows Server 2012)](LDAP_SETUP.md)

* [Configuring a Single User for Kerberos authentication with MarkLogic Server (Windows Server 2008)](SETUP_2008.md)
* [Configuring an Active Directory Group for Kerberos authentication and LDAP authorization with MarkLogic Server (Windows Server 2008)](LDAP_SETUP_2008.md)
