# Configuring a Single User

## Prerequisites
- A Windows Server (this guide uses Windows Server 2012 R2) which you can RDC into and have Administrator rights.
- A separate instance running MarkLogic (this guide uses RHEL 7)

(MarkLogic host is: engrlab-130-175.engrlab.marklogic.com)
(Windows host is: engrlab-129-099.marklogic.com)

## Create your test user (with Administrator access)

- Create a Remote Desktop Connection into your Windows Machine and log in.

![RDC Connection](src/main/resources/images/runthrough/1_rdc_connect.png)

- Create a new local user on the server (you'll use this to log back into the machine after Active Directory has been set up)
  - Right click on Start and Select **Computer Management** from the context menu
  - In **Computer Management** expand **Local Users and Groups**, select the **Users** folder icon, right click in the main panel and select **New User...** from the context menu
  - Set the User name field and create a password; uncheck **User must change password at next logon** and click on **Create**
  - Your test user should now be listed as a new user; right click on the user and select **Properties**
  - Select the **MemberOf** tab and select **Add**
  - In the **Select Groups** dialogue box type in *Administrators* and click on **Check Names** then **OK**
  - Confirm user is now listed as being a member of the Administrators group and ensure Apply is set to save the changes
  - Test the login to ensure you're able to get back in after

![Computer Management](src/main/resources/images/runthrough/2_computer_management.png)

![Create New User](src/main/resources/images/runthrough/3_create_new_user.png)

![Enter New User Details](src/main/resources/images/runthrough/4_enter_user_details.png)

![Configure User Properties](src/main/resources/images/runthrough/5_configure_user_properties.png)

![Select MemberOf Tab](src/main/resources/images/runthrough/6_select_memberof_tab.png)

![Add New Group For User](src/main/resources/images/runthrough/7_add_a_new_group_for_user.png)

![Add User To Administrators](src/main/resources/images/runthrough/8_add_user_to_administrators.png)

![Confirm User is Admin](src/main/resources/images/runthrough/9_confirm_user_as_admin.png)

## Set up Active Directory on your Windows Server
