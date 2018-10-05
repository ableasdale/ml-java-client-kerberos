# Configuring a Single User for Kerberos authentication with MarkLogic Server

## Prerequisites
- A Windows Server (this guide uses Windows Server 2012 R2) which you can RDC into and have Administrator rights.
- A separate instance running MarkLogic 9 (this guide uses Redhat Enterprise Linux 7 as the Operating System)

Notes:
(MarkLogic host is: engrlab-130-175.engrlab.marklogic.com)
(Windows host is: engrlab-129-160.marklogic.com)

## Create a single test user (with Administrator access)

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

## Setting up Active Directory on your Windows Server

- Open the **Server Manager** using the icon in the task bar.
- From the **Server Manager** dashboard, select **Add roles and features** (item 2) from the options
- On the Installation Type screen, select Role-based or features-based and click Next.
- By default, the current server is selected. Click Next.
- On the Server Roles screen, select the check box next to Active Directory Domain Services.



From the task bar, click Open the Server Manager.

Select the yellow notifications icon in the top navigation bar of the Server Manager window.

The Notifications Pane opens and displays a Post-deployment Configuration notification. Click the Promote this server to a domain controller link that appears in the notification.



From the Deployment Configuration tab, select Radial options > Add a new forest. Enter your root domain name in the Root domain name field and click Next.

Select a Domain and a Forest functional level.

Enter a password for Directory Services Restore Mode (DSRM) in the Password field.

Note: The DSRM password is used when booting the Domain Controller into recovery mode.

Review the warning on the DNS Options tab and select Next.

Confirm or enter a NetBIOS name and click Next.

Specify the locations of the Database, Log files, and SYSVOL folders, then click Next.

Review the configuration options and click Next.

The system checks if all of the necessary prerequisites are installed on the system. If the system passes these checks, click Install.

Note: The server automatically reboots after the installation is complete.

After the server reboots, reconnect to it by using Microsoft Remote Desktop Protocol (RDP).



![Server Manager Icon](src/main/resources/images/runthrough/10_open_server_manager.png)

![Add Roles and Features](src/main/resources/images/runthrough/11_add_roles_and_features.png)

![Select Next using the Wizard](src/main/resources/images/runthrough/12_select_next_in_features_wizard.png)

![Select Feature Based Installation](src/main/resources/images/runthrough/13b_feature_based_installation.png)

![Add Active Directory Domain Services](src/main/resources/images/runthrough/13a_add_active_directory_domain_services.png)

![Select Server From Pool](src/main/resources/images/runthrough/14_select_server_from_pool.png)

![Confirm Active Directory Domain Services is Selected](src/main/resources/images/runthrough/15_confirm_choice_and_next.png)

![Confirm Active Directory Domain Services](src/main/resources/images/runthrough/16_confirm_ad_ds.png)

![Install](src/main/resources/images/runthrough/17_install_active_directory.png)

![Install - Progress Bar](src/main/resources/images/runthrough/18_install_progress_bar.png)

![Installation Completed - Close](src/main/resources/images/runthrough/19_install_complete_close_wizard.png)

![Post Install Notification](src/main/resources/images/runthrough/20_post_install_tasks_notification.png)

![Promote Server to Domain Controller](src/main/resources/images/runthrough/21_promote_server_to_dc.png)

![Add New Forest and configure Root Domain](src/main/resources/images/runthrough/22_create_forest_and_root_domain.png)

![Set DSRM Password](src/main/resources/images/runthrough/23_set_dsrm_password.png)

![Configure/Confirm DNS Options](src/main/resources/images/runthrough/24_dns_options.png)

![Configure/Confirm NetBIOS name](src/main/resources/images/runthrough/25_confirm_netbios_name.png)

![Configure Filesystem Paths](src/main/resources/images/runthrough/26_configure_paths.png)

![Review configuration changes](src/main/resources/images/runthrough/27_review_configuration_changes.png)

![Prerequisites Check](src/main/resources/images/runthrough/28_prerequisites.png)

![Installation](src/main/resources/images/runthrough/29_installation.png)

![Reboot](src/main/resources/images/runthrough/30_reboot.png)

## Log back in after reboot

![Specify new Domain](src/main/resources/images/runthrough/31_login_using_new_domain.png)

![Log In Screen](src/main/resources/images/runthrough/32_log_in_as_testuser_on_domain.png)

![]()


