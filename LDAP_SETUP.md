# Configuring an AD Group for Kerberos authentication and LDAP authorization with MarkLogic Server

This guide follows on from - and relies on - most of the configuration and setup as found in the guide for configuring a Single User for Kerberos authentication with MarkLogic Server.  The additional steps outlined in this guide demonstrate the creation of an Active Directory group and then configuring the necessary mappings for MarkLogic Server.

## Create a Group for LDAP Authorization

Any users added to this group will be mapped to a corresponding MarkLogic group

- Right click on the Start menu and select Run and then type in **dsa.msc** to open **Active Directory Users and Computers**
- New Group
- Give it a name (we will call this **MarkLogic**)



![Run "Active Directory Users and Computers" (dsa.msc)](src/main/resources/images/group-configuration/1_open_ad_users.png)

![Run "Active Directory Users and Computers" (dsa.msc)](src/main/resources/images/group-configuration/2_create_new_group.png)

![Run "Active Directory Users and Computers" (dsa.msc)](src/main/resources/images/group-configuration/3_set_group_name.png)

## Add user(s) to your new group

- Right click on your user (in this example we will be using **testuser** created in the previous guide).

- Use xdmp:ldap-lookup()
  - Note that the lookup path should be the same as the output from `setspn -L testuser` (see SETUP.md for details)
  - Check that ML can read the memberOf attribute using a simple xdmp:ldap-lookup, make a note of the memberOf DN as this is what is used in the role mapping
```xquery
xquery version "1.0-ml";
xdmp:ldap-lookup('CN=testuser,CN=Users,DC=activedirectory,DC=marklogic,DC=com',
  <options xmlns="xdmp:ldap">
      <username>Test User</username>
      <password>testuser-password</password>
      <server-uri>ldap://your-active-directory-hostname:389</server-uri>
      <bind-method>simple</bind-method>
 </options>
)
```

![Run "Active Directory Users and Computers" (dsa.msc)](src/main/resources/images/group-configuration/4_configure_user_properties.png)

![Run "Active Directory Users and Computers" (dsa.msc)](src/main/resources/images/group-configuration/5_memberof_add.png)

![Run "Active Directory Users and Computers" (dsa.msc)](src/main/resources/images/group-configuration/6_confirm_apply.png)

![](src/main/resources/images/group-configuration/7_confirm_testuser_has_searchable_name.png)

![](src/main/resources/images/group-configuration/8_xdmp_ldap_lookup.png)