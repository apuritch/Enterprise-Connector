# Openshift configuration

## Secrets

### API Key

`oc create secret generic ghostcms-apikey --from-literal=GHOSTCMSAPIKEY=`

### Ghost CMS Hostname

`oc create secret generic ghostcms-hostname --from-literal=GHOSTCMSHOSTNAME=`

### Zipkin host

`oc create secret generic ghostcms-hostname --from-literal=ZIPKIN_HOST=`