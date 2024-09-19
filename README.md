# Init

github action and argocd for CI/CD

## Quick start

**local settings.xml 설정 필요**

```sh
mvn spring-boot:run
```

## Prerequisite to deploy

VM 및 k8s cluster 배포를 위한 사전 작업

### GitOps manifest 적용

ArgoCD Application 및 Repository Secret 생성

```sh
kustomize build --enable-helm ./manifests/overlays/<env-name>/gitops | kubectl apply -f -
```

### Github Actions 변수 설정 

Github Actions secrets / variables 생성

```sh
gh variable set -f .ghvars
gh secret set -f .ghsecrets
```

- template for secrets

    ```sh
    AZURE_CREDENTIALS_COMMON=<azure-service-principal-credentials>
    AZURE_CREDENTIALS_DEV=<azure-service-principal-credentials>
    AZURE_CREDENTIALS_PRD=<azure-service-principal-credentials>
    > e.g.
        {
            "clientId": "00000000-0000-0000-0000-000000000000",
            "tenantId": "00000000-0000-0000-0000-000000000001",
            "clientSecret": "<client-secret>",
            "subscriptionId": "00000000-0000-0000-0000-000000000002"
        }
    AZURE_VM_IP_DEV=<deploy-vm-ip-for-ssh>
    AZURE_VM_IP_PRD=<deploy-vm-ip-for-ssh>
    AZURE_VM_SSH_PRIVATE_KEY_DEV=<deploy-vm-private-key-for-ssh>
    AZURE_VM_SSH_PRIVATE_KEY_PRD=<deploy-vm-private-key-for-ssh>
    GH_ADMIN_TOKEN=<github-pat>
    ```

- template for vars

    ```sh
    ACR_LOGIN_SERVER=<acr-name>.azurecr.io
    ACR_NAME=<acr-name>
    ACR_REPOSITORY=<repo-name>
    APP_NAME=<app-name>
    AZURE_COMMON_RESOURCE_GROUP=<azure-common-resource-group-name>
    AZURE_COMMON_STORAGE_ACCOUNT=<azure-common-storage-account-name>
    AZURE_COMMON_ARTIFACT_CONTAINER=<azure-common-blob-artifact-container-name>
    AZURE_RESOURCE_PREFIX=<azure-resource-prefix-for-deploy-vm>
    AZURE_VM_PREFIX=<deploy-vm-name-prefix>
    AZURE_VM_USER=<deploy-vm-user>
    ```
