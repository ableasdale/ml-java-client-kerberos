 - create /etc/krb5.conf
 
```
[logging]
default = FILE:/tmp/rb5libs.log
kdc = FILE:/tmp/krb5kdc.log
admin_server = FILE:/tmp/kadmind.log
[libdefaults]
default_realm = KERBEROSTEST.LOCAL
dns_lookup_realm = true
dns_lookup_kdc = true
ticket_lifetime = 24h
renew_lifetime = 7d
forwardable = true
[realms]
KERBEROSTEST.LOCAL = {
kdc = YOURHOSTNAME.YOURDOMAIN
admin_server = YOURHOSTNAME.YOURDOMAIN
default_domain = KERBEROSTEST.LOCAL
}
[domain_realm]
.kerberostest.local = KERBEROSTEST.LOCAL
kerberostest.local = KERBEROSTEST.LOCAL
```
 
 - Cache the credentials/password: `kinit -V mjones@KERBEROSTEST.LOCAL`

 - Confirm credentials has been cached `klist`

```
Credentials cache: API:CE48E312-2525-46EF-9DF2-2A80D1AF558C
        Principal: mjones@KERBEROSTEST.LOCAL

  Issued                Expires               Principal
Aug 30 13:12:58 2018  Aug 30 23:12:58 2018  krbtgt/KERBEROSTEST.LOCAL@KERBEROSTEST.LOCAL
```

 - gradle run
```
$ gradle run
Parallel execution is an incubating feature.

> Task :run
13:23:23.579 [main] INFO ConnectKrb - trying to connect using the Kerberos Auth Context
13:23:23.641 [main] DEBUG com.marklogic.client.impl.OkHttpServices - Connecting to engrlab-130-217 at 9000 as mjones@KERBEROSTEST.LOCAL
13:23:23.952 [main] DEBUG com.marklogic.client.impl.OkHttpServices - Posting eval
13:23:24.714 [main] INFO ConnectKrb - Testing connection (eval 1+1): 2

BUILD SUCCESSFUL in 2s
```